package com.yeshiw.gallary.gallaryservice.model;

import java.awt.Image;
import java.util.List;

public class Gallery {
	
	private String id;
	private List<ImageResponse> images;
		
	public Gallery() {
		
	}

	public Gallery(String id, List<ImageResponse> images) {
		super();
		this.id = id;
		this.images = images;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ImageResponse> getImages() {
		return images;
	}

	public void setImages(List<ImageResponse> images) {
		this.images = images;
	}
	
	
	
	
	

}
