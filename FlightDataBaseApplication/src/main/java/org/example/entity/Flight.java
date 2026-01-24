package org.example.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    public Integer id;
    public Integer flightNumber;
    public LocalDateTime departureDate;
    public Integer departureAirportCode;
    public LocalDateTime arrivalDate;
    public Integer arrivalAirportCode;
    public Integer aircraftId;
    public String status;

    public Flight(Integer id, Integer flightNumber, LocalDateTime departureDate, Integer departureAirportCode, LocalDateTime arrivalDate, Integer arrivalAirportCode, Integer aircraftId, String status) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public Integer getDepartureAirportCode() {
        return departureAirportCode;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public Integer getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureAirportCode(Integer departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalAirportCode(Integer arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", departureDate=" + departureDate +
                ", departureAirportCode=" + departureAirportCode +
                ", arrivalDate=" + arrivalDate +
                ", arrivalAirportCode=" + arrivalAirportCode +
                ", aircraftId=" + aircraftId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(departureAirportCode, flight.departureAirportCode) && Objects.equals(arrivalDate, flight.arrivalDate) && Objects.equals(arrivalAirportCode, flight.arrivalAirportCode) && Objects.equals(aircraftId, flight.aircraftId) && Objects.equals(status, flight.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureDate, departureAirportCode, arrivalDate, arrivalAirportCode, aircraftId, status);
    }
}
