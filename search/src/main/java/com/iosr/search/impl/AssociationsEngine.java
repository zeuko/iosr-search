package com.iosr.search.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.iosr.search.AssociationsGraph;
import com.iosr.search.AssociationsGraphProviderInterface;

public class AssociationsEngine implements AssociationsGraphProviderInterface {
	Map<Integer, List<String>> synonyms;

	public AssociationsEngine() {
		synonyms = new HashMap<Integer, List<String>>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:C:/baza/base.dat");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from lista;");

			int x = 0;
			String s;
			int id;
			String[] parts;
			String word;

			while (rs.next()) {
				List<String> words = new ArrayList<String>();
				id = Integer.parseInt(rs.getString("id"));
				s = rs.getString("synonimy").substring(1);
				s = s.substring(0, s.length() - 1);
				parts = s.split(";");
				int size = parts.length;

				for (int i = 0; i < size; i = i + 1) {
					if(!parts[i].startsWith("|")){
						words.add(parts[i]);
					}
				}
				synonyms.put(new Integer(id), words);
				x++;
				
			}

			rs.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getSynonims(String keyword) {
		Integer result_key = 0;

		for (Map.Entry<Integer, List<String>> entry : synonyms.entrySet()) {
			Integer key = entry.getKey();
			List<String> values = entry.getValue();
			for (String z : values)
				if (z.equals(keyword)) {
					result_key = entry.getKey();
				}
		}
		List<String> result_list = synonyms.get(result_key);
		ArrayList toRemove = new ArrayList();
		if (result_list == null) {
			return null;
		}
//		for (String str : result_list) {
//			if (str.equals(keyword)) {
//				toRemove.add(str);
//			}
//		}
//
//		result_list.removeAll(toRemove);
		System.out.println(result_list);
		return result_list;
	}

	@Override
	public AssociationsGraph getAssociations(String keyword) {
		AssociationsGraph child = new AssociationsGraph(keyword);
		System.out.println("KEYWORD !!" + keyword);
		System.out.println("1"+getSynonims(keyword));
		System.out.println("2"+getSynonims(keyword));
		System.out.println("3"+getSynonims(keyword));


		if (getSynonims(keyword) != null && !getSynonims(keyword).isEmpty()) {
			System.out.println(getSynonims(keyword));
			for (String s : getSynonims(keyword)) {
				System.out.println("Grand - Child:" + s);
				child.addChild(new AssociationsGraph(s));
			}
		}
		return child;
	}

	@Override
	public List<AssociationsGraph> getAssociations(List<String> keywords) {
		List<AssociationsGraph> list = new ArrayList<AssociationsGraph>();
		for (int i = 0; i < keywords.size(); i++) {
			AssociationsGraph child = getAssociations(keywords.get(i));

			list.add(child);
		}
		return list;
	}

	@Override
	public AssociationsGraph getCommonAssociations(List<String> keywords) {
		if (keywords != null && !keywords.isEmpty()) {
			AssociationsGraph ag = getAssociations(keywords.get(0));
			System.out.println("Mother:" + keywords.get(0));
			for (int i = 1; i < keywords.size(); i++) {
				System.out.println("Child:" + keywords.get(i));

				AssociationsGraph child = getAssociations(keywords.get(i));
				ag.addChild(child);
			}

			return ag;
		}
		return new AssociationsGraph("NO RESULTS");
	}

}
