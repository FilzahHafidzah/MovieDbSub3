package com.filzah.moviedbsub3duty.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.filzah.moviedbsub3duty.DetailActivity;
import com.filzah.moviedbsub3duty.R;
import com.filzah.moviedbsub3duty.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<Movie> listMovie;

    public MainAdapter(Context context, List<Movie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        final Movie movies = listMovie.get(position);

        holder.nameView.setText(movies.getTitle());

        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185"+movies.getPosterPath())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.default_pict))
                .override(350, 550)
                .error(R.drawable.default_pict)
                .into(holder.imgView);

        holder.itemView.setOnClickListener(new ClickItemAdapter(new ClickItemAdapter.onCallback() {
            @Override
            public void onItemClicked(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.Extra, (Parcelable) movies);
                view.getContext().startActivity(intent);
            }
        }));



    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView nameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.item_image);
            nameView = itemView.findViewById(R.id.item_name);
        }

    }
}
