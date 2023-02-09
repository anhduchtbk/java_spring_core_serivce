package com.AIS.coreservice.model;

public class LoadPhoto {
    private String photoName;
    private String photoType;
    private String photoSize;
    private byte[] image;

    public LoadPhoto() {

    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public void setPhotoSize(String photoSize) {
        this.photoSize = photoSize;
    }

    public void setImage(byte[] photo) {
        this.image = photo;
    }

    public byte[] getImage() {
        return this.image;
    }

    public String getPhotoName() {
        return this.photoName;
    }

    public String getPhotoType() {
        return this.photoType;
    }

    public String getPhotoSize() {
        return this.photoSize;
    }
}
