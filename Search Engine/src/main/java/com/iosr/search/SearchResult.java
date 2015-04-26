package com.iosr.search;


/**
 * Rezultaty zamodelowane analogicznie, jak w google:
 * 
 * Tytu³ strony
 * URL
 * 
 * 
 * @author Patrycja
 */
public class SearchResult {
	
	/** Zawartoœæ tagu <title> */
	private String title;
	
	private String url;
	
	/** Zawartoœæ <meta name="description", lub jakiœ fragment zawieraj¹cy keyword */
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
