package com.AIS.coreservice.service;

import com.AIS.coreservice.model.LoadPhoto;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private GridFsTemplate template;
    @Autowired
    private GridFsOperations operations;

    public PhotoService() {

    }

    public String addPhoto(MultipartFile upload) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());
        Object photoID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        return photoID.toString();
    }

    public LoadPhoto downloadPhoto(String id) throws IOException {
        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));
        LoadPhoto loadPhoto = new LoadPhoto();
        if(gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadPhoto.setPhotoName(gridFSFile.getFilename());
            loadPhoto.setPhotoType((gridFSFile.getMetadata().get("_contentType").toString()));
            loadPhoto.setImage(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }
        return loadPhoto;
    }

    public void delete (String id) throws IOException {
        template.delete(new Query(Criteria.where("_id").is(id)));
    }

    public List<GridFSFile> get() throws IOException {
        GridFSFindIterable result = template.find(new Query(Criteria.where("length").gte(0)));
        List<GridFSFile> listOb = new ArrayList<>();
        //
        result.forEach(x -> listOb.add(x));//
        System.out.println(listOb.size());
        return listOb;
    }
}
