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
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BuyCarListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    Button logo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car_list);

        mDrawerLayout = findViewById(R.id.draw);

        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        Intent carini = new Intent(this,Singlecar_Activity.class);
        startActivity(carini);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
