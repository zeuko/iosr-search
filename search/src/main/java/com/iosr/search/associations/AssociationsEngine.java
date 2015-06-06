package com.iosr.search.associations;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iosr.search.associations.database.SynonymService;
import com.iosr.search.impl.SearchEngine;

public class AssociationsEngine implements AssociationsEngineInterface {

	@Autowired
	private SynonymService synonymService;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchEngine.class);

	@Override
	public AssociationsGraphNode getAssociations(String keyword) {
		AssociationsGraphNode child = new AssociationsGraphNode(keyword);
		List<String> synonims = synonymService.getSynonyms(keyword);
		logger.info("Found synonyms for given keyword: " + keyword + " - "+synonims);
		if (synonims != null && !synonims.isEmpty()) {
			for (String s : synonims) {
				child.addChild(new AssociationsGraphNode(s));
			}
		}
		return child;
	}

	@Override
	public List<AssociationsGraphNode> getAssociations(List<String> keywords) {
		List<AssociationsGraphNode> list = new ArrayList<AssociationsGraphNode>();
		for (int i = 0; i < keywords.size(); i++) {
			AssociationsGraphNode child = getAssociations(keywords.get(i));
			list.add(child);
		}
		return list;
	}

	@Override
	public AssociationsGraphNode getCommonAssociations(List<String> keywords) {
		if (keywords != null && !keywords.isEmpty()) {
			AssociationsGraphNode ag = getAssociations(keywords.get(0));
			for (int i = 1; i < keywords.size(); i++) {
				AssociationsGraphNode child = getAssociations(keywords.get(i));
				ag.addChild(child);
			}
			return ag;
		}
		return new AssociationsGraphNode("NO RESULTS");
	}

}
