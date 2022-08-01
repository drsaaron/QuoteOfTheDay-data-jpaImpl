/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.QuoteSourceCode;
import java.util.Comparator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    SourceCodeComparatorTest.SourceCodeComparatorTestConfiguration.class,
    SourceCodeComparatorConfiguration.class
})
public class SourceCodeComparatorTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SourceCodeComparatorTest.class);
    
    @Configuration
    static class SourceCodeComparatorTestConfiguration {
        
    }
    
    @Autowired
    private Comparator<QuoteSourceCode> instance;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testCompare_less() {
        logger.info("testCompare_less");
        
        QuoteSourceCode c1 = new QuoteSourceCode();
        c1.setText("first text");
        QuoteSourceCode c2 = new QuoteSourceCode();
        c2.setText("second text");
        
        int result = instance.compare(c1, c2);
        assertTrue(result < 0);
    }
    
    @Test
    public void testCompare_greater() {
        logger.info("testCompare_greater");
        
        QuoteSourceCode c2 = new QuoteSourceCode();
        c2.setText("first text");
        QuoteSourceCode c1 = new QuoteSourceCode();
        c1.setText("second text");
        
        int result = instance.compare(c1, c2);
        assertTrue(result > 0);
    }
    
    @Test
    public void testCompare_equal() {
        logger.info("testCompare_equal");
        
        QuoteSourceCode c2 = new QuoteSourceCode();
        c2.setText("first text");
        QuoteSourceCode c1 = new QuoteSourceCode();
        c1.setText(c2.getText());
        
        int result = instance.compare(c1, c2);
        assertEquals(0, result);
    }
}
