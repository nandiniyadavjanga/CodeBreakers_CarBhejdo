package com.example.carbhejdo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.parse.ParseUser;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Model favourite_model;
    private DrawerLayout mDrawerLayout;
    private FavouritesAdapter fav_server = null;
    private RecyclerView fav_recycler = null;
    private ActionBarDrawerToggle mToggle;
    private GestureDetectorCompat gesture_detector = null;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = fav_recycler.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = fav_recycler.getChildViewHolder(view);
                if (holder instanceof FavouritesAdapter.FavouritesViewHolder
                ) {
                    int position = holder.getAdapterPosition();

                    // handle single tap
                    Log.d("click", "clicked on item "+ position);
//                    TextView outputTV = findViewById(R.id.outputTV);
//                    outputTV.setText("Clicked on " + myModel.thePlanets.get(position).name);
//                    // Remove the selected data from the model
//                    myModel.thePlanets.remove(position);
//                    planetServer.notifyItemRemoved(position);

                    return true;  // Use up the tap gesture
                }
            }

            // we didn't handle the gesture so pass it on
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
//        navigationView.setNavigationItemSelectedListener(this);
        favourite_model = Model.getModel();
        fav_server = new FavouritesAdapter(favourite_model,this);
        fav_recycler = findViewById(R.id.Recycler);
        fav_recycler.setAdapter(fav_server);
       // mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        fav_recycler.setLayoutManager(linearLayoutManager);

        gesture_detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        fav_recycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return gesture_detector.onTouchEvent(e);
            }
        });
        Intent in=getIntent();
        String nam=in.getStringExtra("CurrentName");
        System.out.println(nam);
        ArrayList<String> names=new ArrayList<String>();
        names.add(nam);
        favourite_model.setFavourites(names);

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
}
