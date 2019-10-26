package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BuyCarListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car_list);

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

    public void onFilterClick(View v){
        Intent filterini = new Intent(this,FilterActivity.class);
        startActivity(filterini);
    }

    public void onLogoClick(View v){
        Intent logoini = new Intent(this,MenuActivity.class);
        startActivity(logoini);
    }
}
