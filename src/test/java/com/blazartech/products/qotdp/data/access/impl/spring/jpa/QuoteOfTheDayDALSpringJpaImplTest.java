/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.QuoteSourceCode;
import com.blazartech.products.qotdp.data.access.QuoteOfTheDayDAL;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.JpaVendorAdapterConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.TransactionManagerConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteOfTheDayDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.SrcValDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.TestDataSourceConfiguration;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.TestEntityManagerConfiguration;
import com.blazartech.products.qotdp.data.config.CacheConfiguration;
import java.util.Collection;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    QuoteOfTheDayDALSpringJpaImplTest.QuoteOfTheDayDALSpringJpaImplTestConfiguration.class,
    TestEntityManagerConfiguration.class,
    TestDataSourceConfiguration.class,
    JpaVendorAdapterConfig.class,
    TransactionManagerConfig.class,
    CacheConfiguration.class,
    SourceCodeComparatorConfiguration.class
})
@Transactional
public class QuoteOfTheDayDALSpringJpaImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(QuoteOfTheDayDALSpringJpaImplTest.class);
    
    @Configuration
    static class QuoteOfTheDayDALSpringJpaImplTestConfiguration {
        
        @Bean
        public QuoteOfTheDayDALSpringJpaImpl instance() {
            return new QuoteOfTheDayDALSpringJpaImpl();
        }
   
    }
    
    @Autowired
    private QuoteOfTheDayDAL instance;
    
    @Autowired
    private QuoteDataRepository quoteDataRepository;

    @Autowired
    private QuoteOfTheDayDataRepository quoteOfTheDayDataRepository;

    @Autowired
    private SrcValDataRepository srcValDataRepository;
    
    public QuoteOfTheDayDALSpringJpaImplTest() {
    }
    
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
    
    private static final String INIT_QUOTE_TEXT = "Initial";
    private static final String FINAL_QUOTE_TEXT = "Updated";
    
    @Test
    @Sql("/dalTest.sql")
    public void test_updateQuote() {
        logger.info("test_updateQuote");
        
        // create a random quote
        Quote q = new Quote();
        q.setSourceCode(1);
        q.setUsable(true);
        q.setText(INIT_QUOTE_TEXT);
        logger.info("added quote " + q.getNumber());
        instance.addQuote(q);
        
        // retrieve what we just created to get it in the cache
        q = instance.getQuote(q.getNumber());
        assertEquals(INIT_QUOTE_TEXT, q.getText());

        // update it
        q.setText(FINAL_QUOTE_TEXT);
        instance.updateQuote(q);

        // read it again.  The update should have cleared the cache so we'll now
        // get the updated value.
        q = instance.getQuote(q.getNumber());
        assertEquals(FINAL_QUOTE_TEXT, q.getText());
    }
    
    @Test
    @Sql("/dalTest.sql")
    public void testGetQuoteSourceCodes() {
        logger.info("testGetQuoteSourceCodes");
        
        Collection<QuoteSourceCode> sourceCodes = instance.getQuoteSourceCodes();
        assertNotNull(sourceCodes);
        assertTrue(sourceCodes.size() > 0);
        sourceCodes.forEach(c -> logger.info("source code " + c.getText()));
        
        // ensure the sorting is as expected.  The third one should be moved up 
        // to the front of the list.
        QuoteSourceCode first = sourceCodes.iterator().next();
        assertEquals(3, first.getNumber());
    }
    
    @Test
    @Sql("/dalTest.sql")
    public void testGetQuotesForSourceCode() {
        logger.info("testGetQuotesForSourceCode");
                
        Collection<Quote> quotes = instance.getQuotesForSourceCode(2);
        assertNotNull(quotes);
        assertFalse(quotes.isEmpty());
        
        // test sorting, quote #1 having been inserted second should show up first
        Quote firstQuote = quotes.iterator().next();
        assertEquals(10, firstQuote.getNumber());
    }
}
