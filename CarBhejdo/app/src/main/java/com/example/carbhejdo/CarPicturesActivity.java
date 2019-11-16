package com.example.carbhejdo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CarPicturesActivity extends Activity {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_pictures_layout);
        getTheUploadedPictures();
    }

    private void getTheUploadedPictures() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
             /*   Log.e("Hello"," Parse objs");
                ParseFile postImage = objects.get(0).getParseFile("ImageFile");
                postImage.getUrl();*/
                mRecyclerView = findViewById(R.id.recyclerView);
                mLinearLayoutManager = new LinearLayoutManager(CarPicturesActivity.this, RecyclerView.VERTICAL,false);
                AdapterCarImages downloadedImages = new AdapterCarImages(CarPicturesActivity.this,objects);
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                mRecyclerView.setAdapter(downloadedImages);
            }
        });

    }
}
