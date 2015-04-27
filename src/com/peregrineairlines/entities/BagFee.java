/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "bag_fee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BagFee.findAll", query = "SELECT b FROM BagFee b"),
    @NamedQuery(name = "BagFee.findByBagFeeId", query = "SELECT b FROM BagFee b WHERE b.bagFeeId = :bagFeeId"),
    @NamedQuery(name = "BagFee.findByNumberOfBags", query = "SELECT b FROM BagFee b WHERE b.numberOfBags = :numberOfBags"),
    @NamedQuery(name = "BagFee.findByFee", query = "SELECT b FROM BagFee b WHERE b.fee = :fee")})
public class BagFee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bag_fee_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bagFeeId;
    @Basic(optional = false)
    @Column(name = "number_of_bags")
    private int numberOfBags;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "fee")
    private BigDecimal fee;
    @JoinColumn(name = "ticket", referencedColumnName = "ticket_id")
    @ManyToOne(optional = false)
    private Ticket ticket;

    public BagFee() {
    }

    public BagFee(Integer bagFeeId) {
        this.bagFeeId = bagFeeId;
    }

    public BagFee(Integer bagFeeId, int numberOfBags, BigDecimal fee) {
        this.bagFeeId = bagFeeId;
        this.numberOfBags = numberOfBags;
        this.fee = fee;
    }

    public Integer getBagFeeId() {
        return bagFeeId;
    }

    public void setBagFeeId(Integer bagFeeId) {
        this.bagFeeId = bagFeeId;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bagFeeId != null ? bagFeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BagFee)) {
            return false;
        }
        BagFee other = (BagFee) object;
        if ((this.bagFeeId == null && other.bagFeeId != null) || (this.bagFeeId != null && !this.bagFeeId.equals(other.bagFeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.BagFee[ bagFeeId=" + bagFeeId + " ]";
    }
    
}
