package com.nizetic.yuumi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView rvPopisZdjela;
    ZdjelaAdapter zdjAdapter;
    AppDatabase db;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mojabaza")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        rvPopisZdjela = findViewById(R.id.rvPopisZ);
        rvPopisZdjela.setLayoutManager(new LinearLayoutManager(this));

        zdjAdapter = new ZdjelaAdapter();
        rvPopisZdjela.setAdapter(zdjAdapter);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(RecyclerViewActivity.this, MainActivity.class);
            startActivity(intent);
        });

        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            String url = "http://192.168.0.22:3002/stanje";
            String response = fetchDataFromServer(url);

            if (response != null) {
                Log.d("RecyclerViewActivity", "Response: " + response);
                Zdjela zdjela = parseResponse(response);

                if (zdjela != null) {
                    db.zdjelaDAO().insert(zdjela);
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(RecyclerViewActivity.this, "Failed to parse data from server.", Toast.LENGTH_SHORT).show();
                    });
                }

                List<Zdjela> zdjelaList = db.zdjelaDAO().fetchAll();
                runOnUiThread(() -> {
                    if (zdjelaList != null && !zdjelaList.isEmpty()) {
                        zdjAdapter.replaceAll(zdjelaList);
                    } else {
                        Toast.makeText(RecyclerViewActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(RecyclerViewActivity.this, "Failed to fetch data from server.", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private String fetchDataFromServer(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            Log.e("RecyclerViewActivity", "Error fetching data from server", e);
        }
        return result.toString();
    }

    private Zdjela parseResponse(String response) {
        try {
            int stanje = Integer.parseInt(response.trim());
            return new Zdjela(stanje);
        } catch (NumberFormatException e) {
            Log.e("RecyclerViewActivity", "Parsing error: ", e);
            return null;
        }
    }
}
