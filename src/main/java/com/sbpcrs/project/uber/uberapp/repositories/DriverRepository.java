package com.sbpcrs.project.uber.uberapp.repositories;

import com.sbpcrs.project.uber.uberapp.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
