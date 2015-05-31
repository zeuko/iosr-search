package com.iosr.search.keywords.impl;

import java.util.List;

import com.iosr.search.keywords.Keyword;
import com.iosr.search.keywords.KeywordsProviderInterface;
import com.iosr.search.keywords.xml.TaggerXMLParser;

import eu.clarin_pl.ws.wsg.tagger.CheckStatusRequestType;
import eu.clarin_pl.ws.wsg.tagger.CheckStatusResponseType;
import eu.clarin_pl.ws.wsg.tagger.GetResultRequestType;
import eu.clarin_pl.ws.wsg.tagger.GetResultResponseType;
import eu.clarin_pl.ws.wsg.tagger.SendRequestRequestType;
import eu.clarin_pl.ws.wsg.tagger.SendRequestResponseType;
import eu.clarin_pl.ws.wsg.tagger.TaggerServiceLocator;
import eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType;

public class TaggerKeywordProvider implements KeywordsProviderInterface {

	@Override
	public List<Keyword> getKeywords(String string) throws Exception {
		TaggerServiceLocator asLocator = new TaggerServiceLocator();
		TaggerServicePortType service = asLocator.getTaggerServicePort();
		SendRequestRequestType parameters = new SendRequestRequestType(string);
		SendRequestResponseType sendRequest = service.sendRequest(parameters);
		String token = sendRequest.getToken();
		CheckStatusRequestType status = new CheckStatusRequestType(token);
		CheckStatusResponseType checkStatus = service.checkStatus(status);
		
		while(!checkStatus.getStatus().equals("READY")) {
			checkStatus = service.checkStatus(status);
		}
		
		GetResultRequestType params = new GetResultRequestType(token);
		GetResultResponseType result = service.getResult(params);

		TaggerXMLParser parser = new TaggerXMLParser();
		return parser.extractKeywords(result.getXml());

	}

}
