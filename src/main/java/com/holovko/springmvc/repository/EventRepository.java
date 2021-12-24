package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query(value = "SELECT e FROM Event e WHERE e.id = ?1", nativeQuery = false)
    Event findEventById(long eventId);

    @Query(value = "SELECT e.id FROM Event e WHERE e.startDate >= ?1 and e.id = ?2", nativeQuery = false)
    Long getNotExpiredEventById(long eventId, LocalDateTime now);

    List<Event> findAllByOrderByStartDateAsc();

    @Query(value = "select e.name from orders ord  inner join events e on ord.event_id = e.id where buyer_name = ?1 order by e.name asc", nativeQuery = true)
    List<String> findEventsByBuyer(String BuyerName);
}
