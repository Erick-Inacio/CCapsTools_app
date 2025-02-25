package com.ccapstools_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.Speaker;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    @Query(value = "SELECT * FROM speakers WHERE user_id = :userId", nativeQuery = true)
    Speaker findSpeakerByUserId(@Param("userId") Long userId);
}
