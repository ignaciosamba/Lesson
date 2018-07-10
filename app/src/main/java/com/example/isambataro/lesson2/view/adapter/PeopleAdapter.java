package com.example.isambataro.lesson2.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isambataro.lesson2.R;
import com.example.isambataro.lesson2.model.DataMovies;
import com.example.isambataro.lesson2.model.DataPeople;

import java.util.List;

/**
 * Created by isambataro on 22/06/18.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{

    private List<DataPeople> items;
//    private final OnItemClickListener listener;

    public PeopleAdapter (List<DataPeople> peopleList/*, OnItemClickListener listener*/){
        super();
        this.items = peopleList;
//        this.listener = listener;
    }

    public void setItem(List<DataPeople> item) {
        System.out.println("SAMBA SETITEM");
        this.items = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("SAMBA ONCREATE ADAPTER");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_people_layout, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        System.out.println("SAMBATA ONBINDVIEWHOLDER");
        holder.name.setText(items.get(position).getName());
        holder.gender.setText(items.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private CardView cardView;
        private TextView name;
        private TextView gender;

        public PeopleViewHolder(View v){
            super(v);
            cardView = v.findViewById(R.id.card_view_movie);
            name = v.findViewById(R.id.people_name);
            gender = v.findViewById(R.id.people_gender);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
