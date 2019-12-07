package com.example.carbhejdo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BuyCarListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ToggleButton toggleButton;
    static List<ModelClass> modelClassList = new ArrayList<>();
    ArrayList<String> temp_reculer_data = new ArrayList<String>();
    private String KIA_PLACE_HOLDER = "https://cdn.pixabay.com/photo/2018/04/09/22/07/car-3305699_960_720.jpg";
    Adapter adapter;
    ParseFile parseFile;
    Button logo ;
    String title;
    String car_price;
    String miles_driven;
    String location;
    String model_no;
    String year;
    String user_object_id;
    private List<ParseObject> mParseObjects;
    private int parsePosition;


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

        ParseQuery<ParseObject> ask = ParseQuery.getQuery("sellingCarInfo");

        ask.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                mParseObjects = objects;
                parseCarObject();
            }

        });


        Log.d("object_id", "getting data" + modelClassList);
        Log.d("object_id", "getting data" + temp_reculer_data);

      /*  for (String sample: temp_reculer_data){
            String [] splited_data = sample.split(" ");
            //modelClassList.add(new ModelClass(R.drawable.altima, splited_data[0], "Price: " + splited_data[1],"Miles Driven: " + splited_data[2]));
            modelClassList.add(new ModelClass(KIA_PLACE_HOLDER,splited_data[0] , "Price: " + splited_data[1],"Miles Driven: " + splited_data[2]));

        }*/
        modelClassList.add(new ModelClass(KIA_PLACE_HOLDER, "Kia", "Price: $5000","Miles Driven: 11000", "Location : US", "model No : XXXX", "year : XXXX", "user object id: XXXXX"));
        adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void parseCarObject(){
        ParseObject carObject = mParseObjects.get(parsePosition);
        title = carObject.getString("car_name");
        if (title == null || title.isEmpty()){
            title = "UNDEFINED";
        }
        car_price = carObject.getString("price");
        if (car_price == null || car_price.isEmpty()){
            car_price = "UNDEFINED";
        }
        miles_driven = carObject.getString("miles_driven");
        if (miles_driven == null || miles_driven.isEmpty()){
            miles_driven = "UNDEFINED";
        }
        location = carObject.getString("location");
        if (location == null || location.isEmpty()){
            location = "UNDEFINED";
        }
        model_no = carObject.getString("model_no");
        if (model_no == null || model_no.isEmpty()){
            model_no = "UNDEFINED";
        }
        year = carObject.getString("year");
        if (year == null || year.isEmpty()){
            year = "UNDEFINED";
        }
        user_object_id = carObject.getString("user_object_id");
        if (user_object_id == null || user_object_id.isEmpty()){
            user_object_id = "UNDEFINED";
        }




        String image_table_id = carObject.getString("car_image_object_id");
        makeQueryAndAddImage(image_table_id);
        temp_reculer_data.add(title +" " + car_price + " " + miles_driven);
        Log.d("object_id", "getting data" + temp_reculer_data);

    }

    private void makeQueryAndAddImage(final String image_table_id){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                String carImageUrl = KIA_PLACE_HOLDER;
                for (int i = 0; i< objects.size();i++) {
                    ParseObject parseObject = objects.get(i);
                    if((parseObject != null)
                            && (parseObject.getObjectId() != null)
                            && parseObject.getObjectId().equalsIgnoreCase(image_table_id)){

                        parseFile = objects.get(i).getParseFile("ImageFile");
                        if(parseFile != null ){
                            carImageUrl = parseFile.getUrl();
                        }
                    }
                }
                modelClassList.add(new ModelClass(carImageUrl,title,"Price: "+car_price,"Miles Driven: " + miles_driven, "location: " + location, "model_no : " + model_no, "year: " + year, "user_object_id: " + user_object_id));
                if(parsePosition >= mParseObjects.size()-1){
                    updateAdapterWithModelData(modelClassList);
                }else{
                    parsePosition++;
                    parseCarObject();
                }
            }
        });


    }

    private void updateAdapterWithModelData(List<ModelClass> modelClasses ){

        if(adapter != null){
            adapter.addData(modelClasses);
        }

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

        return false;
    }
}
