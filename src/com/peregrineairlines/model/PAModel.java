/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.model;

import com.peregrineairlines.entities.Flight;
import com.peregrineairlines.entities.PlaneModel;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Chris
 */
public class PAModel {
    
    public static void open() {
        PADA.open();
    }
    
    public static void close() {
        PADA.close();
    }
    
    public static Collection<PlaneModel> getPlaneModels() {
        return PADA.getPlaneModels();
    }
    
    public static void checkIn(int ticketNumber) {
        // TODO
    }
    
    public static Collection<Flight> searchFlights(String to, String from, Date depart, Date arrival) {
        return PADA.searchFlights(to, from, depart, arrival);
    }
    
    public static Flight getFlightById(int flightId) {
        return PADA.getFlightById(flightId);
    }
    
    public static void changeFlight(int oldTicketNumber, int newFlightId, String firstName, String lastName, int ticketCount) {
        PADA.returnTicket(oldTicketNumber);
        // TODO
    }
    
    public static void submitOrder(int flightId, String firstName, String lastName, int ticketCount) {
        // TODO
    }
}
