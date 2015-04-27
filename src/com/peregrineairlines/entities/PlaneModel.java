/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.entities;

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
 * @author Chris
 */
@Entity
@Table(name = "plane_model")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaneModel.findAll", query = "SELECT p FROM PlaneModel p"),
    @NamedQuery(name = "PlaneModel.findByPlaneModelId", query = "SELECT p FROM PlaneModel p WHERE p.planeModelId = :planeModelId"),
    @NamedQuery(name = "PlaneModel.findByName", query = "SELECT p FROM PlaneModel p WHERE p.name = :name")})
public class PlaneModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "plane_model_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planeModelId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planeModel", orphanRemoval = true)
    private Collection<PlaneModelSeat> planeModelSeatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planeModel", orphanRemoval = true)
    private Collection<Flight> flightCollection;

    public PlaneModel() {
    }

    public PlaneModel(Integer planeModelId) {
        this.planeModelId = planeModelId;
    }

    public PlaneModel(Integer planeModelId, String name) {
        this.planeModelId = planeModelId;
        this.name = name;
    }

    public Integer getPlaneModelId() {
        return planeModelId;
    }

    public void setPlaneModelId(Integer planeModelId) {
        this.planeModelId = planeModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<PlaneModelSeat> getPlaneModelSeatCollection() {
        return planeModelSeatCollection;
    }

    public void setPlaneModelSeatCollection(Collection<PlaneModelSeat> planeModelSeatCollection) {
        this.planeModelSeatCollection = planeModelSeatCollection;
    }

    @XmlTransient
    public Collection<Flight> getFlightCollection() {
        return flightCollection;
    }

    public void setFlightCollection(Collection<Flight> flightCollection) {
        this.flightCollection = flightCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeModelId != null ? planeModelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneModel)) {
            return false;
        }
        PlaneModel other = (PlaneModel) object;
        if ((this.planeModelId == null && other.planeModelId != null) || (this.planeModelId != null && !this.planeModelId.equals(other.planeModelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.PlaneModel[ planeModelId=" + planeModelId + " ]";
    }
    
}
