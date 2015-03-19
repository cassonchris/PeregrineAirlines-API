/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f"),
    @NamedQuery(name = "Flight.findByFlightId", query = "SELECT f FROM Flight f WHERE f.flightId = :flightId"),
    @NamedQuery(name = "Flight.findByFlightDatetime", query = "SELECT f FROM Flight f WHERE f.flightDatetime = :flightDatetime")})
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "flight_id")
    private Integer flightId;
    @Basic(optional = false)
    @Column(name = "flight_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flightDatetime;
    @JoinColumn(name = "plane_model", referencedColumnName = "plane_model_id")
    @ManyToOne(optional = false)
    private PlaneModel planeModel;
    @JoinColumn(name = "departing_airport", referencedColumnName = "airport_id")
    @ManyToOne(optional = false)
    private Airport departingAirport;
    @JoinColumn(name = "arriving_airport", referencedColumnName = "airport_id")
    @ManyToOne(optional = false)
    private Airport arrivingAirport;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight", orphanRemoval = true)
    private Collection<Ticket> ticketCollection;

    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Flight(Integer flightId, Date flightDatetime) {
        this.flightId = flightId;
        this.flightDatetime = flightDatetime;
    }
    
    public Flight(Date flightDatetime, PlaneModel planeModel, Airport departingAirport, Airport arrivingAirport) {
        this.flightDatetime = flightDatetime;
        this.planeModel = planeModel;
        this.departingAirport = departingAirport;
        this.arrivingAirport = arrivingAirport;
    }
    
    /**
     * This method is intended for adding fligts that aren't already in the system.
     * @param flightDatetime
     * @param planeModel
     * @param departingAirport
     * @param arrivingAirport
     * @return 
     */
    public static Flight createFlight(Date flightDatetime, PlaneModel planeModel, Airport departingAirport, Airport arrivingAirport) {
        Flight flight = new Flight(flightDatetime, planeModel, departingAirport, arrivingAirport);
        flight.createAndSetTicketCollection();
        return flight;
    }
    
    private void createAndSetTicketCollection() {
        Collection<Ticket> tickets = new ArrayList<>();
        for (PlaneModelSeat seat : planeModel.getPlaneModelSeatCollection()) {
            // TODO
        }
        this.ticketCollection = tickets;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Date getFlightDatetime() {
        return flightDatetime;
    }

    public void setFlightDatetime(Date flightDatetime) {
        this.flightDatetime = flightDatetime;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public Airport getDepartingAirport() {
        return departingAirport;
    }

    public void setDepartingAirport(Airport departingAirport) {
        this.departingAirport = departingAirport;
    }

    public Airport getArrivingAirport() {
        return arrivingAirport;
    }

    public void setArrivingAirport(Airport arrivingAirport) {
        this.arrivingAirport = arrivingAirport;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.Flight[ flightId=" + flightId + " ]";
    }
    
}
