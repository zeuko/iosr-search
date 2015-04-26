package com.iosr.search.impl;

import com.google.common.collect.Lists;
import com.iosr.search.GraphAssociations;
import com.iosr.search.GraphAssociationsProviderInterface;

public class MockGraphAssociationsProvider implements GraphAssociationsProviderInterface {

	@Override
	public GraphAssociations getAssociations() {
		GraphAssociations ga = new GraphAssociations("wynajem");
		GraphAssociations child1 = new GraphAssociations("kupno");
		GraphAssociations child2 = new GraphAssociations("dzier¿awa");
		GraphAssociations child3 = new GraphAssociations("mieszkanie");
		GraphAssociations child4 = new GraphAssociations("lokal");
		ga.setChildren(Lists.newArrayList(child1, child2, child3, child4));
		return ga;
	}
	
}
