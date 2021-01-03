package com.dev.controller;

import java.util.ArrayList;
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

import com.dev.entites.Amis;
import com.dev.entites.User;
import com.dev.repository.amisRepository;
import com.dev.repository.userRepository;

@RestController
public class amisController {

	
	private amisRepository ar;
	private userRepository ur;
	
	public amisController(amisRepository ar , userRepository ur) {
		this.ar = ar;
		this.ur = ur;
	}
	
	
	@GetMapping("/ami/{id}")
	public User fri(@PathVariable long id ) {
		return this.ur.findById(id).orElse(null) ;
	}
	
	@PostMapping("/amis")
	public HashMap<String , String> ReqFriend(@RequestBody String usersIdString){
		HashMap<String , String> map = new HashMap<String , String>();	
		try {
				JSONObject UsersIds= new JSONObject(usersIdString);
				long sender_id = UsersIds.getInt("sender_id");
				long reciver_id = UsersIds.getInt("reciver_id");

				System.out.println("sender_id " + sender_id + "reciver_id "+ reciver_id);
				
				User User_sender = this.ur.findById(sender_id).orElse(null);
				User User_reciver = this.ur.findById(reciver_id).orElse(null);
				
				if( User_sender != null && User_reciver != null) {
					System.out.println("aaaa");

					int Status = 0;
					Amis a = new Amis();
					a.setStatus(Status);
					a.setSend(User_sender);
					a.setReciver(User_reciver);


					this.ar.save(a);
					map.put("STATUS", "OK");

				}else {
					System.out.println("hhh");
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
				//map.put("STATUS","FAILS");
				return null;
			}
		return map;		
	}
	
	
	@PostMapping("/amiss")
	public Amis ReqsFriend(@RequestBody String usersIdString){
		HashMap<String , String> map = new HashMap<String , String>();	
		try {
				JSONObject UsersIds= new JSONObject(usersIdString);
				long sender_id = UsersIds.getInt("sender_id");
				long reciver_id = UsersIds.getInt("reciver_id");

				System.out.println("sender_id " + sender_id + "reciver_id "+ reciver_id);
				
				User User_sender = this.ur.findById(sender_id).orElse(null);
				User User_reciver = this.ur.findById(reciver_id).orElse(null);
				
					System.out.println("aaaa");

					int Status = 0;
					Amis a = new Amis();
					a.setStatus(Status);
					a.setSend(User_sender);
					a.setReciver(User_reciver);
					map.put("STATUS", "OK");
					return a;
				
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}

	}
	@PostMapping("/amis/status")
	public HashMap<String , String> AcceptFriend(@RequestBody String usersIdString){
		HashMap<String , String> map = new HashMap<String , String>();	
		try {
				JSONObject UsersIds= new JSONObject(usersIdString);
				String sender_id = UsersIds.get("sender_id").toString();
				String reciver_id = UsersIds.get("reciver_id").toString();
				int Status = (int) UsersIds.get("Status");

				if(Status == -1) {	
					Amis a = ar.findAmisStatus((long) Integer.parseInt(sender_id), (long) Integer.parseInt(reciver_id),(long) Integer.parseInt(reciver_id),(long) Integer.parseInt(sender_id));

					this.ar.delete(a);
				}else {
					Amis a = ar.findAmis((long) Integer.parseInt(sender_id), (long) Integer.parseInt(reciver_id));

					a.setStatus(Status);
					this.ar.save(a);
				}

				map.put("STATUS", "OK");
			} catch (JSONException e) {
				e.printStackTrace();
				map.put("STATUS","FAILS");
			}
			return map;		
	}
	@PostMapping("/checkstatus")
	public Amis checkFriend(@RequestBody String usersIdString){
		try {
				JSONObject UsersIds= new JSONObject(usersIdString);
				String sender_id = UsersIds.get("user_id_1").toString();
				String reciver_id = UsersIds.get("user_id_2").toString();
				Amis a = ar.findAmisStatus((long) Integer.parseInt(sender_id), (long) Integer.parseInt(reciver_id) ,(long) Integer.parseInt(reciver_id),(long) Integer.parseInt(sender_id));
				return a;		

			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	

	@GetMapping("/amis/my/{id}")
	public HashMap<String, List<Amis>> MyFriends(@PathVariable long id){
		HashMap<String, List<Amis>> hmap = new HashMap<String,List<Amis>>();
		List<Amis> WaitingList = this.ar.UsersWaitInv(id);
		hmap.put("waiting", WaitingList);
		List<Amis> FriendList = this.ar.UsersAllInv(id,id);
		for(int i = 0 ; i<FriendList.size();i++) {
			if(FriendList.get(i).getSender().getUser_id() == id) {
				FriendList.get(i).setSend(FriendList.get(i).getReciver());
			}
		}
		hmap.put("Friend", FriendList);		
		
		return hmap;
	}
}
