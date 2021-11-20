package com.example.utspam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMovieList(View view) {
        Intent intent = new Intent(this, ListVideoActivity.class);
        startActivity(intent);
    }

    public void showAboutMe(View view) {
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }
}