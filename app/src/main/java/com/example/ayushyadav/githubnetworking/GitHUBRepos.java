package com.example.ayushyadav.githubnetworking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ayushyadav on 17/03/18.
 */

public class GitHUBRepos {

    @SerializedName("name")
    private String reposName;

    public String getReposName() {
        return reposName;
    }

    public void setReposName(String reposName) {
        this.reposName = reposName;
    }

}
