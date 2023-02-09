package com.AIS.coreservice.controller;

import com.AIS.coreservice.model.LoadPhoto;
import com.AIS.coreservice.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("file")
public class FileController {
    @Autowired
    private PhotoService photoService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        return new ResponseEntity<>(photoService.addPhoto(file), HttpStatus.OK);
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        LoadPhoto loadFile = photoService.downloadPhoto(id);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadFile.getPhotoType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getPhotoName() + "\"").body(new ByteArrayResource(loadFile.getImage()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws IOException {
        photoService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList() throws IOException {
        return new ResponseEntity<>(photoService.get(), HttpStatus.OK);
    }

}
