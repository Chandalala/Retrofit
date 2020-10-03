package com.chandalala.retrofit;

import com.google.gson.annotations.SerializedName;

class Comment {

    private int postID, id;
    private String name, email;

    @SerializedName("body")//If json key differs from object key
    private String text;

    public int getPostID() {
        return postID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
