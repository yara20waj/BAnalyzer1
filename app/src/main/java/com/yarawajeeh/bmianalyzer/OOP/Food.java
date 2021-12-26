package com.yarawajeeh.bmianalyzer.OOP;

public class Food {
    private String name;
    private String category;
    private String calory;
    private int image;
    private String id;

    public Food(String name,String category,String calory,int image) {
        this.name = name;
        this.category= category;
        this.calory = calory;
        this.image=image;
    }
    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
