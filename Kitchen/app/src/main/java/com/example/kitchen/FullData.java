package com.example.kitchen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FullData {

    @SerializedName("id")
    @Expose
    private int idRecipe;
    @SerializedName("productName")
    @Expose
    private List<String> productName;
    @SerializedName("productColor")
    @Expose
    private List<String> productColor;
    @SerializedName("productCount")
    @Expose
    private List<Integer> productCount;
    @SerializedName("productType")
    @Expose
    private List<String> productType;
    @SerializedName("steps")
    @Expose
    private List<String> steps;
    @SerializedName("stepNumber")
    @Expose
    private List<Integer> stepNumber;

    public int getIdRecipe() { return idRecipe; }
    public String getProductName(int i) {
        return productName.get(i);
    }
    public String getProductColor(int i) {
        return productColor.get(i);
    }
    public int getProductCount(int i) {
        return productCount.get(i);
    }
    public String getProductType(int i) {
        return productType.get(i);
    }
    public String getStep(int i) { return steps.get(i);}
    public int getStepNumber(int i) { return stepNumber.get(i); }
    public int getStepsCount (){return  steps.size();}
    public int getProductSize (){return  productName.size();}
    }

