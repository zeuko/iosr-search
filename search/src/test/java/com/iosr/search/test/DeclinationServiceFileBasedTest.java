package com.iosr.search.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iosr.search.keywords.DeclinationService;


public class DeclinationServiceFileBasedTest {

    DeclinationService sut;
    
    @Before
    public void setUp() throws Exception {
        sut = new DeclinationService();
        sut.setFilePath("odm-short.txt");
        sut.afterPropertiesSet();
    }

    @Test
    public void testGetBasicForm() throws Exception {
        String word1 = sut.getBasicForm("abadance");
        String word2 = sut.getBasicForm("abaty");
        String word3 = sut.getBasicForm("abietynowi");
        assertEquals("abadanka", word1);
        assertEquals("abat", word2);
        assertEquals("abietynowy", word3);
    }

    @Test
    public void testGetBasicFormWithPolishDiacritics() throws Exception {
        String word1 = sut.getBasicForm("abakańscy");
        String word2 = sut.getBasicForm("abażurku");
        String word3 = sut.getBasicForm("nieabidżańska");
        assertEquals("abakański", word1);
        assertEquals("abażurek", word2);
        assertEquals("abidżański", word3);
    }
    
    
}