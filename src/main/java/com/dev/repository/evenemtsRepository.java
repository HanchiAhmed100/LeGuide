package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.entites.Evenement;

@Repository
public interface evenemtsRepository extends JpaRepository<Evenement, Long> {


	@Query(value = "SELECT e.* FROM evenement AS e JOIN activite AS a ON e.activite_evenement = a.activite_id WHERE a.activite_id = ?",nativeQuery = true)
	public List<Evenement> getEvenementByActivite(long id);
	
	
	
}
