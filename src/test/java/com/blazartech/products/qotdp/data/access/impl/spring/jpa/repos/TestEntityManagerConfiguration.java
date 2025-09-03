/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 *
 * @author scott
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos",
        transactionManagerRef = "qotdTransactionManager",
        entityManagerFactoryRef = "entityManagerFactory"
)
public class TestEntityManagerConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(dataSource);
        f.setPersistenceXmlLocation("classpath:META-INF/test-persistence.xml");
        f.setJpaVendorAdapter(jpaVendorAdapter);
        f.setPersistenceUnitName("test_com.blazartech_QuoteOfTheDay-data-jpaImpl_jar_1.0-SNAPSHOTPU");

        return f;
    }
}
