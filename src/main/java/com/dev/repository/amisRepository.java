package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.entites.Activite;
import com.dev.entites.Amis;

@Repository
public interface amisRepository extends JpaRepository<Amis, Long> {

	@Query(value = "SELECT * FROM amis WHERE user_sender = ? AND  user_reciver = ?  ",nativeQuery=true)
	public Amis findAmis(long s, long r);

	@Query(value = "SELECT * FROM amis WHERE (user_sender = ? AND  user_reciver = ?) OR (user_sender = ? AND  user_reciver = ?)  ",nativeQuery=true)
	public Amis findAmisStatus(long s, long r , long r1, long s2);
	
	
	@Query(value = "SELECT a.* FROM user AS u JOIN amis AS a ON a.user_sender = u.user_id WHERE  a.user_reciver = ? AND a.status = 0 ",nativeQuery=true)
	public List<Amis> UsersWaitInv(long id);
	
	@Query(value = "SELECT a.* FROM user AS u JOIN amis AS a ON a.user_sender = u.user_id WHERE (user_sender = ? OR  user_reciver = ?) AND a.status = 1 ",nativeQuery=true)
	public List<Amis> UsersAllInv(long id,long id2);
	
	
	
}
