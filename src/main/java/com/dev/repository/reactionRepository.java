package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.entites.Post;
import com.dev.entites.Reaction;

@Repository
public interface reactionRepository extends JpaRepository<Reaction, Long> {

	@Query(value = "SELECT r.* FROM reaction as r JOIN post as p ON r.post_id= p.post_id WHERE p.post_id = ? ",nativeQuery = true)
	public List<Reaction> postReactions(long id);

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM reaction WHERE user_id = ?  AND post_id = ? ",nativeQuery = true)
	public void DeleteReaction(long user_id , long post_id);
}
