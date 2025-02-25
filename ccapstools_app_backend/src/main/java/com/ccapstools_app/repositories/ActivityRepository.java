package com.ccapstools_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.event.ActivityModel;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {
}
