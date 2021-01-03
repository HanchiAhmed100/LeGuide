package com.dev.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@JsonIgnoreProperties(value = { "post" })
@Table(name = "activite")
public class Activite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long activite_id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "cover",columnDefinition = "varchar(255) default 'Default_cover.jpg'")
	private String cover;

	@Column(name = "link",nullable = true)
	private String link;
	
	@Column(name = "pdf",nullable = true)
	private String pdf;
	
	@ManyToMany
	private List<User> users;

	
	@ManyToOne
    @JoinColumn(name="activite_admin",referencedColumnName = "user_id")
	private User Admin;
	
	
	@OneToMany(mappedBy = "activite")
	@JsonIgnore
	private List<Post> Post;
	
	@OneToMany(mappedBy = "activite")
	@JsonIgnore
	private List<Evenement> Evenements;
	
	@OneToMany(mappedBy= "activite")
	@JsonIgnore
	private List<Maps> maps;
	
	public Activite() {
		super();
	}
	
	

	public Activite(Long activite_id, String type, String nom, String description, String cover, String link,
			String pdf, List<User> users, List<com.dev.entites.Post> post) {
		super();
		this.activite_id = activite_id;
		this.type = type;
		this.nom = nom;
		this.description = description;
		this.cover = cover;
		this.link = link;
		this.pdf = pdf;
		this.users = users;
		Post = post;
	}

	


	public Activite(Long activite_id, String type, String nom, String description, String cover, String link, String pdf) {
		super();
		this.activite_id = activite_id;
		this.type = type;
		this.nom = nom;
		this.description = description;
		this.cover = cover;
		this.link = link;
		this.pdf = pdf;
	}

	public Activite(Long activite_id, String type, String nom, String description, String cover, String link, String pdf,
			List<User> users) {
		super();
		this.activite_id = activite_id;
		this.type = type;
		this.nom = nom;
		this.description = description;
		this.cover = cover;
		this.link = link;
		this.pdf = pdf;
		this.users = users;
	}

	public Long getActivite_id() {
		return activite_id;
	}

	public void setActivite_id(Long activite_id) {
		this.activite_id = activite_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
	public List<Post> getPost() {
		return Post;
	}



	public void setPost(List<Post> post) {
		Post = post;
	}



	public User getAdmin() {
		return Admin;
	}



	public void setAdmin(User admin) {
		Admin = admin;
	}



	@Override
	public String toString() {
		return "Activite [activite_id=" + activite_id + ", type=" + type + ", nom=" + nom + ", description="
				+ description + ", cover=" + cover + ", link=" + link + ", pdf=" + pdf + ", Users=" + users + "]";
	}
	

	
	
}
