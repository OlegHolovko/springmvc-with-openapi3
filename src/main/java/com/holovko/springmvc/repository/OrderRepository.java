package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT SUM(amount) FROM orders WHERE event_id = ?1", nativeQuery = true)
    Integer getAmountSumByEventId(long eventId);

}
