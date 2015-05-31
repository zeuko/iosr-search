package com.iosr.search.keywords;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.iosr.search.util.FileHelper;

public class DeclinationService implements InitializingBean {

	private String filePath;
	private Map<String, String> declinationsReverseIndex = new HashMap<>();

	public String getBasicForm(String word) {
		return declinationsReverseIndex.containsKey(word) ? declinationsReverseIndex.get(word) : word;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initializeDictionary();
	}

	private void initializeDictionary() throws IOException {
		File declinationFile = FileHelper.getFileFromClasspath(filePath);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(declinationFile)), "ISO-8859-2"))) {
			String line;
			while ((line = br.readLine()) != null) {
				addDeclinationsToDictionary(line);
			}
		}
	}

	private void addDeclinationsToDictionary(String line) {
		String[] words = line.split(", ");
		if (words.length > 1) {
			String basicWord = words[0];
			for (int i = 1; i < words.length; i++) {
				declinationsReverseIndex.put(words[i], basicWord);
			}
		}
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
