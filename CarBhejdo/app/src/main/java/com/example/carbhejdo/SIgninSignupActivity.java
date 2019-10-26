package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SIgninSignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
    }

    public void signInClck(View v){
        Intent ini = new Intent(this, MainScreen.class);
        startActivity(ini);
    }

    public void signUpClick(View v){
        Intent ini = new Intent(this, SignUpProfileActivity.class);
        startActivity(ini);
    }

//    public void onActivityResult(int requestCode,int resultCode,Intent signIn){
//        if(requestCode==REQUEST){
//            if(resultCode==RESPONSE){
//
//            }
//        }
    //   }


}
