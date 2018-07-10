package com.example.isambataro.lesson2.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.isambataro.lesson2.R;

public class BaseActivity extends AppCompatActivity {

    // Activity code here

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
        if (id == R.id.action_movies) {
//            Intent share = new Intent(android.content.Intent.ACTION_SEND);
//            share.setType("text/plain");
//            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//
//            // Add data to the intent, the receiving app will decide
//            // what to do with it.
//            share.putExtra(Intent.EXTRA_TEXT, "TEXT OF API");
//
//            startActivity(Intent.createChooser(share, "Share link!"));
            Intent intent = new Intent(BaseActivity.this, MoviesAvtivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_people) {
            Intent intent = new Intent(BaseActivity.this, PeopleActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_table) {
            Intent intent = new Intent(BaseActivity.this, TableActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}