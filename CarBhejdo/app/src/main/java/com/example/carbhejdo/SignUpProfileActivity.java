package com.example.carbhejdo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SignUpProfileActivity extends AppCompatActivity {
    private static ParseObject imgupload;
    private String image_object_id;
    ImageView addimage;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private static final int PERMISSION_REQUEST_AGAIN=0;
    private static final int RESULT_LOAD_IMAGE_CAR=1;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private EditText username;
    private EditText mobile;
    private EditText address;
    public static String object_id = null;


    private LocationManager locationManager;
    private Location location;



    private static final int PERMISSION_REQUEST=0;
    private static final int RESULT_LOAD_IMAGE=1;
    int row_id = 0;
    ImageView profileimg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);

         profileimg = findViewById(R.id.profileimg);





        email = findViewById(R.id.email_signup);
        password =  findViewById(R.id.passwordsingup);
        confirmpassword =  findViewById(R.id.passwordsingup);
        username=findViewById(R.id.full_name);
        mobile=findViewById(R.id.phone_signup);
        address=findViewById(R.id.locationsignup);

        getGPSLocation();



        final Button sign_action_button = findViewById(R.id.singup_action);
        sign_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validationError = false;

                address=findViewById(R.id.locationsignup);
                final ParseUser sing_up_user = new ParseUser();
                sing_up_user.setEmail(email.getText().toString());
                sing_up_user.setPassword(password.getText().toString());
                sing_up_user.setUsername(username.getText().toString());
                sing_up_user.put("Mobile",(mobile.getText().toString()));
                sing_up_user.put("Address",(address.getText().toString()));
                sing_up_user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //dlg.dismiss();
                            object_id = sing_up_user.getObjectId();
                            alertDisplayer("Sucessful Signup","Successfully signed up " + email.getText().toString() + "!");

                        } else {
                            //dlg.dismiss();
                            ParseUser.logOut();
                            Toast.makeText(SignUpProfileActivity.this, "error message", Toast.LENGTH_LONG).show();
                            Toast.makeText(SignUpProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

//        addimage = findViewById(R.id.profileimg);
//        Button  addimgbutton = findViewById(R.id.profilebtn);
//        addimgbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dispatchTakePictureIntent();
//            }
//        });

    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case PERMISSION_REQUEST_AGAIN:
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "Permission granted",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(this, "Permission not granted",Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//            case RESULT_LOAD_IMAGE_CAR:
//                if(resultCode == RESULT_OK){
//                   /* Uri selectedImage = data.getData();
//                    String[] filePathColumn1 = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn1, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex= cursor.getColumnIndex(filePathColumn1[0]);
//                    String picturepath1 = cursor.getString(columnIndex);
//                    Bitmap bitmap = BitmapFactory.decodeFile(picturepath1);*/
//                    Bundle extras = data.getExtras();
//                    if (extras != null && extras.get("data") != null) {
//                        Bitmap imageBitmap = (Bitmap) extras.get("data");
//                        addimage.setImageBitmap(imageBitmap);
//                        uploadPictureToParse(imageBitmap);
//                    }
//
//                }
//        }
//    }
//    private void dispatchTakePictureIntent() {
//        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, RESULT_LOAD_IMAGE_CAR);
//        }
//    }
//    private void uploadPictureToParse(Bitmap path){
//        ParseFile file = null;
//        try {
//            file = new ParseFile("picturePath", readInFile(getFileFromBitMap(path).getPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Upload the image into Parse Cloud
//        file.saveInBackground();
//
//        // Create a New Class called "ImageUpload" in Parse
//        imgupload = new ParseObject("Image");
//
//        // Create a column named "ImageName" and set the string
//        imgupload.put("Image", "picturePath");
//
//
//        // Create a column named "ImageFile" and insert the image
//        imgupload.put("ImageFile", file);
//
//        // Create the class and the columns
//        imgupload.saveInBackground();
//
//        //push_sell_car.setEnabled(true);
//
//
//        // Show a simple toast message
//        Toast.makeText(this, "Image Saved, Upload another one ",Toast.LENGTH_SHORT).show();
//
//    }
//
//    private byte[] readInFile(String path) throws IOException {
//        // TODO Auto-generated method stub
//        byte[] data = null;
//        File file = new File(path);
//        InputStream input_stream = new BufferedInputStream(new FileInputStream(
//                file));
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        data = new byte[16384]; // 16K
//        int bytes_read;
//        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
//            buffer.write(data, 0, bytes_read);
//        }
//        input_stream.close();
//        return buffer.toByteArray();
//    }
//    private File getFileFromBitMap(Bitmap bitmap){
//
//        File file = new File(getCacheDir(), "image_"+System.currentTimeMillis());
//        try {
//            file.createNewFile();
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//            byte[] bitmapdata = bos.toByteArray();
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(bitmapdata);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
    private void getGPSLocation() {

        FetchAddressService addressService = new FetchAddressService(this.getLocalClassName(), this);
        Location gpsLocation = addressService.getLocation(LocationManager.GPS_PROVIDER);
        double latitude = 0.0;
        double longitude = 0.0;
        if(gpsLocation != null){
            latitude = gpsLocation.getLatitude();
            longitude = gpsLocation.getLongitude();
            ConvertToAddress.getAddress(latitude,longitude,this,new GeocoderHandler());
            Log.d("LOCATION","Lat is "+ latitude+" and long is "+longitude);
        }else{
            showSettingsAlert();
        }
    }

public void signinclick(View v){
        Intent in = new Intent(this,SIgninSignupActivity.class);
    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
}
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }


            Log.d("Location",locationAddress);
            address.setText(locationAddress);
        }
    }


    public void showSettingsAlert() {
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(
                SignUpProfileActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        SignUpProfileActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//            case RESULT_LOAD_IMAGE:
//                if(resultCode == RESULT_OK){
//                    Uri selectedImage = data.getData();
//                    String[] filePathColumn1 = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn1, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex= cursor.getColumnIndex(filePathColumn1[0]);
//                    String picturepath1 = cursor.getString(columnIndex);
//                    profileimg.setImageBitmap(BitmapFactory.decodeFile(picturepath1));
//                }
//        }
//    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText text1, EditText text2){
        if(text1.getText().toString().equals(text2.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpProfileActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(SignUpProfileActivity.this, BuyCarListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}