package com.example.kitchen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;



public class AdapterForTopList extends RecyclerView.Adapter<AdapterForTopList.TopViewHolder> {
    List<HomeData> homeData;



    public AdapterForTopList(List<HomeData> HomeData){
        homeData = HomeData;


    }

    @NonNull
    @Override
    public AdapterForTopList.TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.home_list_element;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        AdapterForTopList.TopViewHolder viewHolder = new AdapterForTopList.TopViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterForTopList.TopViewHolder holder, final int position) {
        holder.bind(position);

//        ImageView recipePhoto = holder.itemView.findViewById(R.id.imageTop);
//
        Picasso.get().load(homeData.get(position).getPhoto()).resize(0, 1000).into(holder.recipePhoto);
        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(holder.itemView.getContext(), RecipePage.class);

                intent.putExtra("IdRecipe", homeData.get(position).getIdRecipe());
                intent.putExtra("UserName", homeData.get(position).getUserName());
                intent.putExtra("CookTime", homeData.get(position).getTimeOfCook());
                intent.putExtra("Photo", homeData.get(position).getPhoto());

                holder.itemView.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return homeData.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView timeCook;
        ImageView recipePhoto;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.UserNameTop);
            timeCook = itemView.findViewById(R.id.CookTime);
            recipePhoto =itemView.findViewById(R.id.imageTop);




        }
        void bind(int Index){
//            Picasso.get().load(homeData.get(Index).getPhoto()).into(recipePhoto);
            userName.setText(homeData.get(Index).getUserName());
            timeCook.setText("Время приготовления: " + homeData.get(Index).getTimeOfCook() + " минут");

        }
    }
}
