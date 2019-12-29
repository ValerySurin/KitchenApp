package com.example.kitchen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeData {

    @SerializedName("id_recipe")
    @Expose
    private int idRecipe;
    @SerializedName("id_user")
    @Expose
    private int idUser;
    @SerializedName("time_of_cook")
    @Expose
    private int timeOfCook;
    @SerializedName("rate_of_recipe")
    @Expose
    private int rate;
    @SerializedName("nickname_of_user")
    @Expose
    private String userName;
    @SerializedName("recipe_photo")
    @Expose
    private String photo;

    public int getIdRecipe() { return idRecipe; }
    public int getIdUser() {
        return idUser;
    }
    public int getTimeOfCook() {
        return timeOfCook;
    }
    public int getRate() {
        return rate;
    }
    public String getUserName() {
        return userName;
    }
    public String getPhoto() {
        return photo;
    }
}
