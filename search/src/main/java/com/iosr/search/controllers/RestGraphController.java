package com.iosr.search.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iosr.search.associations.AssociationsEngine;
import com.iosr.search.associations.AssociationsGraphNode;
import com.iosr.search.associations.AssociationsEngineInterface;

/**
 * Restowy serwis zwracajacy JSONa z drzewem skojarzen.
 * 
 * @author Patrycja
 */
@RestController
public class RestGraphController {

	@Autowired
	AssociationsEngine associationsEngine;
	
	// example host:port/search/rest/graph/x,y,z
	@RequestMapping(value="/rest/graph/{keywords}", method=RequestMethod.GET, produces = "application/json")
	public AssociationsGraphNode getGraphAssociationsData(@PathVariable String[] keywords) {
		System.out.print("KEYWORDS" + Arrays.asList(keywords).toString());
		return associationsEngine.getCommonAssociations(Arrays.asList(keywords));
	}
	
}
