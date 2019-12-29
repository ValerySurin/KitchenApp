package com.example.kitchen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class AdapterForRecipePage extends RecyclerView.Adapter<AdapterForRecipePage.RecipePageViewHolder>{
    private  FullData FullData;

    public AdapterForRecipePage(FullData fullData){
        FullData = fullData;

    }

    @NonNull
    @Override
    public AdapterForRecipePage.RecipePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.element_of_ingredients_list;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

//        ViewPagerAdapterForSteps viewPageAdapterForCard = new ViewPagerAdapterForSteps(context);
//        ViewPager viewPager = view.findViewById(R.id.mainViewPager);
//        viewPager.setAdapter(viewPageAdapterForCard);
//        viewPager.setPadding(50, 0, 50, 0);
//        viewPager.setCurrentItem(1);

        AdapterForRecipePage.RecipePageViewHolder viewHolder = new AdapterForRecipePage.RecipePageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterForRecipePage.RecipePageViewHolder holder, final int position) {
        holder.bind(position);






    }

    @Override
    public int getItemCount() {
        return FullData.getProductSize();
    }

    class RecipePageViewHolder extends RecyclerView.ViewHolder {
        Chip chip;
        TextView textView;

        public RecipePageViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.ChipInIngredientList);
            textView = itemView.findViewById(R.id.IngredientDiscription);

        }
        void bind(int Index){
            List<ColorID> colorID = new ArrayList<>();
            colorID.add(new ColorID(R.color.spice, "spice"));
            colorID.add(new ColorID(R.color.meat, "meat"));
            colorID.add(new ColorID(R.color.fish, "fish"));
            colorID.add(new ColorID(R.color.fruits, "fruits"));
            colorID.add(new ColorID(R.color.vegetable, "vegetable"));
            colorID.add(new ColorID(R.color.sause, "sause"));

            int color=0;
            for(int i = 0; i < colorID.size(); i++){
                String k = colorID.get(i).getColorName();
                String p = FullData.getProductColor(Index);
                if (k.equals(p)){
                    color = colorID.get(i).getColorId();
                }
            }

            chip.setText(FullData.getProductName(Index));
            chip.setChipBackgroundColorResource(color);
//            chip.setBackgroundColor(color2);
            textView.setText(FullData.getProductCount(Index) + " " +FullData.getProductType(Index));
        }
    }
}
