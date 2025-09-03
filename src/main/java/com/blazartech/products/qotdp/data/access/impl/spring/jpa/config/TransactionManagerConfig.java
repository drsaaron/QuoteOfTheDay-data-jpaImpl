/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author scott
 */
@Configuration
public class TransactionManagerConfig {
    
    @Autowired
    private EntityManagerFactory emf;
    
    @Bean
    public PlatformTransactionManager qotdTransactionManager() {
        JpaTransactionManager m = new JpaTransactionManager(emf);
        return m;
    }
}
