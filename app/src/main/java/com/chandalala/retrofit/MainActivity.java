package com.chandalala.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private API_Interface api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        //Execute get requests
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api_interface = retrofit.create(API_Interface.class);

        getSocialMediaPost();

        getComments();

        createSocialMediaPost();

        deletePost();
    }

    private void deletePost() {

        Call<Void> call = api_interface.deleteSocialMediaPosts(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void createSocialMediaPost() {
        final SocialMediaPost socialMediaPost = new SocialMediaPost(23, "New Title", "New Text");

        Call<SocialMediaPost> socialMediaPostCall = api_interface.createSocialMediaPost(socialMediaPost);

        socialMediaPostCall.enqueue(new Callback<SocialMediaPost>() {
            @Override
            public void onResponse(Call<SocialMediaPost> call, Response<SocialMediaPost> response) {
                if (!response.isSuccessful()){
                    textView.setText(response.code());
                    return;
                }

                SocialMediaPost socialMediaPost1 = response.body();
                if (socialMediaPost1 != null) {
                    textView.setText(socialMediaPost1.getText());
                }
            }

            @Override
            public void onFailure(Call<SocialMediaPost> call, Throwable t) {

            }
        });

    }

    private void getComments() {
        Call<List<Comment>> call = api_interface.getComments(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

    }

    private void getSocialMediaPost() {
        //Executing the network request
        Call<List<SocialMediaPost>> call = api_interface.getSocialMediaPost();

        //runs on a separate thread
        call.enqueue(new Callback<List<SocialMediaPost>>() {
            @Override
            public void onResponse(Call<List<SocialMediaPost>> call, Response<List<SocialMediaPost>> response) {
                if (!response.isSuccessful()){
                    textView.setText("code: "+ response.code());
                    return;
                }

                List<SocialMediaPost> socialMediaPosts = response.body();
                StringBuilder stringBuilder = new StringBuilder();

                if (socialMediaPosts != null) {
                    for (SocialMediaPost socialMediaPost : socialMediaPosts) {
                        stringBuilder.append(socialMediaPost).append("/");
                    }
                }

                textView.setText(stringBuilder);

            }

            @Override
            public void onFailure(Call<List<SocialMediaPost>> call, Throwable t) {
                //Something went wrong with communication with the server
                //json object doesnt fit into what we are trying to pass it into
                textView.setText(t.getMessage());
            }
        });
    }
}
