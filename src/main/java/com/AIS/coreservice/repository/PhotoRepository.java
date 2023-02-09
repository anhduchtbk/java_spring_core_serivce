package com.AIS.coreservice.repository;

import com.AIS.coreservice.model.LoadPhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<LoadPhoto, String> {
//    public String addPhoto();
//    public Photo getPhoto();
}
