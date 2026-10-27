package com.example.seminar04_1075;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateListare extends AppCompatActivity {

    ArrayList<Tricou> listaPrimita;
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


        listaPrimita = (ArrayList<Tricou>) getIntent().getSerializableExtra("lista_tricouri");

        if (listaPrimita == null) {
            listaPrimita = new ArrayList<>();
        }



//
        ArrayAdapter<Tricou> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaPrimita
        );
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tricou tricouSelectat = listaPrimita.get(position);
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
}