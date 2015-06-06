package com.iosr.search.keywords;

import java.util.List;

public interface KeywordsProviderInterface {

	/**
	 * Returns list of keywords for given phrase.  
	 * 
	 * 
	 * @param phrase text to be divided into keywords
	 * @return list of keywords
	 * @throws Exception when any error occurs
	 */
	public List<Keyword> getKeywords(String phrase) throws Exception;
	
}
