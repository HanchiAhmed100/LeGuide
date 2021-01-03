package com.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entites.Chats;
import com.dev.entites.User;
import com.dev.repository.amisRepository;
import com.dev.repository.chatRepository;
import com.dev.repository.userRepository;

@RestController
public class chatController {
	private chatRepository cr;
	private userRepository ur;
	
	public chatController(chatRepository cr , userRepository ur) {
		this.cr = cr;
		this.ur = ur;
	}
	
	@PostMapping("/chat")
	public List<Chats> getMessages(@RequestBody HashMap<String, String> map){
		long id_1 = (long)Integer.parseInt(map.get("id_1"));
		long id_2 = (long)Integer.parseInt(map.get("id_2"));
		List<Chats> c = this.cr.findMsgs(id_1,id_2,id_2,id_1);
		return c;
		
	}
	
	@PostMapping("/chat/add")
	public List<Chats> addMessages(@RequestBody HashMap<String, String> map){
		long id_1 = (long)Integer.parseInt(map.get("id_1"));
		long id_2 = (long)Integer.parseInt(map.get("id_2"));
		String msg = map.get("msg");
		User u1 = ur.findById(id_1).get();
		User u2 = ur.findById(id_2).get();
		Chats ch = new Chats();
		ch.setMsg(msg);
		ch.setSend_msg(u1);
		ch.setRecive_msg(u2);
		Date date  = new Date();
		ch.setCreationDateTime(date);
		this.cr.save(ch);
		List<Chats> c = this.cr.findMsgs(id_1,id_2,id_2,id_1);		
		return c;
		
	}
	
}
