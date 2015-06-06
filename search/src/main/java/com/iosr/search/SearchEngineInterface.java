package com.iosr.search;

import java.util.List;

/**
 * Interfejs dla klas zwracajacych rezultaty wyszukiwania dla zadanych slow
 * kluczowych.
 * 
 * @author Patrycja
 */
public interface SearchEngineInterface {

	/**
	 * Zwraca liste wynikow powiazanych z podanym slowem kluczowym.
	 */
	public List<SearchResult> searchForKeyword(String keyword);

	/**
	 * Zwraca liste wynikow powiazanych ze wszystkimi podanymi slowami
	 * kluczowymi.
	 */
	public List<SearchResult> searchForKeywords(List<String> keywords);
}
