package com.dev.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.dev.entites.Activite;

@Service
public class GetUserActivitesImpl implements GetUserActivites  {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Activite> actUser(long id){

		// TypedQuery<Activite> query = entityManager.createQuery("SELECT a.* FROM user AS u JOIN activite_user AS a_u ON u.user_id = a_u.users_user_id JOIN activite AS a ON a.activite_id = a_u.activite_activite_id  WHERE u.user_id = :id ", Activite.class);
		// activite.type,activite.nom,activite.cover,activite.pdf,activite.link,activite.activite_id,activite.description FROM activite JOIN activite_user ON activite_user.activite_activite_id = activite.activite_id JOIN user ON user.user_id = activite_user.users_user_id WHERE user.user_id = :id 
	    TypedQuery<Activite> query = entityManager.createQuery("SELECT a FROM user u JOIN u.activite a", Activite.class);
	    query.setParameter("id", id);
	    List<Activite> resultList = query.getResultList();
		return resultList;
		
	}

}
