/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.JpaVendorAdapterConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.TransactionManagerConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteOfTheDayData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    QuoteOfTheDayDataRepositoryTest.QuoteOfTheDayDataRepositoryTestConfiguration.class,
    TestEntityManagerConfiguration.class,
    TestDataSourceConfiguration.class,
    JpaVendorAdapterConfig.class,
    TransactionManagerConfig.class
})
@Transactional
public class QuoteOfTheDayDataRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(QuoteOfTheDayDataRepositoryTest.class);

    @Configuration
    @PropertySource("classpath:test.properties")
    static class QuoteOfTheDayDataRepositoryTestConfiguration {

        
    }

    @Autowired
    private QuoteOfTheDayDataRepository instance;

    public QuoteOfTheDayDataRepositoryTest() {
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

    private Date parseDate(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(d);
        } catch (ParseException p) {
            throw new RuntimeException("error parsing date: " + p.getMessage(), p);
        }
    }

    /**
     * Test of findByQuoteDate method, of class QuoteOfTheDayDataRepository.
     */
    /**
     * the test is commented out.  I cannot figure out how to have the H2 create
     * the tables automatically.  If I have the create statements in the SQL file,
     * which I shouldn't, I can run one test but not two.  So comment this out 
     * until I cna figure out how to have the system automatically create the
     * tables.
     */
   // @Test
    @Sql("/quoteOfTheDayTest.sql")
    public void testFindByQuoteDate() {
        logger.info("findByQuoteDate");

        Date quoteDate = parseDate("2021-01-01");

        QuoteOfTheDayData result = instance.findByQuoteDate(quoteDate);

        assertNotNull(result);
        assertEquals(1, result.getQuoteNum());
    }

    /**
     * Test of findByDateRange method, of class QuoteOfTheDayDataRepository.
     */
    @Test
    @Sql("/quoteOfTheDayTest.sql")
    public void testFindByDateRange() {
        logger.info("findByDateRange");

        Date startDate = parseDate("2020-01-01");
        Date endDate = parseDate("2020-01-03");

        Collection<QuoteOfTheDayData> result = instance.findByDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(3, result.size());
    }
}
