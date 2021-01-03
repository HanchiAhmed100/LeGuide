package com.dev.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.websocket.server.PathParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.entites.Activite;
import com.dev.entites.User;
import com.dev.pojo.ActivtesQuery;
import com.dev.pojo.ResponseMessage;
import com.dev.repository.activiteRepository;
import com.dev.repository.userRepository;
import com.dev.service.FilesStorageService;
import com.dev.service.GetUserActivites;

@RestController
public class activiteController {
	
	private activiteRepository activiteRepo;
	private userRepository Urepo;
	
	@Autowired
	GetUserActivites GetUserActivitesService;
	
	public activiteController(activiteRepository repo , userRepository Urepo) {
		this.activiteRepo = repo;
		this.Urepo = Urepo;
	}
	
	 @Autowired
	 FilesStorageService storageService;

	 @PostMapping("/uploads/cover/{id}")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable long id) {
	    String message = "";
	    try {
	    	if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/jpg") ) {
	    		storageService.saveCover(file);
	    		message = "Le fichier a été téléchargé avec succès: " + file.getOriginalFilename();
	    		Activite act = activiteRepo.getOne(id);
	    		act.setCover(file.getOriginalFilename());
	    		activiteRepo.save(act);
	    	}else {
	    		message = "type de fichier incorrect: " + file.getContentType();
	    	}
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Ipossible de télécharger le fichier:" + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	
	 @PostMapping("/uploads/pdf/{id}")
	  public ResponseEntity<ResponseMessage> uploadPdf(@RequestParam("file") MultipartFile file,@PathVariable long id) {
	    String message = "";
	    try {
	    	if(file.getContentType().equals("application/pdf")) {
		      storageService.savePdf(file);
		      message = "Uploaded the file successfully: " + file.getOriginalFilename();
		      Activite act = activiteRepo.getOne(id);
		      act.setPdf(file.getOriginalFilename());
		      activiteRepo.save(act);
		      System.out.println(act.toString());
	    	}else {
	    		message = "file type inccorrecte : " + file.getContentType();	
	    	}
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	 
	 
	@PostMapping("/activite/url/{id}")
	public Activite updateActiviteUrl(@RequestBody String urlJson,@PathVariable long id) {
			try {
				JSONObject ParamsJson = new JSONObject(urlJson);
				Activite a = activiteRepo.getOne(id);	
				System.out.println(id);
				System.out.println(a.toString());
				System.out.println(ParamsJson.getString("url"));
				
				
				a.setLink( ParamsJson.getString("url"));
				return activiteRepo.save(a);
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
	@GetMapping("/activites")
	public List<Activite> getAllActivites(){
	    System.out.println("hnee");
		return activiteRepo.findAll();
	}
	
	@PostMapping("/activite")
	public Activite saveNewActivite(@RequestBody HashMap<String, String> activite) {
		Activite a = new Activite();
		a.setNom(activite.get("nom"));
		a.setDescription(activite.get("description"));
		a.setType(activite.get("type"));
		a.setCover(activite.get("cover"));
		User u = Urepo.findById((long) Integer.parseInt(activite.get("activite_admin"))).get();
		a.setAdmin(u);
		return activiteRepo.save(a);
	}
	
	
	/*@GetMapping("/user_activite/{id}")
	public List<Activite> GetAllUserActivites(@PathVariable long id) {
		List<Activite> a = Urepo.UserActivites(id);
		for(int i = 0 ; i< a.size();i++) {
			System.out.println(a.get(i).toString());
		}
		return null;
	}

	@GetMapping("/user_activite/{id}")
	public HashMap<String,HashSet<HashMap<String, Object>>> GetAllUserActivites(@PathVariable long id) {
		
		HashMap<String, Object> Map =  new HashMap<String, Object>();
		
		HashMap<String,HashSet<HashMap<String, Object>>> FinalMap =  new HashMap<String,HashSet<HashMap<String, Object>>>();
		
		HashSet<HashMap<String, Object>> set = new HashSet<>();

		
		List<Object[]> QueryResultObject = Urepo.UserActivites(id);

		for (Object[] obj : QueryResultObject) {
			if(Map.isEmpty()) {
				Map.put("activite_id", obj[0]);
				Map.put("cover", obj[1]);
				Map.put("description",obj[2]);
				Map.put("link", obj[3]);
				Map.put("nom",obj[4]);
				Map.put("pdf",obj[5]);
				Map.put("type",obj[6]);	
				System.out.println("push new");
				System.out.println("objet value : "+obj[4]);
				System.out.println("map value : "+Map.get("nom"));
				set.add(Map);
				Iterator value = set.iterator(); 			        
		        System.out.println("The iterator values are: "); 
		        while (value.hasNext()) { 
		            System.out.println(value.next()); 
		        } 
				System.out.println("-----------");


			}else {
				Map.replace("activite_id", obj[0]);
				Map.replace("cover", obj[1]);
				Map.replace("description",obj[2]);
				Map.replace("link", obj[3]);
				Map.replace("nom",obj[4]);
				Map.replace("pdf",obj[5]);
				Map.replace("type",obj[6]);
				System.out.println("replace");
				System.out.println("object value : "+obj[4]);
				System.out.println("map value "+Map.get("nom"));
				set.add(Map);	
				Iterator value = set.iterator();    
		        System.out.println("The iterator values are: "); 
		        while (value.hasNext()) { 
		            System.out.println(value.next()); 
		        } 
			}	
		}
		FinalMap.put("activite", set);
		return FinalMap;
	}
		*/
	
	/*
	@GetMapping("/user_activite/{id}")
	public void GetAllUserActivites(@PathVariable long id) {
		List<Activite> li = GetUserActivitesService.actUser(id);
		for(int i = 0 ; i< li.size() ;i++) {
			System.out.println(li.get(i).getNom());
		}


	}*/
	
	@GetMapping("/user_activite/{id}")
	public List<Activite> GetAllUserActivites(@PathVariable long id) {
		List<Activite> li = activiteRepo.UserAct(id);
		return li;
	}
	
	
	
	
	
	
	
	@PostMapping("/activite/abonnement")
	public HashMap<String, String> UserAbonnementActivite(@RequestBody String DataJson) {
		HashMap<String , String> Map = new HashMap<>();
		try {
			JSONObject Jobej = new JSONObject(DataJson);
			long user_id = Jobej.getInt("user_id");
			long activite_id = Jobej.getInt("activite_id");
			AddUserToActivite(user_id,activite_id);
			AddActiviteToUser(user_id, activite_id);
			Map.put("STATUS", "OK");
		} catch (JSONException e) {
			e.printStackTrace();
			Map.put("STATUS", "FAIL");
		}
		return Map;
	}
	
	@PostMapping("/activite/desabonnement")
	public HashMap<String, String> UserDesAbonnementActivite(@RequestBody String DataJson) {
		HashMap<String , String> Map = new HashMap<>();
		try {
			JSONObject Jobej = new JSONObject(DataJson);
			long user_id = Jobej.getInt("user_id");
			long activite_id = Jobej.getInt("activite_id");
			DeleteUserFromActiviteEnt(user_id, activite_id);
			DeleteActiviteFromUserEnt(user_id, activite_id);
			Map.put("DELETE", "OK");
			
		} catch (JSONException e) {
			e.printStackTrace();
			Map.put("STATUS", "FAIL");
		}
		return Map;
	}
	
	
	public void AddUserToActivite(long user_id,long activite_id) {
		User u = this.Urepo.getOne(user_id);
		Activite a = this.activiteRepo.getOne(activite_id);
		List<User> list = a.getUsers();
		list.add(u);
		a.setUsers(list);
		this.activiteRepo.save(a);
	}
	public void AddActiviteToUser(long user_id,long activite_id) {
		User u = this.Urepo.getOne(user_id);
		Activite a = this.activiteRepo.getOne(activite_id);
		List<Activite> list = u.getActivites();
		list.add(a);
		u.setActivites(list);
		this.Urepo.save(u);
	}
	
	
	public void DeleteUserFromActiviteEnt(long user_id,long activite_id) {
		Activite a = this.activiteRepo.getOne(activite_id);			
		List<User> users = a.getUsers();
		for(int i = 0 ; i< users.size() ; i++) {
			if(users.get(i).getUser_id() == user_id) {
				users.remove(i);
			}
		}
		a.setUsers(users);
		this.activiteRepo.save(a);
		System.out.println("DeleteUserFromActiviteEnt SUCCESS");
	}
	
	public void DeleteActiviteFromUserEnt(long user_id,long activite_id) {
		User u = this.Urepo.getOne(user_id);			
		List<Activite> activites = u.getActivites();
		for(int i = 0 ; i< activites.size() ; i++) {
			if(activites.get(i).getActivite_id() == activite_id) {
				activites.remove(i);
			}
		}
		u.setActivites(activites);
		this.Urepo.save(u);
		System.out.println("DeleteActiviteFromUserEnt SUCCESS");
	}
	
	
}
 


