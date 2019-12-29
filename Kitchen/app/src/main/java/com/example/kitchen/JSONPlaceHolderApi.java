package com.example.kitchen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("mainpage")
    public Call<List<HomeData>> HomeRequest();

    @POST("/search")
    public Call<List<HomeData>> SearchRecipe(@Body SearchIngredients body);

    @POST("recipefull")
    public Call<FullData> RecipeFull(@Body RecipeID body);

    @POST("addrecipe")
    public Call<String> AddRecipe(@Body AddData body);

}
