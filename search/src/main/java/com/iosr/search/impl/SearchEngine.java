package com.iosr.search.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
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
import com.iosr.search.controllers.SearchController;

public class SearchEngine implements SearchEngineInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	private HttpSolrServer server;
	private long _start = System.currentTimeMillis();
	private AutoDetectParser parse;
	private int files = 0;

	private Collection documents = new ArrayList();

	public SearchEngine() throws IOException, SolrServerException {
		this("http://localhost:8983/solr");
	}

	private SearchEngine(String url) throws IOException, SolrServerException {
		server = new HttpSolrServer(url);

		server.deleteByQuery("*:*");
		server.setSoTimeout(20000);
		server.setConnectionTimeout(20000);
		server.setMaxRetries(1);
		server.setParser(new XMLResponseParser());
		parse = new AutoDetectParser();
		errorCheck(new File("c:\\pages"));
		indStats();

	}

	private void indStats() throws IOException, SolrServerException {
		if (documents.size() > 0) {
			server.add(documents, 400000);
		}
		server.commit();

		long endTime = System.currentTimeMillis();
		log("Time: " + (endTime - _start) + " milliseconds to index " + files + " documents");
	}

	private List<SearchResult> searchQuery(String keywords) throws IOException, SolrServerException,
			MalformedURLException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keywords);
		// query.setQuery("tomaszewski"); //// nasze zapytanie
		System.out.println("Searching for query:" + keywords);
		QueryResponse response = server.query(query);
		System.out.print(response.getExpandedResults());
		SolrDocumentList results = response.getResults();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		for (int i = 0; i < results.size(); ++i) {
			int index = i + 1;
			System.out.print(index + ":");
			System.out.println(results.get(i));
			System.out.println("Keyset:" + results.get(i).keySet());
			System.out.println("Values:" + results.get(i).values());
			SearchResult searchResult = new SearchResult();
			searchResult.setTitle((String) results.get(i).getFieldValue("title"));
			String desc = ((String) results.get(i).getFieldValue("description"));
			desc = desc.length() < 300 ? desc : desc.substring(300);
			if (desc.split("&&&&").length > 2) {
				searchResult.setDescription(desc.split("&&&&")[2]);
			}
			searchResult.setUrl(((String) results.get(i).getFieldValue("description")).split("&&&&")[1]);
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
			dumpMetadata(file.getCanonicalPath(), metadata);
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", file.getCanonicalPath());
			String author = metadata.get("Author");

			if (author != null) {
				doc.addField("author", author, (float) 1.);
			}

			String title = metadata.get("title");
			if (title == null) {
				continue;
			}
			doc.addField("title", title, (float) 1.);

			doc.addField("description", textHandler.toString(), (float) 1.);
			documents.add(doc);

			++files;
			if (documents.size() >= 10000) {
				UpdateResponse resp = server.add(documents, 300000);
				if (resp.getStatus() != 0) {
					log("Error has occurred, status: " + resp.getStatus());
				}
				documents.clear();
			}
		}
		System.out.println("Count read files : " + countGood);

	}

	private void dumpMetadata(String fileName, Metadata metadata) {
		// log("Dumping metadata for file: " + fileName);
		// for (String name : metadata.names()) {
		// log(name + ":" + metadata.get(name));
		// }
		// log("\n\n");
	}

	@Override
	public List<SearchResult> searchForKeyword(String keyword) {
		try {
			return searchQuery(keyword);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SearchResult> searchForKeywords(List<String> keywords) {
		try {
			StringBuilder searchString = new StringBuilder();

			for (String string : keywords) {
				searchString.append(string);
				searchString.append(" ");

			}
			System.out.println("Keywords: " + searchString.toString());
			return searchQuery(searchString.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
