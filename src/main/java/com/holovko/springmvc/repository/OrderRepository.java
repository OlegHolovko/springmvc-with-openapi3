package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Event;
import com.holovko.springmvc.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT e FROM Event e WHERE e.id = ?1", nativeQuery = false)
    Event findEventById(long eventId);

    @Query(value = "SELECT id FROM springmvc.public.events WHERE start_date >= CURRENT_DATE and id = ?1", nativeQuery = true)
    Long getNotExpiredEventById(long eventId);

    @Query(value = "SELECT SUM(amount) FROM springmvc.public.orders WHERE event_id = ?1", nativeQuery = true)
    Integer getAmountSumByEventId(long eventId);



}
