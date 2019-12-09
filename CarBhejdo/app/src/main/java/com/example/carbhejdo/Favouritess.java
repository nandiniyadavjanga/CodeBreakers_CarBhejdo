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

import com.google.android.material.navigation.NavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Favouritess extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ArrayList<String> car=new ArrayList<>();
    ArrayList<String> price=new ArrayList<>();
    ArrayList<String> image=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritess);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.recycler_View);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        final List<ModelClass_fav> modelClass_favList = new ArrayList<>();
        ParseQuery<ParseObject> fav=ParseQuery.getQuery("userCarFav");
        fav.whereEqualTo("user_object_id",SIgninSignupActivity.object_id);
        fav.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null)
                {
                    for(ParseObject obj:objects)
                    {
                        car.add(obj.getString("car_name"));
                        price.add(obj.getString("price"));
                        image.add(obj.getString("car_image_object_id"));
                    }
                }
                for(int i=0;i<car.size();i++)
                {
                    modelClass_favList.add(new ModelClass_fav(image.get(i),car.get(i),price.get(i)));
                }
                System.out.println(modelClass_favList);
                Adapter_fav adapter = new Adapter_fav(modelClass_favList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }


        });

//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user one","hello this is user one"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user two","hello this is user two"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user three","hello this is three"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user four","hello this is four"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user five","hello this is five"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user six","hello this is six"));
//        modelClass_favList.add(new ModelClass_fav(R.drawable.ic_launcher_background,"user seven","hello this is seven"))

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
            Intent ini = new Intent(this,Favouritess.class);
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
            finish();
        }
        return false;
    }
}
