package com.zachworks.school.ad340_mainapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();

        String title = intent.getStringExtra("Title");
        String year = intent.getStringExtra("Year");
        String director = intent.getStringExtra("Director");
        String desc = intent.getStringExtra("Desc");

        TextView movieTitleView = findViewById(R.id.movie_title2);
        TextView yearView   = findViewById(R.id.movie_year2);
        TextView directorView = findViewById(R.id.movie_director);
        TextView descView = findViewById(R.id.movie_desc);

        movieTitleView.setText(title);
        yearView.setText(year);
        directorView.setText(director);
        descView.setText(desc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(MovieDetail.this, "You clicked Settings!",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
