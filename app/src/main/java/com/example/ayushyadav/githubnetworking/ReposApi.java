package com.example.ayushyadav.githubnetworking;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ayushyadav on 17/03/18.
 */

public interface ReposApi {

    @GET("users/{userName}/repos")
    Call<ArrayList<GitHUBRepos>> getGitHUBRepos(@Path("userName") String USERNAME);

}
