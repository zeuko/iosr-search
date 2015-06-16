package com.iosr.search.associations;

import java.util.List;

import com.google.common.collect.Lists;


/**
 * Mock implementation for test purpose.
 * @author Patrycja
 */
public class MockGraphAssociationsProvider implements AssociationsEngineInterface {

	@Override
	public AssociationsGraphNode getAssociations(String keyword) {
		AssociationsGraphNode ga = new AssociationsGraphNode("wynajem");
		AssociationsGraphNode child1 = new AssociationsGraphNode("kupno");
		AssociationsGraphNode child2 = new AssociationsGraphNode("dzierzawa");
		AssociationsGraphNode child3 = new AssociationsGraphNode("mieszkanie");
		AssociationsGraphNode child4 = new AssociationsGraphNode("lokal");
		ga.setChildren(Lists.newArrayList(child1, child2, child3, child4));
		return ga;
	}

	@Override
	public List<AssociationsGraphNode> getAssociations(List<String> keywords) {
		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public AssociationsGraphNode getCommonAssociations(List<String> keywords) {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
}
