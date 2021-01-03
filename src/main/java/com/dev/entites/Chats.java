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

@Entity
@Table(name="chats")
public class Chats {
		

	@Id
	@Column(name="chats_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long chats_id;
	
	@Column(name="msg")
	private String msg;
	
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    
	
	@ManyToOne
	@JoinColumn(name="user_sender",referencedColumnName = "user_id")
	private User Send_msg;
	
	@ManyToOne
	@JoinColumn(name="user_reciver",referencedColumnName = "user_id")
	private User Recive_msg;

	public long getChats_id() {
		return chats_id;
	}

	public void setChats_id(long chats_id) {
		this.chats_id = chats_id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public User getSend_msg() {
		return Send_msg;
	}

	public void setSend_msg(User send_msg) {
		Send_msg = send_msg;
	}

	public User getRecive_msg() {
		return Recive_msg;
	}

	public void setRecive_msg(User recive_msg) {
		Recive_msg = recive_msg;
	}
	
	
	

}
