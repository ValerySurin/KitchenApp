package com.example.kitchen;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String Name;
    private int PicId;
    private  int ID;


    public String getName(){
        return Name;
    }

    public int getPicId(){
        return PicId;
    }

    public int getId(){
        return ID;
    }

    public Food(String name, int picId, int id) {
        Name = name;
        PicId = picId;
        ID = id;

    }



}
