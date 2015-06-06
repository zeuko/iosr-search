package com.iosr.search.associations.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

public class SynonymService implements InitializingBean {

	private Map<Integer, List<String>> synonyms;

	@Override
	public void afterPropertiesSet() throws Exception {
		synonyms = new HashMap<Integer, List<String>>();
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/baza/base.dat");
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from lista;");

		String s;
		int id;
		String[] parts;

		while (rs.next()) {
			List<String> words = new ArrayList<String>();
			id = Integer.parseInt(rs.getString("id"));
			s = rs.getString("synonimy").substring(1);
			s = s.substring(0, s.length() - 1);
			parts = s.split(";");
			int size = parts.length;

			for (int i = 0; i < size; i = i + 1) {
				if (!parts[i].startsWith("|")) {
					words.add(parts[i]);
				}
			}
			synonyms.put(new Integer(id), words);

		}

		rs.close();
		conn.close();

	}

	public List<String> getSynonyms(String keyword) {
		Integer result_key = 0;

		for (Map.Entry<Integer, List<String>> entry : synonyms.entrySet()) {
			List<String> values = entry.getValue();
			for (String z : values)
				if (z.equals(keyword)) {
					result_key = entry.getKey();
				}
		}
		List<String> result_list = synonyms.get(result_key);
		if (result_list == null) {
			return null;
		}

		return result_list;
	}

}
