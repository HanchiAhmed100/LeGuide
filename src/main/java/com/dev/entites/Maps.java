package com.dev.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="maps")
public class Maps implements Serializable{
	
	@Id
	@Column(name="position_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long position_id;
	
	@Column(name="lat")
	private String lat;
	
	
	@Column(name="lng")
	private String lng;
	
	
    @ManyToOne
    @JoinColumn(name="activite",referencedColumnName = "activite_id")
	private Activite activite;
    
    
	public Maps() {
		super();
	}


	public Maps(long position_id, String lat, String lng, Activite activite) {
		super();
		this.position_id = position_id;
		this.lat = lat;
		this.lng = lng;
		this.activite = activite;
	}


	public long getPosition_id() {
		return position_id;
	}


	public void setPosition_id(long position_id) {
		this.position_id = position_id;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	/*
	public Activite getActivite() {
		return activite;
	}*/


	public void setActivite(Activite activite) {
		this.activite = activite;
	}
    
    

}
