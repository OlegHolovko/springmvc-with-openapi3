package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByOrderByStartDateAsc();

    @Query(value = "select e.name from orders ord  inner join events e on ord.event_id = e.id where buyer_name = ?1 order by e.name asc", nativeQuery = true)
    List<String> findEventsByBuyer(String BuyerName);
}
