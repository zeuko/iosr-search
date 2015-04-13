package com.iosr.search.keywords;

public class Keyword {

	private String baseWord;
	private String word;

	public String getBaseWord() {
		return baseWord;
	}

	public void setBaseWord(String baseWord) {
		this.baseWord = baseWord;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [baseWord=");
		builder.append(baseWord);
		builder.append(", word=");
		builder.append(word);
		builder.append("]");
		return builder.toString();
	}

}
