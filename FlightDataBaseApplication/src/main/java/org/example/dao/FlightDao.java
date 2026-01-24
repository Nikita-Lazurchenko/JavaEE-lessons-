package org.example.dao;

import org.example.entity.Flight;
import org.example.exception.DAOException;
import org.example.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements DAO<Flight,Integer>{

    private static final FlightDao INSTANCE = new FlightDao();

    private static String UPDATE_SQL = """
            UPDATE flight 
            SET flight_number=?,departure_date=?,departure_airport_code=?,
                arrival_date=?,arrival_airport_code=?,aircraft_id=?,status=?
            WHERE id=?""";

    private static String FIND_ALL_SQL = """
            SELECT id,flight_number,departure_date,departure_airport_code,
                arrival_date,arrival_airport_code,aircraft_id,status
            FROM flight    
            """;

    private static String FIND_BY_ID = FIND_ALL_SQL + "WHERE id = ? ";

    private static String SAVE_SQL = """
            INSERT INTO flight(flight_number,departure_date,departure_airport_code,
                arrival_date,arrival_airport_code,aircraft_id,status)
            VAlUES (?,?,?,?,?,?,?)
            """;

    private static String DELETE_SQL = "DELETE FROM flight WHERE id = ?";

    @Override
    public boolean update(Flight flight) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL))
        {
            statement.setInt(1,flight.getFlightNumber());
            statement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            statement.setInt(3,flight.getDepartureAirportCode());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getArrivalDate()));
            statement.setInt(5,flight.getArrivalAirportCode());
            statement.setInt(6,flight.getAircraftId());
            statement.setString(7,flight.getStatus());

            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL_SQL))
        {
            List<Flight> flights = new ArrayList<>();
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                flights.add(builtFlight(resultSet));
            }

            return flights;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Flight builtFlight(ResultSet resultSet) throws SQLException {
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

    @Override
    public Optional<Flight> findById(Integer id) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL_SQL))
        {
            statement.setInt(1,id);
            var resultSet = statement.executeQuery();
            var flight = builtFlight(resultSet);
            if(resultSet.next()){
                flight = builtFlight(resultSet);
            }

            return Optional.of(flight);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Flight save(Flight flight) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(SAVE_SQL))
        {
            statement.setInt(1,flight.getFlightNumber());
            statement.setTimestamp(2, Timestamp.valueOf(flight.getDepartureDate()));
            statement.setInt(3,flight.getDepartureAirportCode());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getArrivalDate()));
            statement.setInt(5,flight.getArrivalAirportCode());
            statement.setInt(6,flight.getAircraftId());
            statement.setString(7,flight.getStatus());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if(keys.next()){
                flight.setId(keys.getInt(1));
            }

            return flight;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(SAVE_SQL))
        {
            statement.setInt(1,id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private FlightDao() {
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
