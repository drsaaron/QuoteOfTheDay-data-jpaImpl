/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos;

import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteOfTheDayData;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author scott
 */
@Repository
public interface QuoteOfTheDayDataRepository extends JpaRepository<QuoteOfTheDayData, Integer>, JpaSpecificationExecutor<QuoteOfTheDayData> {
    
    QuoteOfTheDayData findByQuoteDate(LocalDate quoteDate);
    Collection<QuoteOfTheDayData> findByDateRangeAndQuoteNumber(@Param("quoteNumber") int quoteNumber, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    Collection<QuoteOfTheDayData> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    Collection<QuoteOfTheDayData> findByQuoteNum(@Param("quoteNum") int quoteNumber);
}
