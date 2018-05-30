package com.zachworks.school.ad340_mainapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    // Wrapper class for shared preferences
    private SharedPreferences mySharedPref;
    private SharedPreferencesWrapper mySharedPrefWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Populate text field w/ shared preference if able
        Context context = HomePage.this;
        mySharedPref = context.getSharedPreferences(getString(
                R.string.saved_message), Context.MODE_PRIVATE);
        mySharedPrefWrapper = new SharedPreferencesWrapper(mySharedPref);
        EditText editText = findViewById(R.id.editText);
        editText.setText(mySharedPrefWrapper.getPreferences());


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Create Drawer and Listener
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, myToolbar,
                R.string.nav_open, R.string.nav_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                Toast toast = Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.nav_about:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.nav_movies:
                intent = new Intent(this, MovieList.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (validateMessage(message)) {
            mySharedPrefWrapper.savePreferences(message);
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra("message", message);
            startActivity(intent);
        } else {
            CharSequence toastText = "Invalid input.";
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validateMessage (String message) {
         return !(message.isEmpty() || message.matches("^\\s*$"));
    }

    public void getMovies(View view) {
        Intent intent = new Intent(this, MovieList.class);
        startActivity(intent);
    }

    public void onClickButton2(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void onClickButton3(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void onClickButton4(View view) {
        CharSequence toastText = "Button 4!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, toastText, duration);
        toast.show();
    }
}
