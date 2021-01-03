package com.dev.entites;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long user_id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="mot_de_passe")
	private String motdepasse;
	
	@Column(name="profile")
	private String profile;
	
	@Column(name="valide_pic")
	private int validepic;
	
	
	@OneToMany(mappedBy = "Send")
	@JsonIgnore
	private List<Amis> Sends;
	
	@OneToMany(mappedBy = "Recive")
	@JsonIgnore
	private List<Amis> Recives;

	
	
	@OneToMany(mappedBy = "Send_msg")
	@JsonIgnore
	private List<Chats> Send_msg;
	
	@OneToMany(mappedBy = "Recive_msg")
	@JsonIgnore
	private List<Chats> Recive_msg;
	
	
	

	@JsonIgnore
	@ManyToMany	
	private List<Activite> activites;
 
	@OneToMany(mappedBy = "Createur")
	@JsonIgnore
	private List<Post> Posts;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Reaction> reactions;
	
	@OneToMany(mappedBy = "Createur")
	@JsonIgnore
	private List<Commentaire> commentaires;

	
	
	@OneToMany(mappedBy = "Admin")
	@JsonIgnore
	private List<Activite> Admin;
	
	@OneToMany(mappedBy = "participant")
	@JsonIgnore
	private List<Participation> paticipation;	
	
	
	
	public User(Long user_id, String nom, String prenom, String mail, String motdepasse, List<Amis> sends,
			List<Amis> recives, List<Activite> activites) {
		super();
		this.user_id = user_id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motdepasse = motdepasse;
		Sends = sends;
		Recives = recives;
		this.activites = activites;
	}
	
	



	public User() {
		super();
	}
	
	@JsonIgnore
	public List<Amis> getSends() {
		return Sends;
	}
	public void setSends(List<Amis> sends) {
		Sends = sends;
	}
	
	@JsonIgnore
	public List<Amis> getRecives() {
		return Recives;
	}
	
	public void setRecives(List<Amis> recives) {
		Recives = recives;
	}
	public User(String mail, String motdepasse) {
		super();
		this.mail = mail;
		this.motdepasse = motdepasse;
	}
	
	public User(Long user_id, String nom, String prenom, String mail, String motdepasse) {
		super();
		this.user_id = user_id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motdepasse = motdepasse;
	}
	public User(Long user_id, String nom, String prenom, String mail, String motdepasse ,String profile) {
		super();
		this.user_id = user_id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motdepasse = motdepasse;
		this.profile = profile;
		this.validepic = -1;
	}

	public User(Long user_id, String nom, String prenom, String mail, String motdepasse, List<Activite> activites) {
		super();
		this.user_id = user_id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.motdepasse = motdepasse;
		this.activites = activites;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public List<Activite> getActivites() {
		return activites;
	}
	public void setActivites(List<Activite> activites) {
		this.activites =  activites;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}





	public int getValidepic() {
		return validepic;
	}





	public void setValidepic(int validepic) {
		this.validepic = validepic;
	}





	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motdepasse="
				+ motdepasse + ", Sends=" + Sends + ", Recives=" + Recives + ", activites=" + activites + "]";
	}


	

	
	
}
