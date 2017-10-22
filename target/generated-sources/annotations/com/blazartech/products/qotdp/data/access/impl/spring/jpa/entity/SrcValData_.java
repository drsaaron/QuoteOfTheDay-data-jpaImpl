package com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-18T09:49:40")
@StaticMetamodel(SrcValData.class)
public class SrcValData_ { 

    public static volatile SingularAttribute<SrcValData, Integer> srcCde;
    public static volatile CollectionAttribute<SrcValData, QuoteData> quoteCollection;
    public static volatile SingularAttribute<SrcValData, String> srcTxt;

}