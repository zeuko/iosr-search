package com.iosr.search.controllers;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.iosr.search.impl.MockSearchEngine;
import com.iosr.search.keywords.Keyword;
import com.iosr.search.keywords.KeywordsProvider;
import com.iosr.search.keywords.MockKeywordProvider;

@Controller
public class HomeController {
	
	private final class KeywordsToStringFunction implements
			Function<Keyword, String> {
		@Override
		public String apply(Keyword arg0) {
			return arg0.getBaseWord();
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final KeywordsProvider keywordsProvider = new MockKeywordProvider();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home2(Locale locale, Model model,  @RequestParam(value="search-input", required=false) String search) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		List<Keyword> keywordList = Lists.newArrayList();
		try {
			keywordList = keywordsProvider.getKeywords(search);
			model.addAttribute("keywords", keywordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SearchEngineInterface sei = new MockSearchEngine();
		Collection<String> transform = Collections2.transform(keywordList, new KeywordsToStringFunction());
		
		List<SearchResult> results = sei.searchForKeywords(Lists.newArrayList(transform));
		
		model.addAttribute("results", results);
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
}
