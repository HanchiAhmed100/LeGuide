package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.entites.Post;

@Repository
public interface postRepository extends JpaRepository<Post, Long> {

	@Query(value = "SELECT p.* FROM post as p  WHERE p.activite = ? ORDER BY p.creationDateTime DESC ",nativeQuery = true)
	public List<Post> findposts(long id);
	
	
	@Query(value = "SELECT p.* FROM post as p JOIN activite as a ON a.activite_id = p.activite JOIN activite_user AS a_u ON a_u.users_user_id = a.activite_id WHERE a_u.users_user_id = ? ORDER BY p.creationDateTime DESC  ",nativeQuery = true)
	public List<Post> likedActivitePosts(long id);

	@Query(value = "SELECT p.* FROM post as p JOIN user as u ON p.createur = u.user_id WHERE p.createur = ? ORDER BY p.creationDateTime DESC", nativeQuery = true)
	public List<Post> UserPost(long id);
}
