package com.example.carbhejdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class BuyCarListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    Button logo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buy_car_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);





        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        List<ModelClass> modelClassList = new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.altima, "Altima 800", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.kia, "Kia", "Price: $72000","Miles Driven: 11000" ));
        modelClassList.add(new ModelClass(R.drawable.altima, "Altima 800", "Price: $1300","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.kia, "Kia", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.altima, "Altima 800", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.kia, "Kia", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.altima, "Altima 800", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.kia, "Kia", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.altima, "Altima 800", "Price: $5000","Miles Driven: 11000"));
        modelClassList.add(new ModelClass(R.drawable.kia, "Kia", "Price: $5000","Miles Driven: 11000"));

        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void onCarClick(View v){
        Intent ini = new Intent(this,CarInfoActivity.class);
        startActivity(ini);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id==R.id.profile_edit){
            Intent ini = new Intent(this,sellerprof.class);
            startActivity(ini);
            //Toast.makeText(this,"This is profile",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}


