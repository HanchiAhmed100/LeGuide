package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.entites.Maps;

@Repository
public interface mapsRepository extends JpaRepository<Maps, Long> {

	@Query(value = "SELECT * FROM maps WHERE activite = ? ",nativeQuery=true)
	public List<Maps> findByActivite(long id);
}
