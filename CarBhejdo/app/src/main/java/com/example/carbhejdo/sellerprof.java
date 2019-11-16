package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class sellerprof extends AppCompatActivity {



    private EditText full_name;
    private EditText email;
    private EditText phone;
    private EditText location;
    private Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerprof);

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

//    public void Onclickhome(View view) {
//        Intent intent=new Intent(this, MenuActivity.class);
//        startActivity(intent);
//    }
}
