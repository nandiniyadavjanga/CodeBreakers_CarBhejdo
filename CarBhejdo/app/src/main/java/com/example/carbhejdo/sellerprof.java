package com.example.carbhejdo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class sellerprof extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private EditText full_name;
    private EditText email;
    private EditText phone;
    private EditText location;
    private Button save;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerprof);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);


        full_name = findViewById(R.id.profile_fullname);
        email = findViewById(R.id.profile_email_id);
        phone = findViewById(R.id.profile_phone);
        location = findViewById(R.id.profile_location);
        save = (Button)  findViewById(R.id.profile_edit);

        full_name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        location.setEnabled(false);
        save.setEnabled(false);


        String object_id = SignUpProfileActivity.object_id;
        if (object_id == null) {
            object_id = SIgninSignupActivity.object_id;
        }

        ParseUser user = ParseUser.getCurrentUser();
        String seller_Name = user.get("username").toString();
        final String seller_email = user.get("email").toString();
        final String seller_phone =  user.get("Mobile").toString();
        String seller_location =  user.get("Address").toString();
        full_name.setText(seller_Name);
        email.setText(seller_email);
        phone.setText(seller_phone);
        location.setText(seller_location);





        ImageButton edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                full_name.setEnabled(true);
                email.setEnabled(true);
                phone.setEnabled(true);
                location.setEnabled(true);
                save.setEnabled(true);

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = ParseUser.getCurrentUser();
                user.setEmail(seller_email);
                user.put("Mobile", seller_phone);
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            Toast.makeText(sellerprof.this, "succefully updated details", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(sellerprof.this, "unable to updated details " + e, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });



    }
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
        if(id==R.id.logout){
            ParseUser.logOut();
            Intent ini = new Intent(this,SIgninSignupActivity.class);
            startActivity(ini);
        }
        return false;
    }

//    public void Onclickhome(View view) {
//        Intent intent=new Intent(this, MenuActivity.class);
//        startActivity(intent);
//    }
}
