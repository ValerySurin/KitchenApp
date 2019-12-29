package com.example.kitchen;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterForFoodListInAddActivity extends RecyclerView.Adapter<AdapterForFoodListInAddActivity.FoodListViewHolder>{

    Context mContext;
    List<Food> foods;
    Dialog myDialog;
    ChipGroup chipGroup;
    View view;
    List<Chip> chipList = new ArrayList<>();
    ArrayList<String> FoodList = new ArrayList<>();
    TextView product;
    LinearLayout hiddenElement;
    TextView enterFoodType;
    TextView enterFoodCount;

//    Context mContext, List<Food> foods, ChipGroup chipGroup, View view

    public AdapterForFoodListInAddActivity(List<Food> foods, TextView Product, LinearLayout HiddenElement, TextView EnterFoodType, TextView EnterFoodCount) {
//        this.mContext = mContext;
        this.foods = foods;
        this.product = Product;
        this.hiddenElement = HiddenElement;
        this.enterFoodType = EnterFoodType;
        this.enterFoodCount = EnterFoodCount;
//        this.chipGroup = chipGroup;
//        this.view = view;

    }

    @NonNull
    @Override
    public AdapterForFoodListInAddActivity.FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;
        final Context context = parent.getContext();

        v = LayoutInflater.from(context).inflate(R.layout.search_element, parent,false);
        final AdapterForFoodListInAddActivity.FoodListViewHolder vHolder = new AdapterForFoodListInAddActivity.FoodListViewHolder(v);

        vHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setText(vHolder.NameFood.getText());
                product.setVisibility(View.VISIBLE);
                product.setTextColor(ContextCompat.getColor(context, foods.get(vHolder.getAdapterPosition()).getPicId()));
                enterFoodType.setTextColor(ContextCompat.getColor(context, foods.get(vHolder.getAdapterPosition()).getPicId()));
                enterFoodCount.setTextColor(ContextCompat.getColor(context, foods.get(vHolder.getAdapterPosition()).getPicId()));
                hiddenElement.setVisibility(View.VISIBLE);
                TextView tmp = hiddenElement.findViewById(R.id.tmp);
                String color =  ""+ foods.get(vHolder.getAdapterPosition()).getPicId();
                tmp.setText(color);

                TextView tmp1 = hiddenElement.findViewById(R.id.tmp1);
                String id =  ""+ foods.get(vHolder.getAdapterPosition()).getId();
                tmp1.setText(id);
            }


        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForFoodListInAddActivity.FoodListViewHolder holder, int position) {
        holder.NameFood.setText(foods.get(position).getName());
        holder.TypeFood.setImageResource(foods.get(position).getPicId());



    }

    @Override
    public int getItemCount() {

        return foods.size();
    }

    public static  class  FoodListViewHolder extends  RecyclerView.ViewHolder{
       private LinearLayout item;
       private TextView NameFood;
       private ImageView TypeFood;

        public FoodListViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.rootElement);
            NameFood = (TextView) itemView.findViewById(R.id.NameOfFood);
            TypeFood = (ImageView) itemView.findViewById(R.id.FoodType);
        }
    }

}
