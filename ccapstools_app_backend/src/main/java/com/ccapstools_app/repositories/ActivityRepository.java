package com.ccapstools_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.event.ActivityModel;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {

    @Query(value="SELECT * FROM activities WHERE event_id = :eventId", nativeQuery = true)
    List<ActivityModel> findAllByEventId(@Param("eventId") Long eventId);
}
