package com.example.isambataro.lesson2.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.isambataro.lesson2.view.adapter.MovieAdapter;
import com.example.isambataro.lesson2.R;
import com.example.isambataro.lesson2.api.NetworkUtils;
import com.example.isambataro.lesson2.model.DataMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isambataro on 18/06/18.
 */

public class MoviesAvtivity extends BaseActivity{

    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private List<DataMovies> mMovieList;
    private MovieAsyncTask movieAsyncTask = new MovieAsyncTask();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("SAMBA ONCREATE");
        setContentView(R.layout.activity_movie);
        initView();
        makeMovieSearchQuery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieAsyncTask.onCancelled();
    }

    private void initView() {
        System.out.println("SAMBA INITVIEW");
        mToolbar = findViewById(R.id.toolbar_movie);
        setSupportActionBar(mToolbar);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = findViewById(R.id.movie_recyclerview);
        mMovieList = new ArrayList<>();
        mMovieAdapter = new MovieAdapter(mMovieList/*, this*/);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    private void makeMovieSearchQuery() {
        String movieQuery = "films";
        URL movieSearchUrl = NetworkUtils.buildUrl(movieQuery);
        movieAsyncTask.execute(movieSearchUrl);
    }

    private  ArrayList<DataMovies> populateDataMovie(String urlRespond){
        System.out.println("SAMBA POPULATEMOVIELIST");
        ArrayList<DataMovies> dataMoviesList = new ArrayList<>();
        System.out.printf("SAMBA 1");
        try {
            JSONObject jsonMovie = new JSONObject(urlRespond);
            JSONArray jsonArrayMovies = jsonMovie.getJSONArray("results");
            for (int i = 0 ; i < 7 ; i++) {
                JSONObject jsonMovieObject = jsonArrayMovies.getJSONObject(i);
                DataMovies movieInfo = new DataMovies();
                movieInfo.setTitle(jsonMovieObject.get("title").toString());
                movieInfo.setDirector(jsonMovieObject.get("director").toString());
                movieInfo.setEpisodeid(jsonMovieObject.get("episode_id").toString());
                movieInfo.setProducer(jsonMovieObject.get("producer").toString());
                movieInfo.setRealeaseDate(jsonMovieObject.get("release_date").toString());
                dataMoviesList.add(movieInfo);
                System.out.printf("SAMBA JSONMOVIE: " + jsonMovieObject.get("title").toString() + "\n");
            }
        } catch (JSONException e) {
            System.out.printf("SAMBA JSONEXCEPTION");
            e.printStackTrace();
        }
        return dataMoviesList;
    }


    private class MovieAsyncTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            System.out.println("SAMBA CALLING DOINBACKGROUND");
            URL searchUrl = params[0];
            String moviesResults = null;
            try {
                moviesResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception r) {
                r.printStackTrace();
            }
            return moviesResults;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("SAMBA ONPOSTEXECUTE");
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mMovieAdapter.setItem(populateDataMovie(result));
            mMovieAdapter.notifyDataSetChanged();
            System.out.println("SAMBA adapter size: " + mMovieAdapter.getItemCount());
        }
    }
}
