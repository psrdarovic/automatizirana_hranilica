package com.nizetic.yuumi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnDalje, btnRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView8);


        btnDalje = findViewById(R.id.btnDalje);
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent namjera = new Intent(MainActivity.this, StanjeZ.class);
                startActivity(namjera);
            }
        });

        btnRv = findViewById(R.id.btnRv);
        btnRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent namjera = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(namjera);
            }
        });
    }
}
