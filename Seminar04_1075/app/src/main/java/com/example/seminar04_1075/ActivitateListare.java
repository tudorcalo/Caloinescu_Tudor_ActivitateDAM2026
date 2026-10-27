package com.example.seminar04_1075;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateListare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_listare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ArrayList<Tricou> listaPrimita = (ArrayList<Tricou>) getIntent().getSerializableExtra("lista_tricouri");

        if (listaPrimita == null) {
            listaPrimita = new ArrayList<>();
        }


        ArrayAdapter<Tricou> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaPrimita
        );
//        listView.setAdapter(adapter);
    }
}