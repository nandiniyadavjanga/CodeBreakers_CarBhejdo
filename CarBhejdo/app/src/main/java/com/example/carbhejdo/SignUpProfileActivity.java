package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class SignUpProfileActivity extends AppCompatActivity {

    int row_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();



        Button singup_action =  findViewById(R.id.singup_action);
        singup_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText full_name = findViewById(R.id.full_name);
                String fullname_string = full_name.getText().toString();
                EditText email_signup = findViewById(R.id.email_signup);
                String email_signup_string = email_signup.getText().toString();
                EditText phone_signup = findViewById(R.id.phone_signup);
                String phone_signup_string = phone_signup.getText().toString();
                EditText locationsignup = findViewById(R.id.locationsignup);
                String locationsignup_string = locationsignup.getText().toString();
                EditText passwordsingup = findViewById(R.id.passwordsingup);
                String passwordsingup_string = passwordsingup.getText().toString();


                /*ParseQuery<ParseObject> ask = ParseQuery.getQuery("Car_Bejdho_User");
                ask.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        for (ParseObject bird: objects){
                            row_id  = row_id +1;
                        }
                    }
                });*/


                final ParseObject tweety = new ParseObject("Car_Bejdho_User");
                tweety.put("Id", row_id +1);
                tweety.put("Name", fullname_string);
                tweety.put("Email", email_signup_string);
                tweety.put("Mobile",phone_signup_string);
                tweety.put("Password",passwordsingup_string);
                tweety.put("Location",locationsignup_string);

                tweety.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(getApplicationContext(), "Error While creating user " +e , Toast.LENGTH_SHORT).show();
                        Log.d("signup", "ObjectId sgnup " + tweety.getObjectId());
                        Log.d("signup", "creation time " + tweety.getCreatedAt());
                    }
                });
                Toast.makeText(getApplicationContext(), "Sucessfully created user for "+ fullname_string , Toast.LENGTH_SHORT).show();
                Log.d("signup", "ObjectId post " + tweety.getObjectId());

                //Intent ini = new Intent(this, MainScreen.class);
                //startActivity(ini);
            }
        });



    }
    public void onSignUpClick(View v){
        Intent ini = new Intent(this, MainScreen.class);
        startActivity(ini);
    }
}
