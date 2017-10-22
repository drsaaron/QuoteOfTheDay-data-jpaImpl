package com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-18T09:49:40")
@StaticMetamodel(QuoteData.class)
public class QuoteData_ { 

    public static volatile SingularAttribute<QuoteData, SrcValData> srcCde;
    public static volatile SingularAttribute<QuoteData, Integer> quoteNum;
    public static volatile CollectionAttribute<QuoteData, QuoteOfTheDayData> quoteOfTheDayCollection;
    public static volatile SingularAttribute<QuoteData, String> quoteTxt;
    public static volatile SingularAttribute<QuoteData, String> canUse;

}