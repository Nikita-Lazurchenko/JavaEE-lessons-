package org.example.dto;

public record TicketFilter(String passengerName,
                           Integer seatNumber,
                           int limit,
                           int offset) {
}
