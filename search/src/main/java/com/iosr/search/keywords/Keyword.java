package com.iosr.search.keywords;

public class Keyword {

	private String baseWord;

	public Keyword() {
	}
	
	public Keyword(String baseWord) {
		this.baseWord = baseWord;
	}
	
	public String getBaseWord() {
		return baseWord;
	}

	public void setBaseWord(String baseWord) {
		this.baseWord = baseWord;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [baseWord=");
		builder.append(baseWord);
		builder.append("]");
		return builder.toString();
	}

}
