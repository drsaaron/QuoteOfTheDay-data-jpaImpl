/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteData;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author scott
 */
@Repository
public interface QuoteDataRepository extends JpaRepository<QuoteData, Integer>, JpaSpecificationExecutor<QuoteData> {
    
    Collection<QuoteData> findByCanUse(String canUse);
}
