package com.example.carbhejdo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class FavouritesActivity extends AppCompatActivity {
    private Model favourite_model;
    private FavouritesAdapter fav_server = null;
    private RecyclerView fav_recycler = null;
    private GestureDetectorCompat gesture_detector = null;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = fav_recycler.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = fav_recycler.getChildViewHolder(view);
                if (holder instanceof FavouritesAdapter.FavouritesViewHolder
                ) {
                    int position = holder.getAdapterPosition();

                    // handle single tap
                    Log.d("click", "clicked on item "+ position);
//                    TextView outputTV = findViewById(R.id.outputTV);
//                    outputTV.setText("Clicked on " + myModel.thePlanets.get(position).name);
//                    // Remove the selected data from the model
//                    myModel.thePlanets.remove(position);
//                    planetServer.notifyItemRemoved(position);

                    return true;  // Use up the tap gesture
                }
            }

            // we didn't handle the gesture so pass it on
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        favourite_model = Model.getModel();
        fav_server = new FavouritesAdapter(favourite_model,this);
        fav_recycler = findViewById(R.id.Recycler);
        fav_recycler.setAdapter(fav_server);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        fav_recycler.setLayoutManager(linearLayoutManager);

        gesture_detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        fav_recycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return gesture_detector.onTouchEvent(e);
            }
        });
    }
}
