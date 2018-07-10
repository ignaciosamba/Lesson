package com.example.isambataro.lesson2.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.example.isambataro.lesson2.R;

/**
 * Created by isambataro on 10/07/18.
 */

public class TableActivity extends BaseActivity {
    private TableLayout mTable;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_soccer);
        mTable = findViewById(R.id.table_soccer);
        mToolbar = findViewById(R.id.toolbar_table);
        setSupportActionBar(mToolbar);
    }
}
