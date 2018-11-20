/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.QuoteSourceCode;
import java.util.Comparator;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class SourceCodeComparator implements Comparator<QuoteSourceCode> {

    @Override
    public int compare(QuoteSourceCode t, QuoteSourceCode t1) {
        return t.getText().compareTo(t1.getText());
    }
    
}
