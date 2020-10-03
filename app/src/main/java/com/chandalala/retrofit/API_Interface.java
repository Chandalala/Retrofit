package com.chandalala.retrofit;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/*Represent our api provider*/
public interface API_Interface {

    @GET("posts") //put the end of the url to be appended to baseurl for example
    //baseurl http//www.chandalala.com/posts
    Call<List<SocialMediaPost>> getSocialMediaPost();

    @GET("posts")           //posts?userId=1
    Call<List<SocialMediaPost>> getSocialMediaPost(@Query("userId") int userID); //retrofit will automatically add a ques mark and an equal sign
                            //userId same as on URL

    @GET("posts") //posts?userId=1&_sort=id&_order=desc
    Call<List<SocialMediaPost>> getSocialMediaPost(
            @Query("userId") int userID,
            @Query("_sort") String sort,
            @Query("_order") String order);

    @GET("posts")
    Call<List<SocialMediaPost>> getSocialMediaPost(@QueryMap Map<String, String> params);

    //@GET("posts/2/comments")
    @GET("posts/{id}/comments") // to dynamically put 2 as above we use curly braces as shown and pass the id as parameter
    Call<List<Comment>> getComments(@Path("id") int postID);// annotate to map postID to id

    //Sending data
    @POST("posts")
    Call<SocialMediaPost>  createSocialMediaPost(@Body SocialMediaPost socialMediaPost);

    @FormUrlEncoded
    @POST("posts")
    Call<SocialMediaPost> createSocialMediaPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<SocialMediaPost> createSocialMediaPost(@FieldMap Map<String, String> fields);

    //PUT, PATCH AND DELETE are used for single item data

    //Used for replace an existing post, if not it creates a new one
    @PUT("posts/{id}")
    Call<SocialMediaPost> createSingleSocialMediaPost(@Path("id") int userId, @Body SocialMediaPost socialMediaPost);

    //Updates an existing post
    @PATCH("posts/{id}")
    Call<SocialMediaPost> patchSingleSocialMediaPost(@Path("id") int userId, @Body SocialMediaPost socialMediaPost);

    @DELETE("posts/{id}")
    Call<Void> deleteSocialMediaPosts(@Path("id") int id);

}
