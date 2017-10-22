package com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-18T09:49:40")
@StaticMetamodel(QuoteOfTheDayData.class)
public class QuoteOfTheDayData_ { 

    public static volatile SingularAttribute<QuoteOfTheDayData, Integer> qotdNum;
    public static volatile SingularAttribute<QuoteOfTheDayData, QuoteData> quoteNum;
    public static volatile SingularAttribute<QuoteOfTheDayData, Date> quoteDate;

}