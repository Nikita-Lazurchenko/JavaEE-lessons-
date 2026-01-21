package org.example;

import org.example.utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCRunner {
    public static void main(String[] args) {
        try(var connection = ConnectionManager.open()){

            System.out.println(connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(getTicketByFlightId(1));
        System.out.println(getFlightsBetween(LocalDate.of(2026,2,1).atStartOfDay(),
                LocalDate.of(2026,2,5).atStartOfDay()));
    }

    public static List<Long> getTicketByFlightId(int flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                        SELECT * FROM ticket
                        WHERE flight_id = ?;
                        """;

        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, flightId);
            var result = statement.executeQuery();
            while(result.next()){
                tickets.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) {
        List<Long> flights = new ArrayList<>();

        String sql = """
                SELECT * FROM flight 
                WHERE departure_date >= ? AND departure_date <= ?;
                """;

        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(sql))
        {
            statement.setTimestamp(1, Timestamp.valueOf(start));
            statement.setTimestamp(2,Timestamp.valueOf(end));
            var result = statement.executeQuery();
            while(result.next()){
                flights.add(result.getLong("id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
}
