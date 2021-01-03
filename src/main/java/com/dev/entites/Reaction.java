package com.dev.entites;

import java.io.Serializable;
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
@Table(name="reaction")
public class Reaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="reaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reaction_id;

	
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    
    @Column(name="reaction")
    private String reaction_type;
    
    
    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "post_id")
    private Post post;
    
    
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;
    
    
    
    
	public Reaction(long reaction_id, Date creationDateTime, String reaction_type, Post post, User user) {
		super();
		this.reaction_id = reaction_id;
		this.creationDateTime = creationDateTime;
		this.reaction_type = reaction_type;
		this.post = post;
		this.user = user;
	}



	public Reaction() {
		super();
	}
	
	

	public long getReaction_id() {
		return reaction_id;
	}

	public void setReaction_id(long reaction_id) {
		this.reaction_id = reaction_id;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getReaction_type() {
		return reaction_type;
	}

	public void setReaction_type(String reaction_type) {
		this.reaction_type = reaction_type;
	}

	@JsonIgnore
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}

