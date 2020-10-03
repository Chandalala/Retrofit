package com.chandalala.retrofit;


import com.google.gson.annotations.SerializedName;

/*Each json object represents a post object in this case*/
public class SocialMediaPost {

    private int userId, id;
    private String title;

    //Used for sending to server
    public SocialMediaPost(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    @SerializedName("body")//If json key differs from object key
    private String text;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }


}
