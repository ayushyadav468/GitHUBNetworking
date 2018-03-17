package com.example.ayushyadav.githubnetworking;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    ArrayAdapter<String> adapter;
    ArrayList<String> reposName = new ArrayList<>();
    ArrayList<GitHUBRepos> gitHUBReposList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);

        adapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, reposName);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchRepos("ayushyadav468");
            }
        });
    }

    private void fetchRepos(String userName) {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReposApi reposApi = retrofit.create(ReposApi.class);

        Call<ArrayList<GitHUBRepos>> call = reposApi.getGitHUBRepos(userName);

        call.enqueue(new Callback<ArrayList<GitHUBRepos>>() {
            @Override
            public void onResponse(Call<ArrayList<GitHUBRepos>> call, Response<ArrayList<GitHUBRepos>> response) {
                ArrayList<GitHUBRepos> arrayList = response.body();
                if(arrayList != null){
                    gitHUBReposList = arrayList;
                    for(int i = 0; i < arrayList.size(); i++){
                        GitHUBRepos gitHUBRepos = arrayList.get(i);
                        reposName.add(gitHUBRepos.getReposName());
                    }
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<GitHUBRepos>> call, Throwable t) {
                Log.d("Response","Failed Call");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
