package com.example.carbhejdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {

    private Model favourite_model;
    private Context context;

    public FavouritesAdapter(Model favourite_model, Context context) {
        super();
        this.favourite_model = favourite_model;
        this.context=context;
    }

    public static class FavouritesViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout view_linear;

        public FavouritesViewHolder(LinearLayout v) {
            super(v);
            view_linear = v;
        }
    }
    @NonNull
    @Override
    public FavouritesAdapter.FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourites_detailed_view, parent, false);

        FavouritesViewHolder view_holder = new FavouritesViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.FavouritesViewHolder holder, int position) {
        LinearLayout layout_holder = holder.view_linear;
        TextView disp_Lyric = layout_holder.findViewById(R.id.favourite);
        disp_Lyric.setText(favourite_model.getFavourites().get(position).favourite);
    }

    @Override
    public int getItemCount() {
         return favourite_model.getFavourites().size();
    }
}
