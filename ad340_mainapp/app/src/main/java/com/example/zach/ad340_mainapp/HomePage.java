package com.example.zach.ad340_mainapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HomePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra("message", message);
        startActivity(intent);
    }

    public void getMovies (View view) {
        Intent intent = new Intent(this, MovieList.class);
        startActivity(intent);
    }

    public void onClickButton2(View view) {
        CharSequence toastText = "Button 2!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, toastText, duration);
        toast.show();
    }

    public void onClickButton3(View view) {
        CharSequence toastText = "Button 3!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, toastText, duration);
        toast.show();
    }

    public void onClickButton4(View view) {
        CharSequence toastText = "Button 4!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, toastText, duration);
        toast.show();
    }
}
