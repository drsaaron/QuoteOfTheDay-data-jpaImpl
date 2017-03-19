/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author scott
 */
@Entity
@Table(name = "Quote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuoteData.findAll", query = "SELECT q FROM QuoteData q")
    , @NamedQuery(name = "QuoteData.findByQuoteNum", query = "SELECT q FROM QuoteData q WHERE q.quoteNum = :quoteNum")
    , @NamedQuery(name = "QuoteData.findByCanUse", query = "SELECT q FROM QuoteData q WHERE q.canUse = :canUse")})
public class QuoteData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QuoteNum")
    private Integer quoteNum;
    @Basic(optional = false)
    @Lob
    @Column(name = "QuoteTxt")
    private String quoteTxt;
    @Basic(optional = false)
    @Column(name = "CanUse")
    private String canUse;
    @JoinColumn(name = "SrcCde", referencedColumnName = "SrcCde")
    @ManyToOne(optional = false)
    private SrcValData srcCde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quoteNum")
    private Collection<QuoteOfTheDayData> quoteOfTheDayCollection;

    public QuoteData() {
    }

    public QuoteData(Integer quoteNum) {
        this.quoteNum = quoteNum;
    }

    public QuoteData(Integer quoteNum, String quoteTxt, String canUse) {
        this.quoteNum = quoteNum;
        this.quoteTxt = quoteTxt;
        this.canUse = canUse;
    }

    public Integer getQuoteNum() {
        return quoteNum;
    }

    public void setQuoteNum(Integer quoteNum) {
        this.quoteNum = quoteNum;
    }

    public String getQuoteTxt() {
        return quoteTxt;
    }

    public void setQuoteTxt(String quoteTxt) {
        this.quoteTxt = quoteTxt;
    }

    public String getCanUse() {
        return canUse;
    }

    public void setCanUse(String canUse) {
        this.canUse = canUse;
    }

    public SrcValData getSrcCde() {
        return srcCde;
    }

    public void setSrcCde(SrcValData srcCde) {
        this.srcCde = srcCde;
    }

    @XmlTransient
    public Collection<QuoteOfTheDayData> getQuoteOfTheDayCollection() {
        return quoteOfTheDayCollection;
    }

    public void setQuoteOfTheDayCollection(Collection<QuoteOfTheDayData> quoteOfTheDayCollection) {
        this.quoteOfTheDayCollection = quoteOfTheDayCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quoteNum != null ? quoteNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuoteData)) {
            return false;
        }
        QuoteData other = (QuoteData) object;
        if ((this.quoteNum == null && other.quoteNum != null) || (this.quoteNum != null && !this.quoteNum.equals(other.quoteNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.Quote[ quoteNum=" + quoteNum + " ]";
    }
    
}
