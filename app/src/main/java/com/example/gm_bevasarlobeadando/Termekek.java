package com.example.gm_bevasarlobeadando;

public class Termekek {
    String name;
    int pricePerCount;
    int count;
    String unit;
    float price;

    public Termekek(String name, int pricePerCount, int count, String unit) {
        this.name = name;
        this.pricePerCount = pricePerCount;
        this.count = count;
        this.unit = unit;
        float fullPrice = (float) pricePerCount * count;
        this.price = Math.round(fullPrice * 100) / 100.0f;
    }

}
