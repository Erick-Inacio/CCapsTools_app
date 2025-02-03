package com.ccapstools_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.Speaker;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
