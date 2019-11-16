package com.example.carbhejdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.List;

public class AdapterCarImages extends RecyclerView.Adapter<AdapterCarImages.CarImageHolder> {


    private Context mContext;
    private List<ParseObject> mParseObjects;

    public AdapterCarImages(Context mContext, List<ParseObject> objects){
        this.mContext = mContext;
        this.mParseObjects = objects;
    }

    @NonNull
    @Override
    public CarImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_car_images, parent, false);

        return new CarImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarImageHolder holder, int position) {
        ParseFile imageFile = mParseObjects.get(position).getParseFile("ImageFile");
        if (imageFile != null) {
            imageFile.getUrl();
            Glide.with(mContext).load(imageFile.getUrl()).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CarImageHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public CarImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
