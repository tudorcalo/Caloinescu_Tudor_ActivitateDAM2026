package com.example.seminar04_1075;

import java.io.Serializable;

public class Tricou implements Serializable {
    private String marca;         // preluat din EditText
    private String marime; // preluat din RadioButton
    private boolean esteNou;      // preluat din Switch
    private float rating;         // preluat din RatingBar

    public Tricou(String marca, String marime, boolean esteNou, float rating) {
        this.marca = marca;
        this.marime = marime;
        this.esteNou = esteNou;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Brand: " + marca + "\n" +
                "Marime: " + marime + "\n" +
                "Stare: " + (esteNou ? "Nou" : "Second Hand") + "\n" +
                "Rating: " + rating + " stele";
    }
}