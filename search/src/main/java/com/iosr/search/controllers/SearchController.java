package com.iosr.search.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.iosr.search.SearchEngineInterface;
import com.iosr.search.SearchResult;
import com.iosr.search.keywords.Keyword;
import com.iosr.search.keywords.KeywordsProviderInterface;

/**
 * Kontroler dla strony glownej
 * 
 * @author Patrycja
 */
@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchEngineInterface searchEngine;

	@Autowired
	private KeywordsProviderInterface keywordsProvider;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleHomePageRequest(Model model, @RequestParam(value="search-input", required=false) String search) {
		logger.info("Request to \"/search\"" + (search == null ? "." : " with phrase: "+ search + "."));
		if (search == null) {
			return "search";
		}
		
		List<Keyword> keywordList = Lists.newArrayList();
		try {
			keywordList = keywordsProvider.getKeywords(search);
		} catch (Exception e) {
			logger.error("Keywords service exception for phrase: "+search, e);
			return "error"; // TODO - add error page
		}

		Collection<String> transform = Collections2.transform(keywordList, new KeywordsToStringFunction());
		
		List<SearchResult> results = searchEngine.searchForKeywords(Lists.newArrayList(transform));
		model.addAttribute("keywords", transform);
		model.addAttribute("results", results);
		
		return "search";
	}
	
	private final class KeywordsToStringFunction implements Function<Keyword, String> {
		@Override
		public String apply(Keyword keyword) {
			return keyword.getBaseWord();
		}
	}
}
	