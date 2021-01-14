package com.example.bakingapp.model;

public class Step {
    private long id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String value) { this.shortDescription = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getVideoURL() { return videoURL; }
    public void setVideoURL(String value) { this.videoURL = value; }

    public String getThumbnailURL() { return thumbnailURL; }
    public void setThumbnailURL(String value) { this.thumbnailURL = value; }
}
