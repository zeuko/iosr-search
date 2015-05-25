package com.iosr.search.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iosr.search.AssociationsGraph;
import com.iosr.search.AssociationsGraphProviderInterface;
import com.iosr.search.impl.MockGraphAssociationsProvider;

/**
 * Restowy serwis zwracajacy JSONa z drzewem skojarzen.
 * 
 * @author Patrycja
 */
@RestController
public class RestGraphController {

	// example host:port/search/rest/graph/x,y,z
	@RequestMapping(value="/rest/graph/{keywords}", method=RequestMethod.GET, produces = "application/json")
	public AssociationsGraph getGraphAssociationsData(@PathVariable String[] keywords) {
		AssociationsGraphProviderInterface gap = new MockGraphAssociationsProvider();
		return gap.getAssociations("");
	}
}
