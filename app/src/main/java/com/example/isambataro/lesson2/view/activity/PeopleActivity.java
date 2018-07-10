package com.example.isambataro.lesson2.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.isambataro.lesson2.R;
import com.example.isambataro.lesson2.api.NetworkUtils;
import com.example.isambataro.lesson2.model.DataMovies;
import com.example.isambataro.lesson2.model.DataPeople;
import com.example.isambataro.lesson2.view.adapter.MovieAdapter;
import com.example.isambataro.lesson2.view.adapter.PeopleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isambataro on 22/06/18.
 */

public class PeopleActivity extends BaseActivity{

    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private PeopleAdapter mPeopleAdapter;
    private List<DataPeople> mPeopleList;
    private PeopleAsyncTask peopleAsyncTask = new PeopleAsyncTask();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("SAMBA ONCREATE");
        setContentView(R.layout.activity_people);
        initView();
        makeMovieSearchQuery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        peopleAsyncTask.onCancelled();
    }

    private void initView() {
        System.out.println("SAMBA INITVIEW");
        mToolbar = findViewById(R.id.toolbar_people);
        setSupportActionBar(mToolbar);
        mProgressBar = findViewById(R.id.peopleProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView = findViewById(R.id.people_recyclerview);
        mPeopleList = new ArrayList<>();
        mPeopleAdapter = new PeopleAdapter(mPeopleList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mPeopleAdapter);
    }

    private void makeMovieSearchQuery() {
        String movieQuery = "people";
        URL movieSearchUrl = NetworkUtils.buildUrl(movieQuery);
        peopleAsyncTask.execute(movieSearchUrl);
    }

    private  ArrayList<DataPeople> populateDataMovie(String urlRespond){
        System.out.println("SAMBA POPULATEMOVIELIST");
        ArrayList<DataPeople> dataPeopleList = new ArrayList<>();
        System.out.printf("SAMBA 1");
        try {
            JSONObject jsonPeople = new JSONObject(urlRespond);
            JSONArray jsonArrayPeople = jsonPeople.getJSONArray("results");
            for (int i = 0 ; i < jsonArrayPeople.length() ; i++) {
                DataPeople peopleInfo = new DataPeople();
                JSONObject jsonPeopleObject = jsonArrayPeople.getJSONObject(i);
                peopleInfo.setName(jsonPeopleObject.get("name").toString());
                peopleInfo.setGender(jsonPeopleObject.get("gender").toString());
                dataPeopleList.add(peopleInfo);
            }
        } catch (JSONException e) {
            System.out.printf("SAMBA JSONEXCEPTION");
            e.printStackTrace();
        }
        return dataPeopleList;
    }


    private class PeopleAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            System.out.println("SAMBA CALLING DOINBACKGROUND");
            URL searchUrl = params[0];
            String peoplesResults = null;
            try {
                peoplesResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception r) {
                r.printStackTrace();
            }
            return peoplesResults;
        }

        // COMO CUANDO LA ACTIVITY MUERE LA TASK QUEDA ATACHEADA EN EL
        // ONDESTROY HAY QUE CANCELAR.
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
            mPeopleAdapter.setItem(populateDataMovie(result));
            mPeopleAdapter.notifyDataSetChanged();
            System.out.println("SAMBA adapter size: " + mPeopleAdapter.getItemCount());
        }
    }
}
