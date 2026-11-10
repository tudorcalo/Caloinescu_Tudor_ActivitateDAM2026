package com.example.seminar04_1075;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateImagini extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        List<ImagineObiect> lista = new ArrayList<>();

        List<Bitmap> listaBitMap = new ArrayList<>();

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://gomagcdn.ro/domains/colorescu.ro/files/product/large/tricou-decebal-culoare-neagra-740037.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains/colorescu.ro/files/product/original/tricou-lup-barbat-culoare-alba-cdac123-559154.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains/colorescu.ro/files/product/original/tricou-trekking-munte-material-tehnic-sport-barbat-culoare-alba-cs65-382024.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains/colorescu.ro/files/product/original/tricou-sfinx-romania-barbat-culoare-galben-mustar-crp107-536568.jpg");
        linkuriImagini.add("https://gomagcdn.ro/domains/colorescu.ro/files/product/original/tricou-oaia-neagra-barbat-culoare-neagra-ct65-921139.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());


        ListView lv = findViewById(R.id.lv_imagini);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini)
                {
                    HttpURLConnection con = null;
                    try
                    {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        listaBitMap.add(BitmapFactory.decodeStream(is));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista.add(new ImagineObiect("t1", listaBitMap.get(0), linkuriImagini.get(0)));
                        lista.add(new ImagineObiect("t2", listaBitMap.get(1), linkuriImagini.get(1)));
                        lista.add(new ImagineObiect("t3", listaBitMap.get(2), linkuriImagini.get(2)));
                        lista.add(new ImagineObiect("t4", listaBitMap.get(3), linkuriImagini.get(3)));
                        lista.add(new ImagineObiect("t5", listaBitMap.get(4), linkuriImagini.get(4)));


                        CustomAdapterImg adapter = new CustomAdapterImg(lista, getApplication(), R.layout.img_item_layout);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}