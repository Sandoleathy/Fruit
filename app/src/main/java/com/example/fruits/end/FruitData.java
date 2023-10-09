package com.example.fruits.end;

public class FruitData {
    private int imageID;
    private int name;
    private int description;
    public FruitData(int id , int name , int desc){
        this.description = desc;
        this.imageID = id;
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public int getDescription() {
        return description;
    }

    public int getName() {
        return name;
    }
}
