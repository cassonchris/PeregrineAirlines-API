/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.model;

import com.peregrineairlines.entities.Airport;
import com.peregrineairlines.entities.BagFee;
import com.peregrineairlines.entities.Customer;
import com.peregrineairlines.entities.Flight;
import com.peregrineairlines.entities.PlaneModel;
import com.peregrineairlines.entities.Ticket;
import com.peregrineairlines.entities.TicketOrder;
import com.peregrineairlines.entities.TicketReturn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Chris
 */
public class PADA {

    private static EntityManagerFactory emf;

    /**
     * This method must be called first in order to use the other methods in
     * this class.
     */
    static void open() {
        emf = Persistence.createEntityManagerFactory("PeregrineAirlines-APIPU");
    }

    /**
     * CALL THIS WHEN YOU'RE FINISHED!!!
     */
    static void close() {
        emf.close();
    }

    /**
     * Inserts an entity object
     *
     * @param object
     */
    private static void insert(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Updates an entity object
     *
     * @param object
     */
    private static void update(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Detaches an entity object. If the object should be deleted, make sure
     * orphanRemoval = true
     *
     * @param object
     */
    private static void detach(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.detach(object);
        em.getTransaction().commit();
        em.close();
    }

    static Collection<Airport> getAirports() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Airport.findAll");
        Collection<Airport> results = query.getResultList();
        em.close();
        return results;
    }
    
    static Airport getAirportById(int airportId) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Airport.findByAirportId");
        query.setParameter("airportId", airportId);
        query.setMaxResults(1);
        List<Airport> results = query.getResultList();
        em.close();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    static Collection<PlaneModel> getPlaneModels() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("PlaneModel.findAll");
        Collection<PlaneModel> results = query.getResultList();
        em.close();
        return results;
    }
    
    static PlaneModel getPlaneModelById(int planeModelId) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("PlaneModel.findByPlaneModelId");
        query.setParameter("planeModelId", planeModelId);
        query.setMaxResults(1);
        List<PlaneModel> results = query.getResultList();
        em.close();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    static Collection<Flight> searchFlights(int arrivingAirportId, int departingAirportId, Date depart, int passengers) {
        EntityManager em = emf.createEntityManager();
        Calendar toDate = Calendar.getInstance();
        toDate.setTime(depart);
        toDate.add(Calendar.DATE, 1);
        Query query = em.createNamedQuery("Flight.findByFlightDetails")
                .setParameter("departingAirport", departingAirportId)
                .setParameter("arrivingAirport", arrivingAirportId)
                .setParameter("from", depart)
                .setParameter("to", toDate.getTime())
                .setParameter("passengers", passengers);
        Collection<Flight> results = query.getResultList();
        em.close();
        return results;
    }

    static Flight getFlightById(int flightId) {
        // TODO
        return new Flight();
    }
    
    static void insertFlight(Flight flight) {
        insert(flight);
    }

    static void insertTicketOrder(TicketOrder ticketOrder) {
        insert(ticketOrder);
    }
    
    static Ticket getAvailableTicketByFlightId(int flightId) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Ticket.findAvailableTicketByFlight");
        query.setParameter("flightId", flightId);
        query.setMaxResults(1);
        List<Ticket> results = query.getResultList();
        em.close();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    static Collection<Ticket> getAvailableTicketsByFlightId(int flightId, int numberOfTickets) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Ticket.findAvailableTicketByFlight");
        query.setParameter("flightId", flightId);
        query.setMaxResults(numberOfTickets);
        List<Ticket> results = query.getResultList();
        em.close();
        return results;
    }

    static Ticket getTicketById(int ticketId) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Ticket.findByTicketId");
        query.setParameter("ticketId", ticketId);
        List<Ticket> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    static void insertTicketReturn(TicketReturn ticketReturn) {
        insert(ticketReturn);
    }

    static void insertBagFee(BagFee bagFee) {
        insert(bagFee);
    }

    static void markPassengerAsCheckedIn(int ticketNumber) {
        // TODO
    }
    
    static Customer getCustomerByFirstAndLast(String first, String last) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Customer.findByFirstAndLast");
        query.setParameter("firstname", first);
        query.setParameter("lastname", last);
        List<Customer> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    static void insertCustomer(Customer customer) {
        insert(customer);
    }
    
    static void updateTicket(Ticket ticket) {
        update(ticket);
    }
}
