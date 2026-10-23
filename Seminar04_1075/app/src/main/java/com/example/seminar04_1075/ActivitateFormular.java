package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateFormular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_formular);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void culegeDate(View view) {
        EditText et = findViewById(R.id.nume_et);
        CheckBox cb = findViewById(R.id.esteZi_CB);
        Spinner sp = findViewById(R.id.spinnerMarimi);
        RatingBar rat = findViewById(R.id.rating);

        String nume = et.getText().toString();
        Boolean esteNou = cb.isChecked();
        String marime = sp.getSelectedItem().toString();
        float rating = rat.getRating();

        Tricou tricou = new Tricou(nume, marime, esteNou, rating);
        Intent it = new Intent();

        Bundle bundle = new Bundle();
        bundle.putSerializable("Tricou", tricou);
        it.putExtras(bundle);

        setResult(RESULT_OK, it);
        finish();

    }
}