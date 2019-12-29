package com.example.kitchen;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AdapterForSearch extends RecyclerView.Adapter<AdapterForSearch.SearchViewHolder> {

    Context mContext;
    List<Food> foods;
    Dialog myDialog;
    ChipGroup chipGroup;
    View view;
    List<Chip> chipList = new ArrayList<>();
    ArrayList<String> FoodList = new ArrayList<>();



    public AdapterForSearch(Context mContext, List<Food> foods, ChipGroup chipGroup, View view) {
        this.mContext = mContext;
        this.foods = foods;
        this.chipGroup = chipGroup;
        this.view = view;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;


        v = LayoutInflater.from(mContext).inflate(R.layout.search_element, parent,false);
        final SearchViewHolder vHolder = new SearchViewHolder(v);

        vHolder.item.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                final Button searchButton = view.findViewById(R.id.SearchButton);

                final Chip chip = new Chip(mContext);

                chip.setText(foods.get(vHolder.getAdapterPosition()).getName());
//                chip.setChipIconResource(foods.get(vHolder.getAdapterPosition()).getPicId());
                chip.setChipBackgroundColorResource(foods.get(vHolder.getAdapterPosition()).getPicId());
                chip.setCloseIconVisible(true);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setCloseIconVisible(false);
                chip.setTextColor(R.color.ChipText);


                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chipGroup.removeView(chip);
                        chipList.remove(chip);
                        if(chipList.size()==0){
                            searchButton.setVisibility(view.GONE);
                        }
                    }
                });

//                chipList.get(i).getText()
                int count = 0;
                for (int i = 0; i<chipGroup.getChildCount(); i++){
                    Chip ChipTMP = (Chip) chipGroup.getChildAt(i);
                    if(ChipTMP.getText() == chip.getText()){
                        count++;
                    }
                }
                if (count == 0){
                    FoodList.add(foods.get(vHolder.getAdapterPosition()).getName());
                    chipList.add(chip);
                    chipGroup.addView(chip);
                    chipGroup.setVisibility(view.VISIBLE);
                    searchButton.setVisibility(view.VISIBLE);


                }
//                if(chipList.size()==0){
//                    chipGroup.addView(chip);
//                    chipGroup.setVisibility(view.VISIBLE);
//                }






            }

        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.NameFood.setText(foods.get(position).getName());
        holder.TypeFood.setImageResource(foods.get(position).getPicId());



    }

    @Override
    public int getItemCount() {

        return foods.size();
    }

    public static  class  SearchViewHolder extends  RecyclerView.ViewHolder{
        private LinearLayout item;
        private TextView NameFood;
        private ImageView TypeFood;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.rootElement);
            NameFood = (TextView) itemView.findViewById(R.id.NameOfFood);
            TypeFood = (ImageView) itemView.findViewById(R.id.FoodType);
        }
    }











//    private Context context;
//    private List<Food> FoodList;
//    private FoodItemClickListener foodItemClickListener;
//    private int CountOfFood;
//
//
//    public AdapterForSearch(int CountFood, Context context, List<Food> foods){
//        CountOfFood = CountFood;
//        this.context = context;
//        this.FoodList = foods;
//        foodItemClickListener = (FoodItemClickListener)context;
//
//    }
//
//    @NonNull
//    @Override
//    public AdapterForSearch.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        int layoutIdForListItem = R.layout.search;
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view = inflater.inflate(layoutIdForListItem, parent, false);
//        AdapterForSearch.SearchViewHolder viewHolder = new AdapterForSearch.SearchViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AdapterForSearch.SearchViewHolder holder, int position) {
//        holder.textView.setText(FoodList.get(position).getName());
//        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, FoodList.get(position).getPicId()));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return CountOfFood;
//    }
//
//    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        LinearLayout search;
//        ImageView imageView;
//        TextView textView;
//        public SearchViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.FoodType);
//            textView = itemView.findViewById(R.id.foodName);
//        search = itemView.findViewById(R.id.rootElement);
//        search.setOnClickListener(this);
//        }
//        void bind(int Index){
//
//        }
//
//        @Override
//        public void onClick(View view) {
//            foodItemClickListener.onItemSelected(FoodList.get(getAdapterPosition()));
//        }
//    }
//    public  void updateList(List<Food> newFood){
//        FoodList.clear();
//        FoodList.addAll(newFood);
//        notifyDataSetChanged();
//    }


}
