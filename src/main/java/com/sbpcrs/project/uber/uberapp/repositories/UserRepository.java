package com.sbpcrs.project.uber.uberapp.repositories;

import com.sbpcrs.project.uber.uberapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
