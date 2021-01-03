package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entites.Participation;

@Repository
public interface participantRepository extends JpaRepository<Participation, Long> {

}
