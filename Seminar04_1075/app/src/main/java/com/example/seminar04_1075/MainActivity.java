package com.example.seminar04_1075;

import static android.widget.Toast.LENGTH_LONG;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Tricou> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdauga = findViewById(R.id.btn_adauga);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ActivitateFormular.class);
                startActivityForResult(it, 1);
            }
        });


        Button btnVeziLista = findViewById(R.id.btn_vezi_lista);
        btnVeziLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitateListare.class);
                intent.putExtra("lista_tricouri", (ArrayList<Tricou>) lista);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller)
    {
        super.onActivityResult(requestCode, resultCode, data, caller);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            Tricou tr = (Tricou) data.getSerializableExtra("Tricou");
            if (tr != null)
            {
                TextView text = findViewById(R.id.tb_rezultat);
                text.setText(tr.toString());
                Toast.makeText(this, tr.toString(), LENGTH_LONG).show();
                lista.add(tr);

            }

        }


    }







    //
}