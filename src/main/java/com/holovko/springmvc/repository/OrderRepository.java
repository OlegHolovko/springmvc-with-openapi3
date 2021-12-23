package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT e FROM Event e WHERE e.id = ?1", nativeQuery = false)
    Event findEventByIdNativeQuery(long id);

}
