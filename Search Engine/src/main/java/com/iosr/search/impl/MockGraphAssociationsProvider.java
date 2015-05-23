package com.iosr.search.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.iosr.search.AssociationsGraph;
import com.iosr.search.AssociationsGraphProviderInterface;


/**
 * Implementacja 'dummy'dla celow testowych.
 * @author Patrycja
 */
public class MockGraphAssociationsProvider implements AssociationsGraphProviderInterface {

	@Override
	public AssociationsGraph getAssociations(String keyword) {
		AssociationsGraph ga = new AssociationsGraph("wynajem");
		AssociationsGraph child1 = new AssociationsGraph("kupno");
		AssociationsGraph child2 = new AssociationsGraph("dzierzawa");
		AssociationsGraph child3 = new AssociationsGraph("mieszkanie");
		AssociationsGraph child4 = new AssociationsGraph("lokal");
		ga.setChildren(Lists.newArrayList(child1, child2, child3, child4));
		return ga;
	}

	@Override
	public List<AssociationsGraph> getAssociations(List<String> keywords) {
		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public AssociationsGraph getCommonAssociations(List<String> keywords) {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
}
