package com.scheduleapp2.repository;

import com.scheduleapp2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
