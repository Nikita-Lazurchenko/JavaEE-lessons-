package org.example.service;

import org.example.dao.TicketDao;
import org.example.dto.FlightDto;
import org.example.dto.TicketDto;

import java.util.List;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private TicketDao ticketDao = TicketDao.getInstance();

    public List<TicketDto> findAllByFlightId(int flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> new TicketDto(ticket.getId(),
                        ticket.getPassengerName(),
                        ticket.getSeatNumber())).toList();
    }


    private TicketService() {}

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
