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

    public String getName() {
        return name;
    }

    public String getPricePerCount() {
        return String.valueOf(pricePerCount);
    }

    public String getCount() {
        return String.valueOf(count);
    }

    public String getUnit() {
        return unit;
    }

}
