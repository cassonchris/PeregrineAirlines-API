/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.entities;

import java.io.Serializable;
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
@Table(name = "ticket_return")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketReturn.findAll", query = "SELECT t FROM TicketReturn t"),
    @NamedQuery(name = "TicketReturn.findByTicketReturnId", query = "SELECT t FROM TicketReturn t WHERE t.ticketReturnId = :ticketReturnId"),
})
public class TicketReturn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_return_id", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketReturnId;
    @JoinColumn(name = "ticket_order", referencedColumnName = "ticket_order_id")
    @ManyToOne(optional = false)
    private TicketOrder ticketOrder;
    @JoinColumn(name = "ticket", referencedColumnName = "ticket_id")
    @ManyToOne(optional = false)
    private Ticket ticket;

    public TicketReturn() {
    }

    public TicketReturn(Integer ticketReturnId) {
        this.ticketReturnId = ticketReturnId;
    }

    public Integer getTicketReturnId() {
        return ticketReturnId;
    }

    public void setTicketReturnId(Integer ticketReturnId) {
        this.ticketReturnId = ticketReturnId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketReturnId != null ? ticketReturnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketReturn)) {
            return false;
        }
        TicketReturn other = (TicketReturn) object;
        if ((this.ticketReturnId == null && other.ticketReturnId != null) || (this.ticketReturnId != null && !this.ticketReturnId.equals(other.ticketReturnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peregrineairlines.entities.TicketReturn[ ticketReturnId=" + ticketReturnId + " ]";
    }

    public TicketOrder getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(TicketOrder ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
}
