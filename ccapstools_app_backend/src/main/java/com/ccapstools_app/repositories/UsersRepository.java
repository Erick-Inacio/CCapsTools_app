package com.ccapstools_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccapstools_app.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
