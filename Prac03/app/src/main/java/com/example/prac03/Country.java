package com.example.prac03;

public class Country {
    private String name;
    private String capital;
    private long population;
    private double area;
    private int density;
    private double worldShare;
    private int flagResource; // Drawable resource for the flag

    public Country(String name, String capital, long population, double area, int density, double worldShare, int flagResource) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.density = density;
        this.worldShare = worldShare;
        this.flagResource = flagResource;
    }

    // Getters for all fields
}
