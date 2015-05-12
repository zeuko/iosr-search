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
			sr.setDescription("Mieszkania do wynajecia na OLX.pl (dawniej Tablica.pl) Krakow, oferty bezposrednie i od agencji nieruchomosci. Mieszkanie wynajme Krakow - sprawdz nasze ogloszenia.");
			sr.setTitle("TEST Tablica Ogloszen OLX.pl - Mieszkania na wynajem Krakow, mieszkania do wynajecia Krakow");
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
			sr.setDescription("Mieszkania do wynajecia na OLX.pl (dawniej Tablica.pl) Krakow, oferty bezposrednie i od agencji nieruchomosci. Mieszkanie wynajme Krakow - sprawdz nasze ogloszenia.");
			sr.setTitle("Tablica Ogloszen OLX.pl - Mieszkania na wynajem Krakow, mieszkania do wynajecia Krakow");
			list.add(sr);
		}
		return list;
	}

}
