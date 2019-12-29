package com.example.kitchen;

import java.util.List;

public class AddData {

    private int id;
    private String photo;
    private List<Integer> product;
    private List<String> productType;
    private List<Integer> productCount;
    private List<String> stepContent;
    private List<Integer> stepNumber;
    private int time;


    public AddData(int ID, String Photo, List<Integer> Product, List<String> ProductType, List<Integer> ProductCount, List<String> StepContent, List<Integer> StepNumber, int ET){
        id = ID;
        photo = Photo;
        product = Product;
        productType = ProductType;
        productCount = ProductCount;
        stepContent = StepContent;
        stepNumber = StepNumber;
        time = ET;
    }

//    public void addId(int ID){
//        id = ID;
//    }
//
//    public void addPhoto(String Photo){
//        photo = Photo;
//    }
//
//    public void addProduct(List<String> Product){
//        product = Product;
//    }
//
//    public void addProductType(List<String> ProductType){
//        productType = ProductType;
//    }
//
//    public void addProductCount(List<Integer> ProductCount){
//        productCount = ProductCount;
//    }
//
//    public void addStepContent(List<String> StepContent){
//        stepContent = StepContent;
//    }
//
//    public void addStepNumber(List<Integer> StepNumber){
//        stepNumber = StepNumber;
//    }

}
