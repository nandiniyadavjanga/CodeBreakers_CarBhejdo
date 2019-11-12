package com.example.carbhejdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity {
   private DrawerLayout mDrawerLayout;
   private ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
//        mDrawerLayout = findViewById(R.id.draw);
//        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);
//        mDrawerLayout.addDrawerListener(mToogle);
//        mToogle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
