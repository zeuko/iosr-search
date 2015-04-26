package com.iosr.search;

import java.util.List;


public interface SearchEngineInterface {

	public List<SearchResult> searchForKeyword(String keyword);
	public List<SearchResult> searchForKeywords(List<String> keywords);
}
