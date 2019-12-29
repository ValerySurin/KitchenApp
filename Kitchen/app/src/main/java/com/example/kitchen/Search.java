package com.example.kitchen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Search extends Fragment  {
    private RecyclerView recyclerView;
    private List<Food> food = new ArrayList<>();
    private EditText FoodName;
    private ChipGroup mchipGroup;
    private List<Integer> ProductList = new ArrayList<>();

    public Search() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search, container, false);
        mchipGroup = view.findViewById(R.id.foodGroup);
        mchipGroup.setSingleSelection(true);



        recyclerView = (RecyclerView) view.findViewById(R.id.foodList);
        final AdapterForSearch[] adapterForSearch = {new AdapterForSearch(getContext(), food, mchipGroup, view)};
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterForSearch[0]);
        FoodName = view.findViewById(R.id.foodName);







        FoodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String userInput = charSequence.toString();
                List<Food> NewFood = new ArrayList<>();

                for(Food Food : food  ){
                    if (Pattern.compile(Pattern.quote(userInput), Pattern.CASE_INSENSITIVE).matcher(Food.getName()).find()){
                        NewFood.add(Food);
//                        Pattern.compile(Pattern.quote(Food.getName()), Pattern.CASE_INSENSITIVE).matcher(userInput).find();
//                        Food.getName().contains(userInput)
                    }
                }

                 adapterForSearch[0] = new AdapterForSearch(getContext(), NewFood, mchipGroup, view);
                 recyclerView.setAdapter(adapterForSearch[0]);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Button searchButton = view.findViewById(R.id.SearchButton);
        final ChipGroup foodGroup = view.findViewById(R.id.foodGroup);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFoodList();
                ((MainActivity) getActivity()).SetTag(ProductList);
                ResultSearch resultSearch = new ResultSearch();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, resultSearch);
                fragmentTransaction.commit();



            }
        });



        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        List<String> Name = new ArrayList<>();
//        Name.add("Помидор");
//        Name.add("Курица");
//        Name.add("Свинина");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");
//        Name.add("Молоко");


//        int[] imgId = {R.color.meat, R.color.fish, R.color.fruits, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable, R.color.vegetable};


//        int count = 0;
//        for(String name : Name){
//            food.add(new Food(name, imgId[count]));
//            count++;
//        }

        food.add(new Food("Свинина", R.color.meat, 1));
        food.add(new Food("Красный перец", R.color.spice, 2));
        food.add(new Food("Соевый соус", R.color.sause, 3));
        food.add(new Food("Соль", R.color.spice, 4));
        food.add(new Food("Яйцо куриное", R.color.meat, 5));


    }

    private void  getFoodList(){
            int count = 0;
            while (count<mchipGroup.getChildCount()) {

                Chip chip = (Chip) mchipGroup.getChildAt(count);
                for (int i = 0; i<food.size(); i++){
                    if(chip.getText().toString().equals(food.get(i).getName())){
                        ProductList.add(food.get(i).getId());
                    }
                }
                count++;
            }
    }



}
