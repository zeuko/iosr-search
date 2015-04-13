package com.iosr.search.keywords;

import java.util.List;

public interface KeywordsProvider {

	List<Keyword> getKeywords(String string) throws Exception;
	
}
