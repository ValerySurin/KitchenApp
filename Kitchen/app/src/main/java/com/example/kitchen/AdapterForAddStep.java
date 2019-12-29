package com.example.kitchen;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

public class AdapterForAddStep extends RecyclerView.Adapter<AdapterForAddStep.AddStepViewHolder> {

public static List<Integer> stepNumber;
public static List<String> stepContent;



    public AdapterForAddStep(List<Integer> StepNumber, List<String> StepContent) {
        this.stepNumber = StepNumber;
        this.stepContent = StepContent;
    }

    @NonNull
    @Override
    public AdapterForAddStep.AddStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;
        final Context context = parent.getContext();

        v = LayoutInflater.from(context).inflate(R.layout.add_step_element, parent,false);
        final AdapterForAddStep.AddStepViewHolder vHolder = new AdapterForAddStep.AddStepViewHolder(v);



        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForAddStep.AddStepViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {

        return stepNumber.size();
    }

    public static  class  AddStepViewHolder extends  RecyclerView.ViewHolder{
        TextView Step;

        public AddStepViewHolder(@NonNull View itemView) {
            super(itemView);
            Step = itemView.findViewById(R.id.StepInAddStep);
        }
        void bind(int Index){

            Step.setText("Шаг #" + stepNumber.get(Index) + "\n" + stepContent.get(Index));

        }
    }

}
