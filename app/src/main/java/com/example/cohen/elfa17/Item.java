package com.example.cohen.elfa17;

public class Item {
   public String id;
   public String name;
   public double dirug;
    public  int counter;

    public Item(String id, String name, double dirug, int counter) {
        this.id = id;
        this.name = name;
        this.dirug = dirug;
        this.counter=0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double  getDirug() {
        return dirug;
    }

    public void setDirug(double dirug) {

         this.dirug=dirug;

    }

    public Item() {
    }
}
