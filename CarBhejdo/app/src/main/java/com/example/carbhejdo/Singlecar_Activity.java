package com.example.carbhejdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;

public class Singlecar_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ModelClass mModelClass;
    private ImageView carImage;
    private TextView carName, carPrice,distance_travelled,condition,userReview,companyReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecar_);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        carImage = findViewById(R.id.imageView6);
        carName = findViewById(R.id.car_name);
        carPrice = findViewById(R.id.car_price);
        distance_travelled = findViewById(R.id.distance_travelled);
        condition = findViewById(R.id.condition);
        userReview = findViewById(R.id.userReview);
        companyReview = findViewById(R.id.companyReview);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        if(getIntent() != null){
            if(getIntent().hasExtra("ModelData")){
                mModelClass = (ModelClass) getIntent().getSerializableExtra("ModelData");
            }
        }
        if(mModelClass != null && (mModelClass.getImageUrl() != null || !mModelClass.getImageUrl().isEmpty())){
            Glide.with(carImage.getContext()).load(mModelClass.getImageUrl()).into(carImage);
        }
        if (mModelClass != null && mModelClass.getBody() != null && mModelClass.getBody() != null) {
            carName.setText(mModelClass.getTitle());
            carPrice.setText(mModelClass.getBody());
            distance_travelled.setText(mModelClass.getYear());
            condition.setText(mModelClass.getYear());
            userReview.setText(mModelClass.getBody());
            companyReview.setText(mModelClass.getLocation());
        }
    }

    public void onFilterClick(View v){
        Intent filterini = new Intent(this,FilterActivity.class);
        startActivity(filterini);
    }


    public void sellerprof(View view) {
        Intent sprof = new Intent(this,sellerprof.class);
        startActivity(sprof);
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

        }
        if(id==R.id.buycar){
            Intent ini = new Intent(this,BuyCarListActivity.class);
            startActivity(ini);

        }
        if(id==R.id.sellcar){
            Intent ini = new Intent(this,CarInfoActivity.class);
            startActivity(ini);

        }
        if(id==R.id.favourites){
            Intent ini = new Intent(this,FavouritesActivity.class);
            startActivity(ini);
        }
        if(id==R.id.faq){
            Intent ini = new Intent(this,FaqActivity.class);
            startActivity(ini);
        }
        if(id==R.id.logout){
            ParseUser.logOut();
            Intent ini = new Intent(this,SIgninSignupActivity.class);
            startActivity(ini);
        }
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(40.330020, -94.873210);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}



