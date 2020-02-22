package com.yeshiw.image.imageservice.resource;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeshiw.image.imageservice.model.Image;
import com.yeshiw.image.imageservice.model.Images;
import com.yeshiw.image.imageservice.repository.ImageRepository;

@RestController
@RequestMapping("/rest/mongodb")
public class ImageServiceResource {

	@Autowired
	private ImageRepository imagesRepository;

	public ImageServiceResource(ImageRepository imagesRepository) {

		this.imagesRepository = imagesRepository;
	}

	@GetMapping("/image/{_id}")
	public Image getImageById(@PathVariable("_id") final ObjectId _id){
		return imagesRepository.findBy_id(_id);
	}
	
	@GetMapping("/images")
	public List<Image> getImages() {

		return imagesRepository.findAll();

	}

	@PostMapping("/add")
	public List<Image> add(@RequestBody final Images images) {

		images.getImages()
		.stream()
		.map(image -> {
			image.set_id(ObjectId.get());
			return image;
		})
		.forEach(image -> imagesRepository.save(image));

		return imagesRepository.findAll();

	}
	
	@PutMapping("/{_id}")
	public Image modifyImageById(@PathVariable("_id") final ObjectId _id, @Valid @RequestBody final Image image){
		image.set_id(_id);
		imagesRepository.save(image);
		return imagesRepository.findBy_id(_id);
		
	}
	
	@PostMapping("/delete/{_id}")
	public List<Image>  delete(@PathVariable  final ObjectId _id){
		imagesRepository.delete(imagesRepository.findBy_id(_id));
		
		return imagesRepository.findAll();
	}
}
