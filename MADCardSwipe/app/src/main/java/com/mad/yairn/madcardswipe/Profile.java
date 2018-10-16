package com.mad.yairn.madcardswipe;

public class Profile {

    private String name;
    private String imageUrl;
    private String position;

    public Profile(String name, String imageUrl, String position) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPosition() {
        return position;
    }
}
