/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author scott
 */
@Configuration
public class JpaVendorAdapterConfig {
    
    @Value("${qotd.config.jpa.vendorType:MYSQL}")
    private String vendorType;
    
    @Value("${app.jpa.dialect:org.hibernate.dialect.MySQLDialect}")
    private String dbDialect;
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setDatabase(Database.valueOf(vendorType));
        va.setDatabasePlatform(dbDialect);
        va.setGenerateDdl(true);
        return va;
    }
}
