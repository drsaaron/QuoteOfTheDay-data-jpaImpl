/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scott
 */
@Entity
@Table(name = "QuoteOfTheDay")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuoteOfTheDayData.findAll", query = "SELECT q FROM QuoteOfTheDayData q")
    , @NamedQuery(name = "QuoteOfTheDayData.findByQotdNum", query = "SELECT q FROM QuoteOfTheDayData q WHERE q.qotdNum = :qotdNum")
    , @NamedQuery(name = "QuoteOfTheDayData.findByDateRangeAndQuoteNumber", query = "SELECT q FROM QuoteOfTheDayData q WHERE q.quoteNum.quoteNum = :quoteNumber and q.quoteDate between :startDate and :endDate")
    , @NamedQuery(name = "QuoteOfTheDayData.findByDateRange", query = "SELECT q FROM QuoteOfTheDayData q WHERE q.quoteDate between :startDate and :endDate")
})
public class QuoteOfTheDayData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QotdNum")
    private Integer qotdNum;
    @Basic(optional = false)
    @Column(name = "QuoteDate")
    @Temporal(TemporalType.DATE)
    private Date quoteDate;
    @JoinColumn(name = "QuoteNum", referencedColumnName = "QuoteNum")
    @ManyToOne(optional = false)
    private QuoteData quoteNum;

    public QuoteOfTheDayData() {
    }

    public QuoteOfTheDayData(Integer qotdNum) {
        this.qotdNum = qotdNum;
    }

    public QuoteOfTheDayData(Integer qotdNum, Date quoteDate) {
        this.qotdNum = qotdNum;
        this.quoteDate = quoteDate;
    }

    public Integer getQotdNum() {
        return qotdNum;
    }

    public void setQotdNum(Integer qotdNum) {
        this.qotdNum = qotdNum;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public QuoteData getQuoteNum() {
        return quoteNum;
    }

    public void setQuoteNum(QuoteData quoteNum) {
        this.quoteNum = quoteNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qotdNum != null ? qotdNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuoteOfTheDayData)) {
            return false;
        }
        QuoteOfTheDayData other = (QuoteOfTheDayData) object;
        if ((this.qotdNum == null && other.qotdNum != null) || (this.qotdNum != null && !this.qotdNum.equals(other.qotdNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteOfTheDay[ qotdNum=" + qotdNum + " ]";
    }

}
