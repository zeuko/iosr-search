package com.iosr.search.keywords.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.iosr.search.keywords.Keyword;

public class TaggerXMLParser {

	public List<Keyword> extractKeywords(String xmlString) {
		
		String xml = preprocessXML(xmlString);
		Document xmlFile = getXmlDocument(xml);
		
		List<Keyword> list = new ArrayList<Keyword>();
		
		NodeList tokens = xmlFile.getElementsByTagName("tok");
		for (int i = 0; i < tokens.getLength(); i++) {
			Element item = (Element) tokens.item(i);

			String orth = getTagContent(item, "orth"); 
			String base = getTagContent(item, "base"); 
			Keyword keyword = new Keyword();
			keyword.setWord(orth);
			keyword.setBaseWord(base);
			list.add(keyword);
		}

		return list;
	}

	private String getTagContent(Element item, String string) {
		NodeList nodeList = item.getElementsByTagName(string);
		Node node = nodeList.item(0);
		String content = node.getTextContent();
		return content;
	}

	private String preprocessXML(String xmlString) {
		String newXML = xmlString.replaceAll("\n", "");
		return newXML;
	}

	private Document getXmlDocument(String xml) {
		xml = xml.replace("ccl.dtd", "http://localhost:8080/search/resources/view/ccl.dtd");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(false);
		Document dom = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			dom = db.parse(is);
			dom.getDocumentElement().normalize();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return dom;
	}
}
