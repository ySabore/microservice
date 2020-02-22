package com.yeshiw.gallary.gallaryservice.model;


public class ImageResponse {
	
	private String _id;
	
	private String category;
	private String name;
	private String url;
	private String port;
	
	//constructor
	public ImageResponse() {
		
	}
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	// ObjectId needs to be converted to string
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

}
