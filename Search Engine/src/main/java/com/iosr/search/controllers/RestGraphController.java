package com.iosr.search.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iosr.search.GraphAssociations;
import com.iosr.search.GraphAssociationsProviderInterface;
import com.iosr.search.impl.MockGraphAssociationsProvider;


@RestController
public class RestGraphController {

	// example host:port/search/rest/graph/x,y,z
	@RequestMapping(value="/rest/graph/{keywords}", method=RequestMethod.GET)
	public GraphAssociations getGraphAssociationsData(@PathVariable String[] keywords) {
		GraphAssociationsProviderInterface gap = new MockGraphAssociationsProvider();
		return gap.getAssociations();
	}
}
