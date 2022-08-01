/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.QuoteSourceCode;
import java.util.Comparator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
public class SourceCodeComparatorConfiguration  {

    @Bean
    public Comparator<QuoteSourceCode> sourceCodeComparator() {
        return (t1, t2) -> t1.getText().compareTo(t2.getText());
    }
    
}
