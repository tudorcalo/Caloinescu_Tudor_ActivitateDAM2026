package com.example.seminar09_1075;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FormActivity extends AppCompatActivity {

    String apiKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        EditText etOras = findViewById(R.id.etOras);
        Spinner spinnerZile = findViewById(R.id.spinnerZile);
        Button btnCauta = findViewById(R.id.btnCauta);
        TextView tvRezultat = findViewById(R.id.tvRezultat);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        btnCauta.setOnClickListener(v -> {
            String oras = etOras.getText().toString();
            String tipPrognoza = spinnerZile.getSelectedItem().toString();

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        // PARTEA I obtinere cod cu citysearch
                        String link = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + oras;
                        URL url = new URL(link);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder builder = new StringBuilder();
                        String linie;
                        while ((linie = reader.readLine()) != null) {
                            builder.append(linie);
                        }
                        reader.close();

                        JSONArray vector = new JSONArray(builder.toString());
                        JSONObject obiect = vector.getJSONObject(0);
                        String cheieOras = obiect.getString("Key");




                        // partea 2 si 3: 1 day sau 5 day
                        String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/" + tipPrognoza + "/" + cheieOras + "?apikey=" + apiKey + "&metric=true";
                        URL urlVreme = new URL(linkVreme);
                        HttpURLConnection connVreme = (HttpURLConnection) urlVreme.openConnection();
                        BufferedReader readerVreme = new BufferedReader(new InputStreamReader(connVreme.getInputStream()));
                        StringBuilder builderVreme = new StringBuilder();
                        while ((linie = readerVreme.readLine()) != null) {
                            builderVreme.append(linie);
                        }
                        readerVreme.close();

                        JSONObject objVreme = new JSONObject(builderVreme.toString());
                        JSONArray dailyForecasts = objVreme.getJSONArray("DailyForecasts");

                        StringBuilder textAfisare = new StringBuilder("Cod oras: " + cheieOras + "\n");

                        for (int i = 0; i < dailyForecasts.length(); i++) {
                            JSONObject zi = dailyForecasts.getJSONObject(i);
                            JSONObject temp = zi.getJSONObject("Temperature");
                            double min = temp.getJSONObject("Minimum").getDouble("Value");
                            double max = temp.getJSONObject("Maximum").getDouble("Value");

                            textAfisare.append("Ziua ").append(i + 1)
                                    .append(" - Min: ").append(min)
                                    .append(" Max: ").append(max).append("\n");
                        }

                        // intoarcere pe main thread
                        handler.post(() -> tvRezultat.setText(textAfisare.toString()));

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        // pt erori
                        handler.post(() -> tvRezultat.setText("Eroare: " + e.toString()));
                    }
                }
            });


        });




    }
}