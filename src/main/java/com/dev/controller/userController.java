package com.dev.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.repository.userRepository;
import com.dev.service.FilesStorageService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.dev.entites.Activite;
import com.dev.entites.User;
import com.dev.pojo.ResponseMessage;


@RestController
public class userController {

	private userRepository userRepo;
	
	public userController(userRepository repo) {
		this.userRepo = repo;
	}
	
	 @Autowired
	 FilesStorageService storageService;

		
	@GetMapping("/adel")
	public String sayHello() {
		return "helloo si adel !";
	}

	@GetMapping("/users")
	public List<User> all(){
		System.out.println("heeer");

		return userRepo.findAll();
	}
	
	@PostMapping("/userauth")
	public HashMap<String, Object> auth(@RequestBody String authParamsString) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			JSONObject authParamsJson = new JSONObject(authParamsString);
			String User_mail = (String) authParamsJson.get("mail");
			String User_password = (String) authParamsJson.get("password");
			User myUser = userRepo.getUserByMail(User_mail);
			if( myUser != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				if( passwordEncoder.matches(User_password , myUser.getMotdepasse() )) {
					map.put("AUTH", "SUCCESS");
				    map.put("USER", myUser);
				}else {
					map.put("AUTH", "FAILED");
					map.put("DESC", "Mot de passe inccorecte");
				    map.put("USER", null);		
				}
			}else {
				map.put("AUTH", "FAILED");
				map.put("DESC", "Utilisateur non trouvé");
			    map.put("USER", null);		
		    }
		} catch (JSONException e) {
			map.put("AUTH", "FAILED");
			map.put("DESC", "JSON ERROR");
		    map.put("USER", null);		
		}
		return map;
	}
	
	@PostMapping("/users")
	public User newUser(@RequestBody User newUser) {
		System.out.println(newUser);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String HashPassword = passwordEncoder.encode(newUser.getMotdepasse()); 
		newUser.setMotdepasse(HashPassword);	
		return userRepo.save(newUser);	
	}
	
	 @PostMapping("/uploads/profile/{id}")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable long id) {
	    String message = "";
	    try {
	    	if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/jpg") ) {
	    		storageService.saveProfile(file);
	    		message = "Le fichier a été téléchargé avec succès: " + file.getOriginalFilename();
	    		User u = userRepo.findById(id).get();
	    		u.setValidepic(1);
	    		u.setProfile(file.getOriginalFilename());
	    		userRepo.save(u);
	    	}else {
	    		message = "type de fichier incorrect: " + file.getContentType();
	    	}
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Ipossible de télécharger le fichier:" + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	 
	@GetMapping("/user/validate/{id}")
	public HashMap<String, String> SetUserValide(@PathVariable long id){
		User u = userRepo.findById(id).get();
		u.setValidepic(1);
		userRepo.save(u);
		HashMap<String , String> m = new HashMap<>();
		m.put("STATUS", "OK");
		return m;
	}
	
	@GetMapping("/username/{nom}")
	public User getUser(@PathVariable String nom) {
		return userRepo.getUserByNom(nom);
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> raja3User(@PathVariable long id) {
		return userRepo.findById(id);
	}
	
	@DeleteMapping("/user/{id}")
	public void DeleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
	}
	
	@PutMapping("/user/{id}")
	public User UpdateUser(@RequestBody User UpUser ,@PathVariable long id ) {
		return userRepo.findById(id).map(us -> {
			us.setNom(UpUser.getNom());
			us.setPrenom(UpUser.getPrenom());
			us.setMail(UpUser.getMail());
			us.setMotdepasse(UpUser.getMotdepasse());
			return userRepo.save(us);				
		})
		.orElseGet(() ->{
			UpUser.setUser_id(id);
			return userRepo.save(UpUser);
		});
	}	
}
