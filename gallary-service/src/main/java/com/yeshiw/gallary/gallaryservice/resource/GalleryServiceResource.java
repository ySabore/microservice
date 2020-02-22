package com.yeshiw.gallary.gallaryservice.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yeshiw.gallary.gallaryservice.model.Gallery;
import com.yeshiw.gallary.gallaryservice.model.ImageResponse;

@RestController
@RequestMapping("/rest/gallery")
public class GalleryServiceResource {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different
		// ports. We load balance among them, and display which instance received the request.
		 return "Hello from Gallery Service running at port: ";
	}


	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/{_id}")
	private Gallery getGalleryById(@PathVariable("_id") final String _id) {

		Gallery gallery = new Gallery();
		gallery.setId(_id);

		ResponseEntity<ImageResponse> imageResponse = restTemplate.exchange("http://image-service/rest/mongodb/image/" + _id,
				HttpMethod.GET, null, ImageResponse.class);
		
		ImageResponse image = imageResponse.getBody();

		List<ImageResponse> images = new ArrayList<ImageResponse>();
		images.add(image);

		gallery.setImages(images);

		return gallery;

	}

	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/images")
	public Gallery getAllImagesInGallary() {
		
		Gallery gallery = new Gallery();

		@SuppressWarnings("unchecked")
		List<ImageResponse> images = restTemplate.getForObject("http://image-service/rest/mongodb/images/", List.class);

		gallery.setImages(images);

		return gallery;

	}
	
	// a fallback method to be called if failure happened
	public Gallery fallback(Throwable hystrixCommand) {
		return new Gallery();
	}
	

	

}
