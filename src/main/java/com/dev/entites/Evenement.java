package com.dev.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="evenement")
public class Evenement {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name="evenement_id")
	private long evenement_id;
	
	@Column(name="evenement_nom")
	private String evenement_nom;
	
	@Column(name="evenement_date")
	private String evenement_date;
	
	@ManyToOne
	@JoinColumn(name = "activite_evenement",referencedColumnName = "activite_id")
	private Activite activite;
	
	@OneToMany(mappedBy = "evenement")
	private List<Participation> participations;

	public long getEvenement_id() {
		return evenement_id;
	}

	public void setEvenement_id(long evenement_id) {
		this.evenement_id = evenement_id;
	}

	public String getEvenement_nom() {
		return evenement_nom;
	}

	public void setEvenement_nom(String evenement_nom) {
		this.evenement_nom = evenement_nom;
	}

	public String getEvenement_date() {
		return evenement_date;
	}

	public void setEvenement_date(String evenement_date) {
		this.evenement_date = evenement_date;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	
	
	
	
}
