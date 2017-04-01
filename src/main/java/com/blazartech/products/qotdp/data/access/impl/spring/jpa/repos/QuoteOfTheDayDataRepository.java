/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteOfTheDayData;
import java.util.Collection;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author scott
 */
@Repository
public interface QuoteOfTheDayDataRepository extends JpaRepository<QuoteOfTheDayData, Integer> {
    
    QuoteOfTheDayData findByQuoteDate(Date quoteDate);
    Collection<QuoteOfTheDayData> findByDateRange(@Param("quoteNumber") int quoteNumber, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
