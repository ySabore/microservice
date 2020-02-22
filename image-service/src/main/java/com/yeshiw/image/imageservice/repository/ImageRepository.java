package com.yeshiw.image.imageservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yeshiw.image.imageservice.model.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
	
	Image findBy_id(ObjectId _id);

}
