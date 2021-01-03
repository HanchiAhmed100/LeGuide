package com.dev.pojo;

public class Posts_POJO {

	private long post_id;
	private String Body;

	
	private long activite_id;
	private String activite_cover;
	private String activite_type;
	private String activite_name;
	
	
	private long creator_id;
	private String creator_name;
	
	
	public Posts_POJO() {
		super();
	}
	
	


	public Posts_POJO(long post_id, String body, long activite_id, String activite_cover, String activite_type,
			String activite_name, long creator_id, String creator_name) {
		super();
		this.post_id = post_id;
		Body = body;
		this.activite_id = activite_id;
		this.activite_cover = activite_cover;
		this.activite_type = activite_type;
		this.activite_name = activite_name;
		this.creator_id = creator_id;
		this.creator_name = creator_name;
	}




	public long getPost_id() {
		return post_id;
	}


	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}


	public String getBody() {
		return Body;
	}


	public void setBody(String body) {
		Body = body;
	}


	public long getActivite_id() {
		return activite_id;
	}


	public void setActivite_id(long activite_id) {
		this.activite_id = activite_id;
	}


	public String getActivite_cover() {
		return activite_cover;
	}


	public void setActivite_cover(String activite_cover) {
		this.activite_cover = activite_cover;
	}


	public String getActivite_type() {
		return activite_type;
	}


	public void setActivite_type(String activite_type) {
		this.activite_type = activite_type;
	}


	public String getActivite_name() {
		return activite_name;
	}


	public void setActivite_name(String activite_name) {
		this.activite_name = activite_name;
	}


	public long getCreator_id() {
		return creator_id;
	}


	public void setCreator_id(long creator_id) {
		this.creator_id = creator_id;
	}


	public String getCreator_name() {
		return creator_name;
	}


	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}



	
	
	
}
