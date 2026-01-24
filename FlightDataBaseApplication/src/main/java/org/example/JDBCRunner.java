package org.example;

import org.example.dao.TicketDao;
import org.example.dto.TicketFilter;
import org.example.entity.Ticket;

import java.util.Optional;


public class JDBCRunner {
    public static void main(String[] args) {
        TicketDao ticketDao = TicketDao.getInstance();
        TicketFilter ticketFilter = new TicketFilter("Alexander Duggan", 5 , 15,0);
//        Ticket ticket = new Ticket();
//        ticket.setPassportNumber("AB7654321");
//        ticket.setPassengerName("Alexander Duggan");
//        ticket.setFlightId(3);
//        ticket.setSeatNumber(45);
//        ticket.setCost(7000);

//        System.out.println(ticketDao.save(ticket));
//        System.out.println(ticketDao.delete(9));
//        Ticket ticket = ticketDao.findById(2).get();
//        System.out.println(ticket);
//        ticket.setSeatNumber(5);
//        System.out.println(ticketDao.update(ticket));
//        System.out.println(ticketDao.findById(2).get());
        System.out.println(ticketDao.findAll());
    }

}
