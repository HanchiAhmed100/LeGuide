package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.entites.Amis;
import com.dev.entites.Chats;

public interface chatRepository extends JpaRepository<Chats, Long>{

	@Query(value = "SELECT * FROM chats WHERE (user_sender = ? AND  user_reciver = ?) OR (user_sender = ? AND  user_reciver = ?) ORDER BY creationDateTime ",nativeQuery=true)
	public List<Chats> findMsgs(long id ,long id_2 , long id_3 , long id_4);
}
