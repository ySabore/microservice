package com.yeshiw.image.imageservice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document(collection ="Image")
public class Image {
	
	@Id
	private ObjectId _id;
	
	private String category;
	private String name;
	private String url;
	private String port;
	
	//constructor
	public Image() {
		
	}

	public Image(ObjectId _id, String category, String name, String url,String port) {
		
		this._id = _id;
		this.category = category;
		this.name = name;
		this.url = url;
		this.port = port;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	// ObjectId needs to be converted to string
	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
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
