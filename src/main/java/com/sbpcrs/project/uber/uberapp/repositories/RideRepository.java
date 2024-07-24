package com.sbpcrs.project.uber.uberapp.repositories;

import com.sbpcrs.project.uber.uberapp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
