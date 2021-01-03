package com.dev.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "post" })
@Table(name="commentaire")
public class Commentaire {
	
	@Id
	@Column(name="commentaire_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentaire_id;
	
	@Column(name="body")
	private String body;
	
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    
    @ManyToOne
	@JoinColumn(name="createur",referencedColumnName = "user_id")
    private User Createur;
    
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name="post",referencedColumnName = "post_id")
    private Post post;

	
	public Commentaire() {
		super();
	}
	public Commentaire(long commentaire_id, String body, Date creationDateTime, User createur, Post post) {
		super();
		this.commentaire_id = commentaire_id;
		this.body = body;
		this.creationDateTime = creationDateTime;
		Createur = createur;
		this.post = post;
	}

	public long getCommentaire_id() {
		return commentaire_id;
	}

	public void setCommentaire_id(long commentaire_id) {
		this.commentaire_id = commentaire_id;
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
		Createur = createur;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
	
}
