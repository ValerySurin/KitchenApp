package com.example.kitchen;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends Fragment {
    List<HomeData> homeData;
    public ProfilePage() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_page, container, false);
        ImageView OptionImage = view.findViewById(R.id.OptionImage);
        ImageView FavouritesImage = view.findViewById(R.id.FavouritesImage);
        ImageView AddImage = view.findViewById(R.id.AddImage);
        ImageView profileImage = view.findViewById(R.id.ProfileImage);
        Picasso.get().load("https://sun9-61.userapi.com/c845216/v845216079/1c96ef/oIDpK5tC6xI.jpg").into(profileImage);




        NetworkService.getInstance()
                .getJSONApi()
//                .getToken(new Authorization("test", "test"))
                .HomeRequest()
                .enqueue(new Callback<List<HomeData>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<HomeData>> call, @NonNull Response<List<HomeData>> response) {

                        homeData = response.body();

                        RecyclerView myRecipe = view.findViewById(R.id.MyRecipe);
                        myRecipe.setNestedScrollingEnabled(false);
                        myRecipe.setItemViewCacheSize(20);
                        myRecipe.setDrawingCacheEnabled(true);
                        myRecipe.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        myRecipe.setLayoutManager(layoutManager);
                        AdapterForTopList MyRecipeAdapter;
                        myRecipe.setHasFixedSize(true);
                        MyRecipeAdapter = new AdapterForTopList(homeData);
                        myRecipe.setAdapter(MyRecipeAdapter);


                    }

                    @Override
                    public void onFailure(@NonNull Call<List<HomeData>> call, @NonNull Throwable t) {

//                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });




//        final Button option = view.findViewById(R.id.Option);
//        option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OptionPage optionPage = new OptionPage();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, optionPage );
//                fragmentTransaction.commit();
//            }
//        });
//
//        final  Button favourites = view.findViewById(R.id.Favourites);
//        favourites.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Top Top = new Top();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, Top );
//                fragmentTransaction.commit();
//            }
//        });

        FavouritesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextUpdate nextUpdate = new NextUpdate();
                FragmentManager fm = getFragmentManager();
//                fm.popBackStack();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame, nextUpdate ).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }


}
