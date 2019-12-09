package com.example.carbhejdo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_fav extends RecyclerView.Adapter<Adapter_fav.Viewholder> {

    private List<ModelClass_fav> modelClass_favList;

    public Adapter_fav(List<ModelClass_fav> modelClass_favList) {
        this.modelClass_favList = modelClass_favList;
    }

    @NonNull
    @Override
    public Adapter_fav.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_fav.Viewholder viewholder, int position) {
        String imageUrl = modelClass_favList.get(position).getImageUrl();
        String title = modelClass_favList.get(position).getTitle();
        String body = modelClass_favList.get(position).getBody();
        viewholder.setData(imageUrl,title,body);
    }

    @Override
    public int getItemCount() {
        return modelClass_favList.size();
    }

    class  Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView body;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

        }
        private void setData(String imageUrl, String titleText, String bodyText){
            //imageView.setImageResource(resource);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(itemView.getContext()).load(imageUrl).into(imageView);
            }
            title.setText(titleText);
            body.setText(bodyText);
        }
    }

}
