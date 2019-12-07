package com.example.carbhejdo;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CarInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView addimage;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private static final int PERMISSION_REQUEST_AGAIN=0;
    private static final int RESULT_LOAD_IMAGE_CAR=1;

    private EditText car_name;
    private EditText model_no;
    private EditText year;
    private EditText location;
    private EditText miles_driven;
    private EditText price;
    private Button push_sell_car;
    private static ParseObject imgupload;
    private String image_object_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);


        push_sell_car = findViewById(R.id.push_sell_car);
        push_sell_car.setEnabled(false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_AGAIN);
        }
        addimage = findViewById(R.id.addimage);
        Button  addimgbutton = findViewById(R.id.addimgbutton);
        addimgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatchTakePictureIntent();
            }
        });




        push_sell_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String object_id = SignUpProfileActivity.object_id;
                if (object_id == null) {

                    object_id = SIgninSignupActivity.object_id;

                }

                car_name = findViewById(R.id.car_name);
                model_no = findViewById(R.id.model_no);
                year = findViewById(R.id.year);
                location = findViewById(R.id.location);
                miles_driven = findViewById(R.id.miles_driven);
                price = findViewById(R.id.price);

                final ParseObject push_car_info = new ParseObject("sellingCarInfo");
                push_car_info.put("user_object_id", object_id);
                push_car_info.put("car_name", car_name.getText().toString());
                push_car_info.put("model_no",model_no.getText().toString());
                push_car_info.put("year",year.getText().toString());
                push_car_info.put("location",location.getText().toString());
                push_car_info.put("miles_driven",miles_driven.getText().toString());
                push_car_info.put("price",price.getText().toString());
                image_object_id = imgupload.getObjectId();
                Log.d("object_id",  "object_id is" + image_object_id);
                Toast.makeText(CarInfoActivity.this,"object is is " + image_object_id,Toast.LENGTH_SHORT).show();
                push_car_info.put("car_image_object_id", image_object_id);
                push_car_info.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            Toast.makeText(CarInfoActivity.this, "car details added for selling", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(CarInfoActivity.this, "unable to add car details " + e, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_AGAIN:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Permission not granted",Toast.LENGTH_SHORT).show();
                    finish();
                }

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case RESULT_LOAD_IMAGE_CAR:
                if(resultCode == RESULT_OK){
                   /* Uri selectedImage = data.getData();
                    String[] filePathColumn1 = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn1, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex= cursor.getColumnIndex(filePathColumn1[0]);
                    String picturepath1 = cursor.getString(columnIndex);
                    Bitmap bitmap = BitmapFactory.decodeFile(picturepath1);*/
                    Bundle extras = data.getExtras();
                    if (extras != null && extras.get("data") != null) {
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        addimage.setImageBitmap(imageBitmap);
                        uploadPictureToParse(imageBitmap);
                    }

                }
        }
    }
    private void dispatchTakePictureIntent() {
        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, RESULT_LOAD_IMAGE_CAR);
        }
    }
    public void onPostClick(View v){
        Intent ini = new Intent(this, BuyCarListActivity.class);
        startActivity(ini);
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

//    @Override
//   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//       super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
//            imageUri = data.getData();
//            addimage.setImageURI(imageUri);
//
//       }
//    }

    private void uploadPictureToParse(Bitmap path){
        ParseFile file = null;
        try {
            file = new ParseFile("picturePath", readInFile(getFileFromBitMap(path).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Upload the image into Parse Cloud
        file.saveInBackground();

        // Create a New Class called "ImageUpload" in Parse
        imgupload = new ParseObject("Image");

        // Create a column named "ImageName" and set the string
        imgupload.put("Image", "picturePath");


        // Create a column named "ImageFile" and insert the image
        imgupload.put("ImageFile", file);

        // Create the class and the columns
        imgupload.saveInBackground();

        push_sell_car.setEnabled(true);


        // Show a simple toast message
        Toast.makeText(this, "Image Saved, Upload another one ",Toast.LENGTH_SHORT).show();

    }

    private byte[] readInFile(String path) throws IOException {
        // TODO Auto-generated method stub
        byte[] data = null;
        File file = new File(path);
        InputStream input_stream = new BufferedInputStream(new FileInputStream(
                file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        data = new byte[16384]; // 16K
        int bytes_read;
        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytes_read);
        }
        input_stream.close();
        return buffer.toByteArray();
    }
    private File getFileFromBitMap(Bitmap bitmap){

        File file = new File(getCacheDir(), "image_"+System.currentTimeMillis());
        try {
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
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


