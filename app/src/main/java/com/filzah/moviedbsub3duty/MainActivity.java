package com.filzah.moviedbsub3duty;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.provider.Settings;
import android.view.MenuItem;

import com.filzah.moviedbsub3duty.views.FragmentMovie;
import com.filzah.moviedbsub3duty.views.FragmentPopular;
import com.filzah.moviedbsub3duty.views.FragmentTvshow;
import com.filzah.moviedbsub3duty.views.FragmentUpcoming;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_language_setting){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movies) {
            FragmentMovie fragMovie = new FragmentMovie();
            fragMovie.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.frame,fragMovie,"Movie") .commit();
            getSupportFragmentManager().popBackStack();
            Toast.makeText(this, "THIS IS MOVIE", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tvshow) {
            FragmentTvshow fragTvshow = new FragmentTvshow();
            fragTvshow.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.frame,fragTvshow,"TV Show") .commit();
            getSupportFragmentManager().popBackStack();
            Toast.makeText(this, "THIS IS TV SHOW", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_popular) {
            FragmentPopular fragPopular = new FragmentPopular();
            fragPopular.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.frame,fragPopular,"Popular") .commit();
            getSupportFragmentManager().popBackStack();
            Toast.makeText(this, "THIS IS POPULAR", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_upcoming) {
            FragmentUpcoming fragUpcoming = new FragmentUpcoming();
            fragUpcoming.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.frame,fragUpcoming,"Upcoming") .commit();
            getSupportFragmentManager().popBackStack();
            Toast.makeText(this, "THIS IS UPCOMING", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "THIS IS SHARE", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "THIS IS SEND", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
