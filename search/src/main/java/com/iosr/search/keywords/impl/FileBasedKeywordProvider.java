package com.iosr.search.keywords.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.iosr.search.keywords.DeclinationService;
import com.iosr.search.keywords.Keyword;
import com.iosr.search.keywords.KeywordsProviderInterface;

public class FileBasedKeywordProvider implements KeywordsProviderInterface {

	@Autowired
	private DeclinationService declinationService;
	
	@Override
	public List<Keyword> getKeywords(String phrase) throws Exception {
		
		String[] splittedPhrase = phrase.split(" ");
		List<Keyword> keywords = Arrays.asList(splittedPhrase).stream()
				.map(t -> declinationService.getBasicForm(t))
				.map(t -> new Keyword(t.replaceAll("\\W", "")))
				.collect(Collectors.toList());
		
		return keywords;
	}
	
	
}
