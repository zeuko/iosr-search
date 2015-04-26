package com.iosr.search;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Zwracana przez RestGraphController jako Json. Na dole w komentarzu znajduje
 * sie przykladowy Json oczekiwany przez javascript.
 * 
 * 
 * @author Patrycja
 */
public class GraphAssociations {

	private String name;
	private List<GraphAssociations> children = Lists.newArrayList();

	public GraphAssociations(String name) {
		this.name = name;
	}

	public GraphAssociations(String name, List<GraphAssociations> children) {
		super();
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GraphAssociations> getChildren() {
		return children;
	}

	public void setChildren(List<GraphAssociations> children) {
		this.children = children;
	}

	public void addChild(GraphAssociations child) {
		this.children.add(child);
	}

}

/*
 * var exampleData = { "name":"rootNode", "children":[ { "name":"child1",
 * "children":[ { "name":"child11", "children":[ { "name":"child111" } ] }, {
 * "name":"child12" }, { "name":"child13" } ] }, { "name":"child2", "children":[
 * { "name":"child21", "children":[ { "name":"child211" } ] }, {
 * "name":"child22" } ] }, { "name":"child3", "children":[ { "name":"child31" }
 * ] } ] }
 */