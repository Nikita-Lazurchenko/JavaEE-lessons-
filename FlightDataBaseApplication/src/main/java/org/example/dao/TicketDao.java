package org.example.dao;

import org.example.dto.TicketFilter;
import org.example.entity.Flight;
import org.example.entity.Ticket;
import org.example.exception.DAOException;
import org.example.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements DAO<Ticket,Integer>{
    private static final TicketDao INSTANCE = new TicketDao();

    private static String SAVE_SQL = """
            INSERT INTO ticket
            (passport_number,passenger_name,flight_id,seat_number,cost)VALUES (?,?,?,?,?)""";

    private static String DELETE_SQL = "DELETE FROM ticket WHERE id = ?";

    private static String FINAD_ALL_SQL = """
            SELECT t.id,t.passport_number,t.passenger_name,t.flight_id,t.seat_number,t.cost,
                   f.flight_number,f.departure_date,f.departure_airport_code,
                   f.arrival_date,f.arrival_airport_code,f.aircraft_id,f.status
            FROM ticket t
            JOIN flight f ON t.flight_id=f.id
            """;

    private static String FIND_BY_ID_SQL = FINAD_ALL_SQL + " WHERE t.id = ?";

    private static String UPDATE_SQL = """
            UPDATE ticket
            SET passport_number=?,passenger_name=?,flight_id=?,seat_number=?,cost=?
            WHERE id=?
            """;

    @Override
    public Ticket save(Ticket ticket){
        try(var connection = ConnectionManager.get();
            var statement  = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, ticket.getPassportNumber());
            statement.setString(2, ticket.getPassengerName());
            statement.setInt(3, ticket.getFlight().getId());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setInt(5, ticket.getCost());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if(keys.next())
                ticket.setId(keys.getInt("id"));

            return ticket;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(DELETE_SQL))
        {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(Ticket ticket){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL))
        {
            statement.setString(1, ticket.getPassportNumber());
            statement.setString(2, ticket.getPassengerName());
            statement.setInt(3, ticket.getFlight().getId());
            statement.setInt(4, ticket.getSeatNumber());
            statement.setInt(5, ticket.getCost());
            statement.setInt(6, ticket.getId());

            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Ticket> findAll(){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FINAD_ALL_SQL))
        {
            var resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()){
                Flight flight = flightBuilder(resultSet);
                Ticket ticket = ticketBuilder(resultSet, flight);

                tickets.add(ticket);
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> findAll(TicketFilter ticketFilter){
        List<Object> parameters = new ArrayList<>();
        List<String> whereSQL = new ArrayList<>();
        if(ticketFilter.passengerName() != null){
            parameters.add(ticketFilter.passengerName());
            whereSQL.add("passenger_name = ?");
        }
        if(ticketFilter.seatNumber() != null){
            parameters.add(ticketFilter.seatNumber());
            whereSQL.add("seat_number = ?");
        }
        parameters.add(ticketFilter.limit());
        parameters.add(ticketFilter.offset());
        var where = whereSQL.stream().collect(Collectors.joining(
                " AND ",
                " WHERE ",
                " LIMIT ? OFFSET ? "));

        String sql = FINAD_ALL_SQL + where;
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(sql))
        {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(statement);
            var resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()){
                Flight flight = flightBuilder(resultSet);
                Ticket ticket = ticketBuilder(resultSet, flight);

                tickets.add(ticket);
            }

            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ticket> findById(Integer id){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BY_ID_SQL))
        {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                Flight flight = flightBuilder(resultSet);
                Ticket ticket = ticketBuilder(resultSet,flight);
                return Optional.ofNullable(ticket);
            }
            return Optional.empty();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    private Ticket ticketBuilder(ResultSet resultSet, Flight flight) throws SQLException {
        Ticket ticket = new Ticket(resultSet.getInt("id"),
                resultSet.getString("passport_number"),
                resultSet.getString("passenger_name"),
                flight,
                resultSet.getInt("seat_number"),
                resultSet.getInt("cost"));

        return ticket;
    }

    private Flight flightBuilder(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight(
                resultSet.getInt("id"),
                resultSet.getInt("flight_number"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getInt("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getInt("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                resultSet.getString("status")
        );

        return flight;
    }


    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao(){
    }


}
