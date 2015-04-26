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
			sr.setDescription("Mieszkania do wynajêcia na OLX.pl (dawniej Tablica.pl) Kraków, oferty bezpoœrednie i od agencji nieruchomoœci. Mieszkanie wynajmê Kraków - sprawdŸ nasze og³oszenia.");
			sr.setTitle("Tablica Og³oszeñ OLX.pl - Mieszkania na wynajem Kraków, mieszkania do wynajêcia Kraków");
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
			sr.setDescription("Mieszkania do wynajêcia na OLX.pl (dawniej Tablica.pl) Kraków, oferty bezpoœrednie i od agencji nieruchomoœci. Mieszkanie wynajmê Kraków - sprawdŸ nasze og³oszenia.");
			sr.setTitle("Tablica Og³oszeñ OLX.pl - Mieszkania na wynajem Kraków, mieszkania do wynajêcia Kraków");
			list.add(sr);
		}
		return list;
	}

}
