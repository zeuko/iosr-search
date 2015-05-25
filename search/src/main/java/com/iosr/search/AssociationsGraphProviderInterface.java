package com.iosr.search;

import java.util.List;


/**
 * Interfejs dla klas zwracajacych informacje o drzewie (grafie) skojarzen.
 * 
 * 
 * @author Patrycja
 */
public interface AssociationsGraphProviderInterface {

	/**
	 * Implementacja tej metody zwraca graf skojarzen dla jednego, podanego slowa kluczowego. 
	 */
	public AssociationsGraph getAssociations(String keyword);
	
	
	/**
	 * Implementacja tej metody zwraca grafy skojarzen odpowiednio dla wszystkich podanych slow kluczowych. 
	 */
	public List<AssociationsGraph> getAssociations(List<String> keywords);
	
	
	/**
	 * Implementacja tej metody zwraca skojarzenia powiazane ze wszystkimi podanymi slowami z listy.
	 * Jako rootName w grafie znajduja sie wszystkie slowa kluczowe.
	 * uwaga -  niewiadomo czy to wykonalne 
	 */
	public AssociationsGraph getCommonAssociations(List<String> keywords);
	
}
