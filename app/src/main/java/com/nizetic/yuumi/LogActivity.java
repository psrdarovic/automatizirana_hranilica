package com.nizetic.yuumi;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private List<String> logovi;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        recyclerView = findViewById(R.id.recyclerLog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // Zatvara aktivnost

        logovi = new ArrayList<>();

        new Thread(() -> {
            String rezultat = NetFetch.fetch("http://192.168.0.22:3002/log");

            if (rezultat != null && !rezultat.trim().isEmpty()) {
                String[] redovi = rezultat.split("\\n");
                for (String r : redovi) {
                    logovi.add(r);  // cijeli red kao jedan string
                }
            }

            runOnUiThread(() -> {
                adapter = new LogAdapter(logovi);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}

