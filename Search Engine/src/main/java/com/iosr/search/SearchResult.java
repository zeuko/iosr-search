package com.iosr.search;


/**
 * Rezultaty zamodelowane analogicznie, jak w google:
 * 
 * Tytul strony (tag title)
 * URL 
 * Opis (description z tagu meta)
 * 
 * @author Patrycja
 */
public class SearchResult {
	
	/** Zawartosc tagu <title> */
	private String title;
	
	private String url;
	
	/** Zawartosc <meta name="description", lub jakis fragment zawierajacy keyword */
	private String description;
	
	public SearchResult() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
