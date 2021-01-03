package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entites.Commentaire;

@Repository
public interface commentaireRepository extends JpaRepository<Commentaire, Long>{

}
