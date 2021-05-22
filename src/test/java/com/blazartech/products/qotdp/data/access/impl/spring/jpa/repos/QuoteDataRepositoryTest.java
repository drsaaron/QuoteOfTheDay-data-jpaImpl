/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.JpaVendorAdapterConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.config.TransactionManagerConfig;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteData;
import java.util.Collection;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    QuoteDataRepositoryTest.QuoteDataRepositoryTestConfiguration.class,
    TestEntityManagerConfiguration.class,
    TestDataSourceConfiguration.class,
    JpaVendorAdapterConfig.class,
    TransactionManagerConfig.class
})
@Transactional
public class QuoteDataRepositoryTest {
    
    private static final Logger logger = LoggerFactory.getLogger(QuoteDataRepositoryTest.class);
    
    @Configuration
    static class QuoteDataRepositoryTestConfiguration {
    }
    
    @Autowired
    private QuoteDataRepository instance;
    
    public QuoteDataRepositoryTest() {
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

    /**
     * Test of findByCanUse method, of class QuoteDataRepository.
     */
    @Test
    @Sql("/quoteTest.sql")
    public void testFindByCanUse() {
        logger.info("findByCanUse");
        String canUse = "Y";

        Collection<QuoteData> result = instance.findByCanUse(canUse);
        assertNotNull(result);
        assertEquals(3, result.size());
    }
    
}
