package com.dev.entites;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="amis")
public class Amis {

	@Id
	@Column(name="amis_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long relation_id;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="user_sender",referencedColumnName = "user_id")
	private User Send;
	
	@ManyToOne
	@JoinColumn(name="user_reciver",referencedColumnName = "user_id")
	private User Recive;
	
	
	

	public Amis(int status2, User user_sender, User user_reciver) {
		super();
		this.status = status2;
		this.Send = user_sender;
		this.Recive = user_reciver;
	}



	public Amis(long relation_id, int status, User send, User recive) {
		super();
		this.relation_id = relation_id;
		this.status = status;
		this.Send = send;
		this.Recive = recive;
	}

	
	public Amis() {
		super();
	}



	public long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(long relation_id) {
		this.relation_id = relation_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getSender() {
		return Send;
	}

	public void setSend(User sender) {
		this.Send = sender;
	}

	public User getReciver() {
		return Recive;
	}

	public void setReciver(User reciver) {
		this.Recive = reciver;
	}



	@Override
	public String toString() {
		return "Amis [relation_id=" + relation_id + ", status=" + status + ", Send=" + Send + ", Recive=" + Recive
				+ "]";
	}
	
	
	
	
	
}
