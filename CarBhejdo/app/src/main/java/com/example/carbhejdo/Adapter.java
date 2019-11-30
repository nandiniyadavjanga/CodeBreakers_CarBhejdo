package com.example.carbhejdo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    public ClickListener clickListener;
    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    public void addData(List<ModelClass> modelClasses){

        if(modelClassList != null && modelClassList.size() > 0){
            modelClassList.addAll(modelClasses);
            notifyDataSetChanged();
        }else{
            modelClassList = modelClasses;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

        // int resource = modelClassList.get(position).getImageResource();
        String imageUrl = modelClassList.get(position).getImageUrl();
        String title = modelClassList.get(position).getTitle();
        String body = modelClassList.get(position).getBody();
        String body1 = modelClassList.get(position).getBody1();
        viewholder.setData(imageUrl, title, body, body1);


    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView title;
        private TextView body;
        private TextView body1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            body1 = itemView.findViewById(R.id.body1);
        }

        private void setData(String imageUrl, String titleText, String bodyText, String bodyText1){
//            imageView.setImageResource(resource);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(itemView.getContext()).load(imageUrl).into(imageView);
            }
            title.setText(titleText);
            body.setText(bodyText);
            body1.setText(bodyText1);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToSingleCarScreen(getAdapterPosition(),v.getContext());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    private void navigateToSingleCarScreen(int position, Context mContext) {


        Intent ini = new Intent(mContext,Singlecar_Activity.class);
        ini.putExtra("ModelData",modelClassList.get(position));
        mContext.startActivity(ini);
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
