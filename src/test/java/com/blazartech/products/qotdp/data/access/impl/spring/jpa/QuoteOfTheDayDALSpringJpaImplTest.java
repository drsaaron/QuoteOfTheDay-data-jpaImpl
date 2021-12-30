/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.access.QuoteOfTheDayDAL;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.JpaVendorAdapterConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.TransactionManagerConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteOfTheDayDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.SrcValDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.TestDataSourceConfiguration;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.TestEntityManagerConfiguration;
import com.blazartech.products.qotdp.data.config.CacheConfiguration;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    CacheConfiguration.class
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
        
        @Bean
        public SourceCodeComparator sourceCodeComparator() {
            return new SourceCodeComparator();
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
    private static final int QUOTE_NUM = 100;
    
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
}
