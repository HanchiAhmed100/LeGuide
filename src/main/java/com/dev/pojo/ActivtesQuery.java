package com.dev.pojo;

import java.util.HashMap;
import java.util.List;

public class ActivtesQuery {

	private List<HashMap<String,Object>> list;


	public ActivtesQuery(List<HashMap<String, Object>> list) {
		super();
		this.list = list;
	}
	

	public ActivtesQuery() {
		// TODO Auto-generated constructor stub
	}


	public List<HashMap<String, Object>> getList() {
		return list;
	}

	public void setList(List<HashMap<String, Object>> list) {
		this.list = list;
	}

	

	
}
