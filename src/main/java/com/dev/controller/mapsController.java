package com.dev.controller;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entites.Activite;
import com.dev.entites.Maps;
import com.dev.repository.activiteRepository;
import com.dev.repository.mapsRepository;

@RestController
public class mapsController {

	private mapsRepository mR;
	private activiteRepository aR;
	
	
	private mapsController(mapsRepository mr ,activiteRepository ar) {
		this.mR = mr;
		this.aR = ar;
	}
	
	
	@CrossOrigin
	@GetMapping("/maps/{id}")
	public List<Maps> GetAllUserActivites(@PathVariable long id) {
		List<Maps> m = mR.findByActivite(id);
		return m;
	}
	
	
	@CrossOrigin
	@PostMapping("/maps")
	public Maps SaveMap(@RequestBody HashMap<String, String> m) {
		Maps map = new Maps();
		map.setLat(m.get("lat"));
		map.setLng(m.get("lng"));
		Activite a = aR.findById((long) Integer.parseInt(m.get("activite_id"))).get();
		map.setActivite(a);
		return mR.save(map);
	}

}
