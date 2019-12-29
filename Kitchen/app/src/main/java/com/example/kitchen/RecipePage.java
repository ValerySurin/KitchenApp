package com.example.kitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipePage extends AppCompatActivity {

    FullData fullData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page_v2);
//        findViewById(R.id.start).requestFocus();


        Bundle extras = getIntent().getExtras();
        int IdRecipe = extras.getInt("IdRecipe");
        String UserName = extras.getString("UserName");
        int CookTime = extras.getInt("CookTime");
        String Photo = extras.getString("Photo");


        TextView userName = findViewById(R.id.RecipePageUserName);
        TextView cookTime = findViewById(R.id.RecipePageCookTime);
        ImageView photo = findViewById(R.id.RecipePagePhoto);


        Picasso.get().load(Photo).into(photo);
        userName.setText(UserName);
        cookTime.setText("Время приготовления: " + CookTime + " минут");



        RecipeID recipeID = new RecipeID(IdRecipe);

        NetworkService.getInstance()
                .getJSONApi()
                .RecipeFull(recipeID)
                .enqueue(new Callback<FullData>() {
                    @Override
                    public void onResponse(@NonNull Call<FullData> call, @NonNull Response<FullData> response) {

                        fullData = response.body();






                        RecyclerView recyclerView;
                        recyclerView =  findViewById(R.id.ingredientsList);
                        recyclerView.setLayoutFrozen(true);
                        recyclerView.setNestedScrollingEnabled(false);

                        final AdapterForRecipePage adapterForRecipePage = new AdapterForRecipePage(fullData);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapterForRecipePage);
                        recyclerView.setHasFixedSize(true);


                        ViewPagerAdapterForSteps viewPageAdapterForCard = new ViewPagerAdapterForSteps(getApplicationContext(), fullData);
                        ViewPager viewPager = findViewById(R.id.mainViewPager);
                        viewPager.setAdapter(viewPageAdapterForCard);
                        viewPager.setPadding(10, 0, 10, 0);
                        viewPager.setCurrentItem(0);


                        

                    }

                    @Override
                    public void onFailure(@NonNull Call<FullData> call, @NonNull Throwable t) {

//                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });







    }
}
