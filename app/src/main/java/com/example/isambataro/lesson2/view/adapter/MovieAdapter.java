package com.example.isambataro.lesson2.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isambataro.lesson2.ApplicationContext;
import com.example.isambataro.lesson2.R;
import com.example.isambataro.lesson2.model.DataMovies;
import com.example.isambataro.lesson2.view.activity.BaseActivity;

import java.util.List;

/**
 * Created by isambataro on 18/06/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>/* implements View.OnClickListener*/{

    private List<DataMovies> items;
//    private OnItemClickListener mListener;


//    public interface OnItemClickListener {
//        void onItemClick(View view, int item);
//    }

    public MovieAdapter (List<DataMovies> movieList/*, OnItemClickListener listener*/){
        super();
        this.items = movieList;
//        this.mListener = listener;
    }

    public void setItem(List<DataMovies> item) {
        this.items = item;
        notifyDataSetChanged();
    }

//    @Override
//    public void onClick(View view) {
//        mListener.onItemClick(view, getItemCount());
//    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("SAMBA ONCREATE ADAPTER");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        System.out.println("SAMBA ONBINDVIEWHOLDER");
        holder.director.setText(items.get(position).getDirector());
        holder.title.setText(items.get(position).getTitle());
        holder.data_release.setText(items.get(position).getRealeaseDate());
        holder.episode.setText(items.get(position).getEpisodeId());
        holder.producer.setText(items.get(position).getProducer());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private CardView cardView;
        private TextView title;
        private TextView data_release;
        private TextView episode;
        private TextView producer;
        private TextView director;
        private FloatingActionButton floatingActionButton;

        public MovieViewHolder(View v){
            super(v);
            cardView = v.findViewById(R.id.card_view_movie);
            title = v.findViewById(R.id.movie_title);
            data_release = v.findViewById(R.id.release);
            episode = v.findViewById(R.id.episode);
            producer = v.findViewById(R.id.producer);
            director = v.findViewById(R.id.director);
            floatingActionButton = v.findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.
                    share.putExtra(Intent.EXTRA_TEXT, title.getText());

                    ApplicationContext.getAppContext().startActivity(Intent.createChooser(share, "Share link!"));
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "value = " + title.getText().toString(),
                    Toast.LENGTH_LONG).show();
        }

    }

}
