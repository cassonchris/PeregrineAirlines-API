/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.model;

import com.peregrineairlines.entities.Airport;
import com.peregrineairlines.entities.Customer;
import com.peregrineairlines.entities.Flight;
import com.peregrineairlines.entities.PlaneModel;
import com.peregrineairlines.entities.Ticket;
import com.peregrineairlines.entities.TicketOrder;
import com.peregrineairlines.entities.TicketReturn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public static Collection<Airport> getAirports() {
        return PADA.getAirports();
    }

    public static Airport getAirportById(int airportId) {
        return PADA.getAirportById(airportId);
    }

    public static Collection<PlaneModel> getPlaneModels() {
        return PADA.getPlaneModels();
    }

    public static PlaneModel getPlaneModelById(int planeModelId) {
        return PADA.getPlaneModelById(planeModelId);
    }

    public static Ticket getAvailableTicketByFlightId(int flightId) {
        return PADA.getAvailableTicketByFlightId(flightId);
    }

    public static Collection<Ticket> getAvailableTicketsByFlightId(int flightId, int numberOfTickets) {
        return PADA.getAvailableTicketsByFlightId(flightId, numberOfTickets);
    }

    public static Ticket getTicketById(int ticketId) {
        return PADA.getTicketById(ticketId);
    }
    
    public static Ticket getTicketByIdAndPassengerLastname(int ticketId, String passengerLastname) {
        return PADA.getTicketByIdAndPassengerLastname(ticketId, passengerLastname);
    }

    public static void returnTicket(int ticketId) {
        Ticket ticket = getTicketById(ticketId);
        if (ticket != null) {
            TicketReturn ticketReturn = new TicketReturn();
            ticketReturn.setTicketOrder(ticket.getTicketOrder());
            ticketReturn.setTicket(ticket);
            insertTicketReturn(ticketReturn);
            
            ticket.setTicketOrder(null);
            ticket.setPassengerFirstname(null);
            ticket.setPassengerLastname(null);
            updateTicket(ticket);
        }
    }
    
    public static void insertTicketReturn(TicketReturn ticketReturn) {
        PADA.insertTicketReturn(ticketReturn);
    }

    public static Ticket checkIn(int ticketNumber) {
        Ticket ticket = getTicketById(ticketNumber);
        if (ticket != null) {
            ticket.setCheckedIn(Boolean.TRUE);
            updateTicket(ticket);
        }
        return ticket;
    }

    public static void scheduleFlight(int from, int to, int planeModelId, Date depart) {
        Airport fromAirport = getAirportById(from);
        Airport toAirport = getAirportById(to);
        PlaneModel planeModel = getPlaneModelById(planeModelId);

        Flight newFlight = Flight.createFlight(depart, planeModel, fromAirport, toAirport);
        insertFlight(newFlight);
    }

    public static Collection<Flight> searchFlights(int to, int from, Date depart, int passengers) {
        return PADA.searchFlights(to, from, depart, passengers);
    }

    public static Flight getFlightById(int flightId) {
        return PADA.getFlightById(flightId);
    }

    public static void insertFlight(Flight flight) {
        PADA.insertFlight(flight);
    }

    public static void changeFlight(int oldTicketNumber, int newFlightId, String firstName, String lastName, int ticketCount) {
        returnTicket(oldTicketNumber);
        // TODO
    }

    public static Customer getCustomerByFirstAndLast(String first, String last) {
        return PADA.getCustomerByFirstAndLast(first, last);
    }

    public static void insertCustomer(Customer customer) {
        PADA.insertCustomer(customer);
    }

    public static void insertTicketOrder(TicketOrder ticketOrder) {
        PADA.insertTicketOrder(ticketOrder);
    }

    public static void updateTicket(Ticket ticket) {
        PADA.updateTicket(ticket);
    }

    public static Collection<Ticket> submitOrder(Collection<Ticket> tickets, String customerFirstName, String customerLastName) {
        Customer customer = getCustomerByFirstAndLast(customerFirstName, customerLastName);
        if (customer == null) {
            customer = new Customer();
            customer.setFirstname(customerFirstName);
            customer.setLastname(customerLastName);
            insertCustomer(customer);
        }

        TicketOrder ticketOrder = new TicketOrder();
        customer.addTicketOrder(ticketOrder);
        insertTicketOrder(ticketOrder);

        List<Ticket> purchasedTickets = new ArrayList<>();

        for (Ticket placeholderTicket : tickets) {
            Ticket ticket = getTicketById(placeholderTicket.getTicketId());
            if (ticket != null) {
                ticket.setPassengerFirstname(placeholderTicket.getPassengerFirstname());
                ticket.setPassengerLastname(placeholderTicket.getPassengerLastname());
                ticketOrder.addTicket(ticket);
                updateTicket(ticket);
            }
            purchasedTickets.add(ticket);
        }

        return purchasedTickets;
    }
}
