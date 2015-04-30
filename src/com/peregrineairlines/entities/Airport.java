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
@Table(name = "airport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a ORDER BY a.city"),
    @NamedQuery(name = "Airport.findByAirportId", query = "SELECT a FROM Airport a WHERE a.airportId = :airportId"),
    @NamedQuery(name = "Airport.findByCity", query = "SELECT a FROM Airport a WHERE a.city = :city"),
    @NamedQuery(name = "Airport.findByLatitude", query = "SELECT a FROM Airport a WHERE a.latitude = :latitude"),
    @NamedQuery(name = "Airport.findByLongitude", query = "SELECT a FROM Airport a WHERE a.longitude = :longitude")})
public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "airport_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airportId;
    @Basic(optional = false)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @Column(name = "latitude")
    private int latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private int longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departingAirport", orphanRemoval = true)
    private Collection<Flight> departingFlightCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivingAirport", orphanRemoval = true)
    private Collection<Flight> arrivingFlightCollection;

    public Airport() {
    }

    public Airport(Integer airportId) {
        this.airportId = airportId;
    }

    public Airport(Integer airportId, String city, int latitude, int longitude) {
        this.airportId = airportId;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Collection<Flight> getDepartingFlightCollection() {
        return departingFlightCollection;
    }

    public void setDepartingFlightCollection(Collection<Flight> departingFlightCollection) {
        this.departingFlightCollection = departingFlightCollection;
    }

    @XmlTransient
    public Collection<Flight> getArrivingFlightCollection() {
        return arrivingFlightCollection;
    }

    public void setArrivingFlightCollection(Collection<Flight> arrivingFlightCollection) {
        this.arrivingFlightCollection = arrivingFlightCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airportId != null ? airportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.airportId == null && other.airportId != null) || (this.airportId != null && !this.airportId.equals(other.airportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.Airport[ airportId=" + airportId + " ]";
    }
    
}
