package com.example.zach.ad340_mainapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

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
}
