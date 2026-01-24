package org.example.entity;

import java.util.Objects;

public class Ticket {
    private Integer id;
    private String passportNumber;
    private String passengerName;
    private Flight flight;
    private Integer seatNumber;
    private Integer cost;

    public Ticket(Integer id, String passportNumber, String passengerName, Flight flight, Integer seatNumber, Integer cost) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.passengerName = passengerName;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.cost = cost;
    }

    public Ticket() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Flight getFlight() {
        return flight;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
               "id=" + id +
               ", passportNumber='" + passportNumber + '\'' +
               ", passengerName='" + passengerName + '\'' +
               ", flight=" + flight +
               ", seatNumber=" + seatNumber +
               ", cost=" + cost +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(passportNumber, ticket.passportNumber) && Objects.equals(passengerName, ticket.passengerName) && Objects.equals(flight, ticket.flight) && Objects.equals(seatNumber, ticket.seatNumber) && Objects.equals(cost, ticket.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportNumber, passengerName, flight, seatNumber, cost);
    }
}
