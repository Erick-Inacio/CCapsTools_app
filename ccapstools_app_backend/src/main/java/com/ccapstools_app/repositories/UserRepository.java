package com.ccapstools_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid);
}
