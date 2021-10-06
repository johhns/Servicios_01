package com.developer.johhns.servicios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar, btnDetener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar) ;
        btnDetener = findViewById(R.id.btnDetener) ;

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService( new Intent( MainActivity.this , ServicioMusica.class ) ) ;
            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService( new Intent( MainActivity.this , ServicioMusica.class ) ) ;
            }
        });

    }
}