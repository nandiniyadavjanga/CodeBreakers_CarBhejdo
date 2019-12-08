package com.example.carbhejdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import com.parse.ParseUser;


public class Singlecar_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ModelClass mModelClass;
    private ImageView carImage;
    private TextView carName, carPrice, distance_travelled, condition, userReview, companyReview, owner_name, owner_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlecar_);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        carImage = findViewById(R.id.imageView6);
        carName = findViewById(R.id.car_name);
        carPrice = findViewById(R.id.car_price);
        distance_travelled = findViewById(R.id.distance_travelled);

        owner_name = findViewById(R.id.owner_name);
        owner_contact = findViewById(R.id.owner_contact);


        owner_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("object_id", "in call onclik");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String mobile = owner_contact.getText().toString();
                String[] obile_data = mobile.split(":");
                intent.setData(Uri.parse("tel:" + obile_data[1].trim()));
                startActivity(intent);


            }
        });

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
            Log.d("object_id", mModelClass.getUser_object_id());



//            ParseQuery<ParseObject> ask = ParseQuery.getQuery("User");
//            ask.whereEqualTo("objectId", mModelClass.getUser_object_id());
//            ask.findInBackground(new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    for (ParseObject bird: objects){
//                        String owner_name_back4app = bird.getString("username");
//                        String current = owner_name.getText().toString();
//                        current = current + owner_name_back4app;
//                        owner_name.setText(current);
//                        String phone = bird.getString("Mobile");
//                        String current_contact = owner_contact.getText().toString();
//                        current_contact = current_contact + phone;
//                        owner_contact.setText(current_contact);
//                    }
//                }
//            });



            ParseUser user = ParseUser.getCurrentUser();
            String mobile = user.get("Mobile").toString();
            String owner_name_back = user.getUsername().toString();
            String current_contact = owner_contact.getText().toString();
            current_contact = current_contact + mobile;
            owner_contact.setText(current_contact);
            String current_name = owner_name.getText().toString();
            current_name = current_name + owner_name_back;
            owner_name.setText(current_name);
            carName.setText(mModelClass.getTitle());
            carPrice.setText(mModelClass.getBody());
            distance_travelled.setText(mModelClass.getYear());

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



