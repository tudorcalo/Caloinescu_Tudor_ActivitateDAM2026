package com.example.seminar04_1075;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Tricou implements Parcelable {
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

    protected Tricou(Parcel in) {
        marca = in.readString();
        marime = in.readString();
        esteNou = in.readByte() != 0;
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(marime);
        dest.writeByte((byte) (esteNou ? 1 : 0));
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tricou> CREATOR = new Creator<Tricou>() {
        @Override
        public Tricou createFromParcel(Parcel in) {
            return new Tricou(in);
        }

        @Override
        public Tricou[] newArray(int size) {
            return new Tricou[size];
        }
    };

    @Override
    public String toString() {
        return "Brand: " + marca + "\n" +
                "Marime: " + marime + "\n" +
                "Stare: " + (esteNou ? "Nou" : "Second Hand") + "\n" +
                "Rating: " + rating + " stele";
    }


    public String getMarca() {
        return marca;
    }

    public String getMarime() {
        return marime;
    }

    public boolean isEsteNou() {
        return esteNou;
    }

    public float getRating() {
        return rating;
    }
}