package org.example.service;

import org.example.dao.FlightDao;
import org.example.dto.FlightDto;
import org.example.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    public static final FlightService INSTANCE = new FlightService();
    public FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll(){
        List<Flight> flights = flightDao.findAll();

        return flights.stream()
                  .map(flight -> new FlightDto(flight.getId(),
                          "%s - %s -%s".formatted(flight.getDepartureDate(),
                                  flight.getArrivalDate(),
                                  flight.getStatus()))).toList();
    }

    private FlightService() {
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
