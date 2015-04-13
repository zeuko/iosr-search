package com.iosr.search.keywords;

import java.util.List;

import com.iosr.search.keywords.xml.TaggerXMLParser;

import eu.clarin_pl.ws.wsg.tagger.GetResultRequestType;
import eu.clarin_pl.ws.wsg.tagger.GetResultResponseType;
import eu.clarin_pl.ws.wsg.tagger.SendRequestRequestType;
import eu.clarin_pl.ws.wsg.tagger.SendRequestResponseType;
import eu.clarin_pl.ws.wsg.tagger.TaggerServiceLocator;
import eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType;

public class TaggerKeywordProvider implements KeywordsProvider {

	@Override
	public List<Keyword> getKeywords(String string) throws Exception {
		TaggerServiceLocator asLocator = new TaggerServiceLocator();
		TaggerServicePortType service = asLocator.getTaggerServicePort();
		SendRequestRequestType parameters = new SendRequestRequestType(string);
		SendRequestResponseType sendRequest = service.sendRequest(parameters);
		String token = sendRequest.getToken();
		Thread.sleep(1000);
		GetResultRequestType params = new GetResultRequestType(token);
		GetResultResponseType result = service.getResult(params);
		System.out.println(result.getXml());
		TaggerXMLParser parser = new TaggerXMLParser();
		return parser.extractKeywords(result.getXml());

	}

}
