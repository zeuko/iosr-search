package com.iosr.search.associations;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * POJO class, prepared to be returned by RestGraphController as JSON. 
 * Represents tree structure of associations graph.
 * 
 * @author Patrycja
 */
public class AssociationsGraphNode {

	private String name;
	private List<AssociationsGraphNode> children = Lists.newArrayList();
	
	/** Indicates, whether this node is part of searched phrase, or just generated association **/
	private boolean isKeywordTreeNode = false;

	public AssociationsGraphNode(String name) {
		this.name = name;
	}

	public AssociationsGraphNode(String name, List<AssociationsGraphNode> children) {
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

	public List<AssociationsGraphNode> getChildren() {
		return children;
	}

	public void setChildren(List<AssociationsGraphNode> children) {
		this.children = children;
	}

	public void addChild(AssociationsGraphNode child) {
		this.children.add(child);
	}

	public boolean isKeywordTreeNode() {
		return isKeywordTreeNode;
	}

	public void setKeywordTreeNode(boolean isKeywordTreeNode) {
		this.isKeywordTreeNode = isKeywordTreeNode;
	}

}

