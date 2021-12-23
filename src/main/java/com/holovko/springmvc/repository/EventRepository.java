package com.holovko.springmvc.repository;

import com.holovko.springmvc.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
