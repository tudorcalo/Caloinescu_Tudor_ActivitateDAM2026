package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateListare extends AppCompatActivity {

    ArrayList<Tricou> listaPrimita;
    private TricouAdapter adapter;

    private int idModificat = 0;
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


        listaPrimita = getIntent().getParcelableArrayListExtra("lista_tricouri");

        if (listaPrimita == null) {
            listaPrimita = new ArrayList<>();
        }

        adapter = new TricouAdapter(listaPrimita, getApplicationContext(), R.layout.item_layout);

//
//        ArrayAdapter<Tricou> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                listaPrimita
//        );
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tricou tricouSelectat = listaPrimita.get(position);

                Intent intentModifica = new Intent(ActivitateListare.this, ActivitateFormular.class);
                intentModifica.putExtra("Tricou", tricouSelectat);
                idModificat = position;
                startActivityForResult(intentModifica, 209);

                Toast.makeText(ActivitateListare.this, tricouSelectat.toString(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaPrimita.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        Button buton_exit = findViewById(R.id.btn_exit);
        buton_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 209) {
                listaPrimita.set(idModificat, data.getParcelableExtra("Tricou"));
                adapter.notifyDataSetChanged();
            }
        }}
}