package org.example.dao;

import org.example.dto.TicketFilter;
import org.example.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface DAO<K,E>{
    boolean update(K k);
    List<K> findAll();
    Optional<K> findById(E id);
    K save(K k);
    boolean delete(E e);
}
