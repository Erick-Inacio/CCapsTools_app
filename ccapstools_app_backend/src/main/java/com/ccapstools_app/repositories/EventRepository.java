package com.ccapstools_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccapstools_app.models.event.EventModel;

public interface EventRepository extends JpaRepository<EventModel, Long> {

}
