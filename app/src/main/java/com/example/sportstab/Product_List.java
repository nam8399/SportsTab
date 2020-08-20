package com.example.sportstab;

class Product_List {
    private int id;
    private int date;
    private String name;
    private String name2;
    private String name3;
    private String image;
    private String image2;

    public Product_List(int id, int date, String name, String name2, String name3, String image, String image2) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.image = image;
        this.image2 = image2;
    }

    public int getId() {
        return id;
    }

    public int getDate() { return date; }

    public String getName() {
        return name;
    }

    public String getName2() { return  name2; }

    public String getName3() { return name3; }

    public String getImage() {
        return image;
    }

    public String getImage2() { return image2; }





}
