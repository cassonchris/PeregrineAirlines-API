/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.model;

import com.peregrineairlines.entities.BagFee;
import com.peregrineairlines.entities.Flight;
import com.peregrineairlines.entities.PlaneModel;
import com.peregrineairlines.entities.TicketOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
     * This method must be called first in order to use the other methods in this class.
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
     * Detaches an entity object. If the object should be deleted, make sure orphanRemoval = true
     * @param object 
     */
    private static void detach(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.detach(object);
        em.getTransaction().commit();
        em.close();
    }
    
    static Collection<PlaneModel> getPlaneModels() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("PlaneModel.findAll");
        Collection<PlaneModel> results = query.getResultList();
        em.close();
        return results;
    }
    
    static Collection<Flight> searchFlights(String to, String from, Date depart, Date arrival) {
        // TODO
        return new ArrayList<>();
    }
    
    static Flight getFlightById(int flightId) {
        // TODO
        return new Flight();
    }
    
    static void insertTicketOrder(TicketOrder ticketOrder) {
        insert(ticketOrder);
    }
    
    static void returnTicket(int ticketNumber) {
        // TODO
    }
    
    static void insertBagFee(BagFee bagFee) {
        insert(bagFee);
    }
    
    static void markPassengerAsCheckedIn(int ticketNumber) {
        // TODO
    }
}
