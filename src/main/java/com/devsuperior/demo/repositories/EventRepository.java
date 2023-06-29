package com.devsuperior.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.demo.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	Page<Event> findAll(Pageable page);

}

