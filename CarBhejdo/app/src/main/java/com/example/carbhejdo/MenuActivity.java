package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onFilterClick(View v){
        Intent filterini = new Intent(this,FilterActivity.class);
        startActivity(filterini);
    }

    public void onLogoClick(View v){
        Intent logoini = new Intent(this,MenuActivity.class);
        startActivity(logoini);
    }
}
