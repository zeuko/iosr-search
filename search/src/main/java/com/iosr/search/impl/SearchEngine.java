package com.iosr.search.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.StreamingUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;

import com.iosr.search.SearchEngineInterface;
import com.iosr.search.SearchResult;


public class SearchEngine implements SearchEngineInterface {

	private static final Logger logger = LoggerFactory.getLogger(SearchEngine.class);

	/** Injected in bean configuration */
	private String pagesDirectory;

	private HttpSolrServer server;
	private AutoDetectParser parse;
	private Collection<SolrInputDocument> documents = new ArrayList<>();
	private int files = 0;

	public SearchEngine(String solrUrl, String pagesDirectory) throws IOException, SolrServerException {
		this.pagesDirectory = pagesDirectory;
		long startTime = System.currentTimeMillis();
		server = new HttpSolrServer(solrUrl);
		server.deleteByQuery("*:*");
		server.setSoTimeout(20000);
		server.setConnectionTimeout(20000);
		server.setMaxRetries(1);
		server.setParser(new XMLResponseParser());
		parse = new AutoDetectParser();
		errorCheck(new File(this.pagesDirectory));
		indStats();
		long endTime = System.currentTimeMillis();
		log("Time: " + (endTime - startTime) + " milliseconds to index " + files + " documents");
	}

	private void indStats() throws IOException, SolrServerException {
		if (documents.size() > 0) {
			server.add(documents, 400000);
		}
		server.commit();
	}

	private List<SearchResult> searchQuery(String keywords) throws IOException, SolrServerException,
			MalformedURLException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keywords);
		QueryResponse response = server.query(query);
		System.out.print(response.getExpandedResults());
		SolrDocumentList results = response.getResults();
		
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		for (int i = 0; i < results.size(); ++i) {
			SearchResult searchResult = new SearchResult();
			searchResult.setTitle((String) results.get(i).getFieldValue("title"));
			String desc = ((String) results.get(i).getFieldValue("description"));
			searchResult.setUrl(desc.split("&&&&")[1]);
			if(searchResult.getTitle() == null  || searchResult.getTitle().isEmpty()){
				searchResult.setTitle(searchResult.getUrl());
			}
			new ScrapyRunner(searchResult.getUrl());
			searchResults.add(searchResult);

		}
		return searchResults;
	}

	private static void log(String msg) {
		logger.info(msg);
	}

	private void errorCheck(File root) throws IOException, SolrServerException {
		int countGood = 0;

		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				errorCheck(file);
				continue;
			}
			ContentHandler textHandler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			countGood++;

			InputStream input = new FileInputStream(file);
			try {
				parse.parse(input, textHandler, metadata, context);
			} catch (Exception e) {
				log(String.format("Fail: %s", file.getCanonicalPath()));
				continue;
			}
			log(String.format("Loaded: %s", file.getCanonicalPath()));

			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", file.getCanonicalPath());
			String author = metadata.get("Author");
			if (author != null) {
				doc.addField("author", author, (float) 1.);
			}
			String title = metadata.get("title");
			if (title == null) {
				doc.addField("title", textHandler.toString().split("&&&&")[1]);
			}else{
			doc.addField("title", title, (float) 1.);
			}
			doc.addField("description", textHandler.toString(), (float) 1.);
			doc.addField("url", textHandler.toString().split("&&&&")[1]);
			documents.add(doc);

			++files;
			if (documents.size() >= 10000) {
				UpdateResponse resp = server.add(documents, 300000);
				if (resp.getStatus() != 0) {
					logger.error("Error has occurred, status: " + resp.getStatus());
				}
				documents.clear();
			}
		}
		log("Count read files : " + countGood);

	}

	@Override
	public List<SearchResult> searchForKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return Collections.emptyList();
		}
		try {
			return searchQuery(keyword);
		} catch (MalformedURLException e) {
			logger.error("SearchEngine error.", e);
		} catch (IOException e) {
			logger.error("SearchEngine error.", e);
		} catch (SolrServerException e) {
			logger.error("SearchEngine error.", e);
		}
		return Collections.emptyList();
	}

	@Override
	public List<SearchResult> searchForKeywords(List<String> keywords) {
		if (keywords == null || keywords.isEmpty()) {
			log("No keywords provided for search");
			return Collections.emptyList();
		}

		try {
			StringBuilder searchString = new StringBuilder();

			for (String string : keywords) {
				searchString.append(string);
				searchString.append(" ");
			}

			return searchQuery(searchString.toString());
		} catch (MalformedURLException e) {
			logger.error("SearchEngine error.", e);
		} catch (IOException e) {
			logger.error("SearchEngine error.", e);
		} catch (SolrServerException e) {
			logger.error("SearchEngine error.", e);
		}
		return Collections.emptyList();
	}

}
