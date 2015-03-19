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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByTicketId", query = "SELECT t FROM Ticket t WHERE t.ticketId = :ticketId"),
    @NamedQuery(name = "Ticket.findBySeat", query = "SELECT t FROM Ticket t WHERE t.seat = :seat"),
    @NamedQuery(name = "Ticket.findByPassengerFirstname", query = "SELECT t FROM Ticket t WHERE t.passengerFirstname = :passengerFirstname"),
    @NamedQuery(name = "Ticket.findByPassengerLastname", query = "SELECT t FROM Ticket t WHERE t.passengerLastname = :passengerLastname")})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Basic(optional = false)
    @Column(name = "seat")
    private String seat;
    @Column(name = "passenger_firstname")
    private String passengerFirstname;
    @Column(name = "passenger_lastname")
    private String passengerLastname;
    @JoinColumn(name = "ticket_order", referencedColumnName = "ticket_order_id")
    @ManyToOne(optional = false)
    private TicketOrder ticketOrder;
    @JoinColumn(name = "flight", referencedColumnName = "flight_id")
    @ManyToOne(optional = false)
    private Flight flight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", orphanRemoval = true)
    private Collection<BagFee> bagFeeCollection;

    public Ticket() {
    }

    public Ticket(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket(Integer ticketId, String seat) {
        this.ticketId = ticketId;
        this.seat = seat;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPassengerFirstname() {
        return passengerFirstname;
    }

    public void setPassengerFirstname(String passengerFirstname) {
        this.passengerFirstname = passengerFirstname;
    }

    public String getPassengerLastname() {
        return passengerLastname;
    }

    public void setPassengerLastname(String passengerLastname) {
        this.passengerLastname = passengerLastname;
    }

    public TicketOrder getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(TicketOrder ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketId != null ? ticketId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.ticketId == null && other.ticketId != null) || (this.ticketId != null && !this.ticketId.equals(other.ticketId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.Ticket[ ticketId=" + ticketId + " ]";
    }

    @XmlTransient
    public Collection<BagFee> getBagFeeCollection() {
        return bagFeeCollection;
    }

    public void setBagFeeCollection(Collection<BagFee> bagFeeCollection) {
        this.bagFeeCollection = bagFeeCollection;
    }
    
}
