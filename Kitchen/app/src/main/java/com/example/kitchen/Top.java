package com.example.kitchen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top extends Fragment  {

    List<HomeData> homeData;


    public Top() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_list, container, false);

        NetworkService.getInstance()
                .getJSONApi()
//                .getToken(new Authorization("test", "test"))
                .HomeRequest()
                .enqueue(new Callback<List<HomeData>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<HomeData>> call, @NonNull Response<List<HomeData>> response) {
                        homeData = response.body();
                        RecyclerView Top = view.findViewById(R.id.top);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        Top.setItemViewCacheSize(20);
                        Top.setDrawingCacheEnabled(true);
                        Top.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
