package com.dev.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entites.Activite;
import com.dev.entites.Evenement;
import com.dev.entites.Participation;
import com.dev.entites.User;
import com.dev.repository.activiteRepository;
import com.dev.repository.evenemtsRepository;
import com.dev.repository.participantRepository;
import com.dev.repository.userRepository;

@RestController
public class evenementController {

	
	private evenemtsRepository EventRepo;
	private activiteRepository ActiviteRepo;
	private userRepository userRepo;
	private participantRepository partiRepository;
	
	
	public evenementController(evenemtsRepository er , activiteRepository ar , userRepository ur, participantRepository pr) {
		this.EventRepo = er;
		this.ActiviteRepo = ar;
		this.userRepo = ur;
		this.partiRepository = pr;
	}
	
	@PostMapping("/evenement")
	public Evenement setEvenement(@RequestBody HashMap<String, String> event) {
		String event_nom = event.get("nom");
		String event_date = event.get("date");
		long activite_id = (long) Integer.parseInt(event.get("activite_id"));
		Activite a = ActiviteRepo.findById(activite_id).get();
		Evenement e = new Evenement();
		e.setEvenement_nom(event_nom);
		e.setEvenement_date(event_date);
		e.setActivite(a);
		return EventRepo.save(e);
	}
	@GetMapping("/evenements")
	public List<Evenement> getEvenements(){
		return EventRepo.findAll();
	}
	
	@GetMapping("/evenements/{id}")
	public List<Evenement> eventsByActivite(@PathVariable long id){
		return EventRepo.getEvenementByActivite(id);
	}
	
	@DeleteMapping("/evenements/{id}")
	public void deleteEvent(@PathVariable long id) {
		EventRepo.deleteById(id);
	}
	
	@PostMapping("/participation")
	public Participation participation(@RequestBody HashMap<String, String> part ) {
		int etat = Integer.parseInt(part.get("etat"));
		long user_id =  Integer.parseInt(part.get("user_id"));
		long event_id = Integer.parseInt(part.get("event_id"));
		Participation p = new Participation();
		p.setEtat(etat);
		User u = userRepo.findById(user_id).get();
		p.setParticipant(u);
		Evenement e = EventRepo.findById(event_id).get();
		p.setEvenement(e);
		return partiRepository.save(p);
		
	}
	
	@DeleteMapping("/participation/{id}")
	public void participationDelete(@PathVariable long id) {
		
	}
}
