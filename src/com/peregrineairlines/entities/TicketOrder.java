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
@Table(name = "ticket_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketOrder.findAll", query = "SELECT t FROM TicketOrder t"),
    @NamedQuery(name = "TicketOrder.findByTicketOrderId", query = "SELECT t FROM TicketOrder t WHERE t.ticketOrderId = :ticketOrderId")})
public class TicketOrder implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketOrder")
    private Collection<TicketReturn> ticketReturnCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_order_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketOrderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketOrder", orphanRemoval = true)
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    @ManyToOne(optional = false)
    private Customer customer;

    public TicketOrder() {
    }

    public TicketOrder(Integer ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
    }

    public Integer getTicketOrderId() {
        return ticketOrderId;
    }

    public void setTicketOrderId(Integer ticketOrderId) {
        this.ticketOrderId = ticketOrderId;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }
    
    public void addTicket(Ticket ticket) {
        ticketCollection.add(ticket);
        ticket.setTicketOrder(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketOrderId != null ? ticketOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketOrder)) {
            return false;
        }
        TicketOrder other = (TicketOrder) object;
        if ((this.ticketOrderId == null && other.ticketOrderId != null) || (this.ticketOrderId != null && !this.ticketOrderId.equals(other.ticketOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.TicketOrder[ ticketOrderId=" + ticketOrderId + " ]";
    }

    @XmlTransient
    public Collection<TicketReturn> getTicketReturnCollection() {
        return ticketReturnCollection;
    }

    public void setTicketReturnCollection(Collection<TicketReturn> ticketReturnCollection) {
        this.ticketReturnCollection = ticketReturnCollection;
    }
    
}
