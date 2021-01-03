package com.dev.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "participation")
public class Participation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long participation_id;

	@Column(name="etat")
	private int etat;
	
	@ManyToOne
	@JoinColumn(name = "participant", referencedColumnName = "user_id")
	private User participant;
	
	@ManyToOne
	@JoinColumn(name = "evenement", referencedColumnName = "evenement_id")
	private Evenement evenement;

	public Long getParticipation_id() {
		return participation_id;
	}

	public void setParticipation_id(Long participation_id) {
		this.participation_id = participation_id;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	
	
	
	
	
}
