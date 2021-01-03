package com.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entites.Commentaire;
import com.dev.entites.Post;
import com.dev.entites.User;
import com.dev.repository.commentaireRepository;
import com.dev.repository.postRepository;
import com.dev.repository.userRepository;

@RestController
public class commentaireController {
	
	private commentaireRepository commentaireRepo;
	private postRepository postRepo;
	private userRepository userRepo;
	
	
	public commentaireController(commentaireRepository cr , postRepository pr , userRepository ur) {
		this.commentaireRepo = cr;
		this.postRepo = pr;
		this.userRepo = ur;
	}
	
	@PostMapping("/commentaire")
	public HashMap<String, String> addComment(@RequestBody String commentaire){
		HashMap<String, String> map = new HashMap<>();
		JSONObject js;
		try {
			js = new JSONObject(commentaire);
			long user_id = js.getLong("user_id");
			long post_id = js.getLong("post_id");
			String body = js.getString("body");
			
			User u = this.userRepo.findById(user_id).get();
			Post p = this.postRepo.findById(post_id).get();
			
			Commentaire c = new Commentaire();
			c.setCreateur(u);
			c.setPost(p);
			Date date  = new Date();
			c.setCreationDateTime(date);
			c.setBody(body);

			this.commentaireRepo.save(c);
			map.put("Status", "OK");
			return map;
		} catch (JSONException e) {
			map.put("Status", "FAILS");
			return map;
		}
	}
	@GetMapping("/commentaires")
	public List<Commentaire> getComments(){
		return this.commentaireRepo.findAll();
	}
	
	@DeleteMapping("/commentaire/{id}")
	public void deleteComment(@PathVariable long id) {
		this.commentaireRepo.deleteById(id);
	}
	
}
