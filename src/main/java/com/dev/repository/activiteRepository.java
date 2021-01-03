package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.entites.Activite;

@Repository
public interface activiteRepository extends JpaRepository<Activite, Long>{
	
	@Query(value = "SELECT a.* FROM user AS u JOIN activite_user AS a_u ON u.user_id = a_u.users_user_id JOIN activite AS a ON a.activite_id = a_u.activite_activite_id  WHERE u.user_id = ? ",nativeQuery=true)
	public List<Activite> UserAct(long id);

}
