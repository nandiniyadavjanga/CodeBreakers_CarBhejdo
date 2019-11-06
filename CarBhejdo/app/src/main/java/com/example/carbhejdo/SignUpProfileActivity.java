package com.example.carbhejdo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.provider.MediaStore;
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
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpProfileActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private EditText username;
    private EditText mobile;
    private EditText address;



    private static final int PERMISSION_REQUEST=0;
    private static final int RESULT_LOAD_IMAGE=1;
    int row_id = 0;
    ImageView profileimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }
         profileimg = findViewById(R.id.profileimg);
        Button  profilebtn = findViewById(R.id.profilebtn);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });



        email = findViewById(R.id.email_signup);
        password =  findViewById(R.id.passwordsingup);
        confirmpassword =  findViewById(R.id.passwordsingup);
        username=findViewById(R.id.full_name);
        mobile=findViewById(R.id.phone_signup);
        address=findViewById(R.id.locationsignup);

    

        final Button signup_button = findViewById(R.id.singup_action);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validationError = false;

                StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
                if (isEmpty(email)) {
                    validationError = true;
                    validationErrorMessage.append("an username");
                }
                if (isEmpty(password)) {
                    if (validationError) {
                        validationErrorMessage.append(" and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("a password");
                }
                if (isEmpty(confirmpassword)) {
                    if (validationError) {
                        validationErrorMessage.append(" and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("your password again");
                }
                else {
                    if (!isMatching(password, confirmpassword)) {
                        if (validationError) {
                            validationErrorMessage.append(" and ");
                        }
                        validationError = true;
                        validationErrorMessage.append("the same password twice.");
                    }
                }
                validationErrorMessage.append(".");

                if (validationError) {
                    Toast.makeText(SignUpProfileActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                //Setting up a progress dialog
//                final ProgressDialog dlg = new ProgressDialog(SignUpProfileActivity.this);
//                dlg.setTitle("Please, wait a moment.");
//                dlg.setMessage("Signing up...");
//                dlg.show();

                ParseUser user = new ParseUser();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setUsername(username.getText().toString());
                user.put("Contact",(mobile.getText().toString()));
                user.put("Address",(address.getText().toString()));
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //dlg.dismiss();
                            alertDisplayer("Sucessful Signup","Successfully signed up " + email.getText().toString() + "!");

                        } else {
                            //dlg.dismiss();
                            ParseUser.logOut();
                            Toast.makeText(SignUpProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

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
                        Intent intent = new Intent(SignUpProfileActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}