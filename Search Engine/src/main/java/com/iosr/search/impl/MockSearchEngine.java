package com.iosr.search.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.iosr.search.SearchEngineInterface;
import com.iosr.search.SearchResult;


/**
 * Implementacja 'dummy'dla celow testowych.
 * @author Patrycja
 */
public class MockSearchEngine implements SearchEngineInterface {

	@Override
	public List<SearchResult> searchForKeyword(String keyword) {
		List<SearchResult> list = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			SearchResult sr = new SearchResult();
			sr.setUrl("http://olx.pl/nieruchomosci/mieszkania/wynajem/krakow/");
			sr.setDescription("Mieszkania do wynaj�cia na OLX.pl (dawniej Tablica.pl) Krak�w, oferty bezpo�rednie i od agencji nieruchomo�ci. Mieszkanie wynajm� Krak�w - sprawd� nasze og�oszenia.");
			sr.setTitle("Tablica Og�osze� OLX.pl - Mieszkania na wynajem Krak�w, mieszkania do wynaj�cia Krak�w");
			list.add(sr);
		}
		return list;
	}

	@Override
	public List<SearchResult> searchForKeywords(List<String> keywords) {
		List<SearchResult> list = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			SearchResult sr = new SearchResult();
			sr.setUrl("http://olx.pl/nieruchomosci/mieszkania/wynajem/krakow/");
			sr.setDescription("Mieszkania do wynaj�cia na OLX.pl (dawniej Tablica.pl) Krak�w, oferty bezpo�rednie i od agencji nieruchomo�ci. Mieszkanie wynajm� Krak�w - sprawd� nasze og�oszenia.");
			sr.setTitle("Tablica Og�osze� OLX.pl - Mieszkania na wynajem Krak�w, mieszkania do wynaj�cia Krak�w");
			list.add(sr);
		}
		return list;
	}

}
