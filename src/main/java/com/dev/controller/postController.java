package com.dev.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entites.Activite;
import com.dev.entites.Post;
import com.dev.entites.Reaction;
import com.dev.entites.User;
import com.dev.pojo.Posts_POJO;
import com.dev.repository.activiteRepository;
import com.dev.repository.postRepository;
import com.dev.repository.reactionRepository;
import com.dev.repository.userRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class postController {

	private postRepository postR;
	private activiteRepository actR;
	private userRepository userR;
	private reactionRepository reactionR;
	
	
	public postController(postRepository postR,activiteRepository actR, userRepository userR,reactionRepository reactionR) {
		this.postR = postR;
		this.actR = actR;
		this.userR = userR;
		this.reactionR = reactionR;
	}
	

	
	
	@DeleteMapping("/post/{id}")
	public void DeletePost(@PathVariable long id) {
		this.postR.deleteById(id);
	}
	
	
	@PostMapping("/post")
	public HashMap<String, String> addPost(@RequestBody String StringPost) {
		JSONObject JSONPost;
		try {
			JSONPost = new JSONObject(StringPost);
			
			long createur_id = (long) JSONPost.getInt("createur_id");
			long activite_id = (long) JSONPost.getInt("activite_id");

			String  Body  = JSONPost.getString("body");
			
			User u = this.userR.findById(createur_id).get();
			Activite a = this.actR.findById(activite_id).get();
			
			Date date  = new Date();
			
			Post P = new Post();
			P.setActivite(a);
			P.setCreateur(u);
			P.setBody(Body);
			P.setCreationDateTime(date);

			this.postR.save(P);
			HashMap<String, String> map = new HashMap<>();
			map.put("POST", "OK");
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	@PostMapping("/post/update/{id}")
	public HashMap<String, String> addPost(@RequestBody String StringPost,@PathVariable long id) {
		JSONObject JSONPost;
		try {
			JSONPost = new JSONObject(StringPost);
			String  Body  = JSONPost.getString("body");
			Post p = this.postR.findById(id).get();
			p.setBody(Body);
			this.postR.save(p);
			HashMap<String, String> map = new HashMap<>();
			map.put("POST", "OK");
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/posts")
	public List<Post> gg(){
		
	List<Post> posts = this.postR.findAll();
		for(int i = 0 ; i <posts.size() ;i++) {
			posts.get(i).getActivite().setPost(null);
			posts.get(i).getActivite().setUsers(null); ;
		}
		return posts;		
	}
	
	@GetMapping("/posts/activite/{id}")
	public List<Post> activite_posts(@PathVariable long id){
		List<Post> posts = this.postR.findposts(id);
		for(int i = 0 ; i <posts.size() ;i++) {
			posts.get(i).getActivite().setPost(null);
			posts.get(i).getActivite().setUsers(null); ;
		}
		return posts;	
	}
	
	@GetMapping("/posts/user/activite/{id}")
	public List<Post> liked_activite_posts(@PathVariable long id){
		List<Post> posts = this.postR.likedActivitePosts(id);
		for(int i = 0 ; i <posts.size() ;i++) {
			posts.get(i).getActivite().setPost(null);
			posts.get(i).getActivite().setUsers(null); ;
		}
		return posts;	
	}
	
	@GetMapping("/post/user/{id}")
	public List<Post> userPosts(@PathVariable long id){
		List<Post> posts = this.postR.UserPost(id);
		for(int i = 0 ; i <posts.size() ;i++) {
			posts.get(i).getActivite().setPost(null);
			posts.get(i).getActivite().setUsers(null); ;
		}
		return posts;
	}
	
	
	@GetMapping("/po")
	public List<Posts_POJO> getPosts(){
		
		
		List<Post> posts = this.postR.findAll();

		
		List<Posts_POJO> l = new ArrayList<Posts_POJO>();

		Posts_POJO p = new Posts_POJO();
		
		for(int i = 0 ; i <posts.size() ;i++) {
			
			p.setPost_id(posts.get(i).getPost_id());
			p.setBody(posts.get(i).getBody());
			p.setActivite_id(posts.get(i).getActivite().getActivite_id());
			p.setActivite_type(posts.get(i).getActivite().getType());
			p.setActivite_name(posts.get(i).getActivite().getNom());
			p.setCreator_id(posts.get(i).getCreateur().getUser_id());
			p.setCreator_name(posts.get(i).getCreateur().getNom()+" "+posts.get(i).getCreateur().getPrenom());
			p.setActivite_cover(posts.get(i).getActivite().getCover());
			
			System.out.println(p.getCreator_name());

			l.add(p);
		}
		for(int j = 0 ; j< l.size(); j++) {
			System.out.println(l.get(j).getBody());
		}
		return l;
			
		


	}

	@GetMapping("/posts/{id}")
	public Post getOnePost(@PathVariable long id) {
		Post p =  this.postR.findById(id).get();
			p.getActivite().setPost(null);
			p.getActivite().setUsers(null); ;
		return p;
	}
	
	@PostMapping("/reaction")
	public HashMap<String, String> setReaction(@RequestBody HashMap<String, String> m){

		long user_id = Integer.parseInt(m.get("user_id"));
		long post_id = Integer.parseInt(m.get("post_id"));

		this.reactionR.DeleteReaction(user_id, post_id);
		
		Post p = postR.findById(post_id).get();
		User u = userR.findById(user_id).get();
		Reaction r = new Reaction();
		r.setReaction_type(m.get("reaction_type"));
		r.setUser(u);
		r.setPost(p);
		reactionR.save(r);
		m.put("STATUS", "OK");
		return m;
	}
	
	@PostMapping("/reaction/delete")
	 HashMap<String, String> DeleteReaction(@RequestBody HashMap<String, String> m){
		System.out.println(m.get("reaction_type"));
		long user_id = Integer.parseInt(m.get("user_id"));
		long post_id = Integer.parseInt(m.get("post_id"));

		Post p = postR.findById(post_id).get();
		User u = userR.findById(user_id).get();

		this.reactionR.DeleteReaction(user_id,post_id);
		m.put("STATUS", "OK");
		return m;
	}
	
	@GetMapping("/reaction/{id}")
	public List<Reaction> getReactionsbyPost(@PathVariable long id){
		return reactionR.postReactions(id);
	}
}
