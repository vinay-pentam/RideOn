package com.sbpcrs.project.uber.uberapp.repositories;

import com.sbpcrs.project.uber.uberapp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
