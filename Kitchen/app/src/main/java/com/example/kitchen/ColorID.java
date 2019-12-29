package com.example.kitchen;

public class ColorID {
    private int color;
    private String colorName;

    public ColorID( int Color, String ColorName){
        this.color = Color;
        this.colorName = ColorName;

    }

    public String getColorName(){
        return colorName;
    }
    public int getColorId(){
        return color;
    }
}
