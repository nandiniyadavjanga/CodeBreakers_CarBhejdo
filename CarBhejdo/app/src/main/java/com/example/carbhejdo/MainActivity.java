package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
     public static final int REQUEST =1;
    public static final int RESPONSE =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClck(View v){
        Intent ini = new Intent(this, MainScreen.class);
        startActivity(ini);
    }

    public void on_sign_up(View v){
        Intent ini = new Intent(this, ProfileActivity.class);
        startActivity(ini);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent signIn){
        if(requestCode==REQUEST){
            if(resultCode==RESPONSE){

            }
        }
    }


}
