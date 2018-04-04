package com.example.sachin.forecast;

public class News {
    private  String title;
    private  String desc;
    private String image;
    private String full_url;

    public News(String title, String desc, String image,String full_url) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.full_url = full_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }
}
