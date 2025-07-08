package com.nizetic.yuumi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StanjeZ extends AppCompatActivity {

    TextView txtVaga;
    Button btnOtvori;
    Button btnNatrag;
    Button btnLog; // NOVI GUMB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stanje_z);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnOtvori = findViewById(R.id.btnOtvori);
        txtVaga = findViewById(R.id.txtVaga);
        btnNatrag = findViewById(R.id.btnNatrag);
        btnLog = findViewById(R.id.btnLog); // NOVI GUMB

        // Prikaz težine svake 5 sekundi
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        Handler handler = new Handler();

        Runnable fetchStanje = new Runnable() {
            @Override
            public void run() {
                String rez = NetFetch.fetch("http://192.168.0.22:3002/stanje");
                if (rez != null) {
                    handler.post(() -> txtVaga.setText(rez));
                }
                exec.schedule(this, 5, TimeUnit.SECONDS);
            }
        };
        exec.execute(fetchStanje);

        // Otvori hranilicu
        btnOtvori.setOnClickListener(view -> {
            ExecutorService e = Executors.newSingleThreadExecutor();
            e.execute(() -> {
                String rez = NetFetch.fetch("http://192.168.0.22:3002/otvori");
                runOnUiThread(() -> {
                    if (rez != null && rez.trim().equals("ok")) {
                        Toast.makeText(getApplicationContext(), "Hrana je izbačena!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Greška pri slanju!", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

        // Povratak na glavni ekran
        btnNatrag.setOnClickListener(v -> {
            Intent namjera = new Intent(StanjeZ.this, MainActivity.class);
            startActivity(namjera);
        });

        // OTVARANJE LOGOVA
        btnLog.setOnClickListener(v -> {
            Intent intent = new Intent(StanjeZ.this, LogActivity.class);
            startActivity(intent);
        });
    }
}
