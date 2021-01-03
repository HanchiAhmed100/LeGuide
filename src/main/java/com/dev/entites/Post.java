package com.dev.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="post")
public class Post implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long post_id;
	
	@Column(name="body")
	private String body;
	
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    
    @ManyToOne
	@JoinColumn(name="createur",referencedColumnName = "user_id")
    private User Createur;
    
    @ManyToOne
    @JoinColumn(name="activite",referencedColumnName = "activite_id")
    private Activite activite;

    
	@OneToMany(mappedBy="post")
	private List<Commentaire> Commentaires;
	
	
	@OneToMany(mappedBy = "post")
	private List<Reaction> reactions;
	
	    
    
    
    public Post() {
		super();
	}

    
	public Post(long post_id, String body, User createur, Activite activite) {
		super();
		this.post_id = post_id;
		this.body = body;
		this.Createur = createur;
		this.activite = activite;
	}


	public Post(long post_id, String body, Date creationDateTime, User createur, Activite activite,
			List<Commentaire> commentaires) {
		super();
		this.post_id = post_id;
		this.body = body;
		this.creationDateTime = creationDateTime;
		Createur = createur;
		this.activite = activite;
		Commentaires = commentaires;
	}


	public Post(long post_id, String body, Date creationDateTime, User createur, Activite activite) {
		super();
		this.post_id = post_id;
		this.body = body;
		this.creationDateTime = creationDateTime;
		this.Createur = createur;
		this.activite = activite;
	}


	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public User getCreateur() {
		return Createur;
	}

	public void setCreateur(User createur) {
		this.Createur = createur;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}


	public List<Commentaire> getCommentaires() {
		return this.Commentaires;
	}


	public void setCommentaires(List<Commentaire> commentaires) {
		this.Commentaires = commentaires;
	}


	public List<Reaction> getReactions() {
		return reactions;
	}


	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	
	

}
