package com.example.kitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapterForSteps extends PagerAdapter {


    private LayoutInflater layoutInflater;
    private Context context;
    private FullData fullData;

    public ViewPagerAdapterForSteps( Context context, FullData FullData) {
        this.fullData = FullData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fullData.getStepsCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.element_of_steps, container, false);
        ImageView Barrow = view.findViewById(R.id.BeforeArrow);
        ImageView Narrow = view.findViewById(R.id.NextArrow);
        if(position == 0 ){
            Barrow.setVisibility(View.GONE);
        }
        if (position == fullData.getStepsCount()-1){
            Narrow.setVisibility(View.GONE);
        }
        TextView step = view.findViewById(R.id.StepInViewPager);
        step.setText("Шаг #"+ fullData.getStepNumber(position) + "\n" +fullData.getStep(position));



        container.addView(view, position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
