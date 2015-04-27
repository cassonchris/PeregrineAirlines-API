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
@Table(name = "plane_model_seat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaneModelSeat.findAll", query = "SELECT p FROM PlaneModelSeat p"),
    @NamedQuery(name = "PlaneModelSeat.findByPlaneModelSeatId", query = "SELECT p FROM PlaneModelSeat p WHERE p.planeModelSeatId = :planeModelSeatId"),
    @NamedQuery(name = "PlaneModelSeat.findBySeat", query = "SELECT p FROM PlaneModelSeat p WHERE p.seat = :seat")})
public class PlaneModelSeat implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price_per_mile")
    private BigDecimal pricePerMile;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "plane_model_seat_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planeModelSeatId;
    @Basic(optional = false)
    @Column(name = "seat")
    private String seat;
    @JoinColumn(name = "plane_model", referencedColumnName = "plane_model_id")
    @ManyToOne(optional = false)
    private PlaneModel planeModel;

    public PlaneModelSeat() {
    }

    public PlaneModelSeat(Integer planeModelSeatId) {
        this.planeModelSeatId = planeModelSeatId;
    }

    public PlaneModelSeat(Integer planeModelSeatId, String seat) {
        this.planeModelSeatId = planeModelSeatId;
        this.seat = seat;
    }

    public Integer getPlaneModelSeatId() {
        return planeModelSeatId;
    }

    public void setPlaneModelSeatId(Integer planeModelSeatId) {
        this.planeModelSeatId = planeModelSeatId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeModelSeatId != null ? planeModelSeatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneModelSeat)) {
            return false;
        }
        PlaneModelSeat other = (PlaneModelSeat) object;
        if ((this.planeModelSeatId == null && other.planeModelSeatId != null) || (this.planeModelSeatId != null && !this.planeModelSeatId.equals(other.planeModelSeatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.PlaneModelSeat[ planeModelSeatId=" + planeModelSeatId + " ]";
    }

    public BigDecimal getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(BigDecimal pricePerMile) {
        this.pricePerMile = pricePerMile;
    }
    
}
