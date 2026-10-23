package com.example.seminar04_1075;

import static android.widget.Toast.LENGTH_LONG;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

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
    }

    public void deschideForm(View view) {
        Intent it = new Intent(this, ActivitateFormular.class);
        startActivityForResult(it, 1);
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
            }

        }


    }







    //
}