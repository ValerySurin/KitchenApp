package com.example.kitchen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultSearch extends Fragment {


    List<HomeData> homeData;
    String Tag = getTag();

    public ResultSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_list, container, false);
        List<Integer> tmp = new ArrayList<>();
        tmp  = ((MainActivity) getActivity()).GetTag();

        int p = 0;

        NetworkService.getInstance()
                .getJSONApi()
//                .getToken(new Authorization("test", "test"))
                .SearchRecipe(new SearchIngredients(tmp))
                .enqueue(new Callback<List<HomeData>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<HomeData>> call, @NonNull Response<List<HomeData>> response) {
                        homeData = response.body();


                        RecyclerView Top = view.findViewById(R.id.top);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        @SuppressLint("WrongConstant") RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        SnapHelper snapHelper = new PagerSnapHelper();
                        Top.setLayoutManager(layoutManager);
//        snapHelper.attachToRecyclerView(Top);
                        AdapterForTopList TopAdapter;
                        Top.setHasFixedSize(true);
                        TopAdapter = new AdapterForTopList(homeData);
                        Top.setAdapter(TopAdapter);



                    }

                    @Override
                    public void onFailure(@NonNull Call<List<HomeData>> call, @NonNull Throwable t) {

//                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });





        return view;
    }
}
