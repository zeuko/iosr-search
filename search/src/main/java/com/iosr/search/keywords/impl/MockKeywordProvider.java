package com.iosr.search.keywords.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.iosr.search.keywords.Keyword;
import com.iosr.search.keywords.KeywordsProviderInterface;

public class MockKeywordProvider implements KeywordsProviderInterface {

	@Override
	public List<Keyword> getKeywords(String string) throws Exception {
		
		Keyword k1 = new Keyword();
		k1.setBaseWord("Krakow");
		
		Keyword k2 = new Keyword();
		k2.setBaseWord("mieszkanie");
		
		Keyword k3 = new Keyword();
		k3.setBaseWord("wynajem");
		
		return Lists.newArrayList(k1, k2, k3);
	}

}
