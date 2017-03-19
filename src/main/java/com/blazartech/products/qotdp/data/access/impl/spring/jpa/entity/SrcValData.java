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
@Table(name = "SrcVal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrcValData.findAll", query = "SELECT s FROM SrcValData s")
    , @NamedQuery(name = "SrcValData.findBySrcCde", query = "SELECT s FROM SrcValData s WHERE s.srcCde = :srcCde")
    , @NamedQuery(name = "SrcValData.findBySrcTxt", query = "SELECT s FROM SrcValData s WHERE s.srcTxt = :srcTxt")})
public class SrcValData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SrcCde")
    private Integer srcCde;
    @Basic(optional = false)
    @Column(name = "SrcTxt")
    private String srcTxt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srcCde")
    private Collection<QuoteData> quoteCollection;

    public SrcValData() {
    }

    public SrcValData(Integer srcCde) {
        this.srcCde = srcCde;
    }

    public SrcValData(Integer srcCde, String srcTxt) {
        this.srcCde = srcCde;
        this.srcTxt = srcTxt;
    }

    public Integer getSrcCde() {
        return srcCde;
    }

    public void setSrcCde(Integer srcCde) {
        this.srcCde = srcCde;
    }

    public String getSrcTxt() {
        return srcTxt;
    }

    public void setSrcTxt(String srcTxt) {
        this.srcTxt = srcTxt;
    }

    @XmlTransient
    public Collection<QuoteData> getQuoteCollection() {
        return quoteCollection;
    }

    public void setQuoteCollection(Collection<QuoteData> quoteCollection) {
        this.quoteCollection = quoteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (srcCde != null ? srcCde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrcValData)) {
            return false;
        }
        SrcValData other = (SrcValData) object;
        if ((this.srcCde == null && other.srcCde != null) || (this.srcCde != null && !this.srcCde.equals(other.srcCde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.SrcVal[ srcCde=" + srcCde + " ]";
    }
    
}
