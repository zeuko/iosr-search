package com.iosr.search.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.iosr.search.associations.database.SynonymService;


public class SynonymServiceTest {

	SynonymService sut;

	@Before
	public void setUp() throws Exception {
		sut = new SynonymService();
		sut.afterPropertiesSet();
		sut.setDatabaseUrl("jdbc:sqlite:/var/iosr/base.dat");
	}

	@Test
	public void testExample() throws Exception {
		List<String> actual = sut.getSynonyms("koral");
		List<String> expected = Arrays.asList("koralowiec", "koral", "rafa koralowa");
		assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
	}

	@Test
	public void testExampleWithPolishDiacritics() throws Exception {
		List<String> actual = sut.getSynonyms("koral");
		List<String> expected = Arrays.asList("koralowiec", "koral", "rafa koralowa");
		assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
	}

}
