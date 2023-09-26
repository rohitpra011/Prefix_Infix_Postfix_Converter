package com.rohit.prefixinfixpostfixconverter;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Prefix Infix Postfix Converter");
        Button b1=findViewById(R.id.p2ip);
        Button b2=findViewById(R.id.i2pp);
        Button b3=findViewById(R.id.p2pi);
        b1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(MainActivity.this, "Please enter correct expression to avoid app crash.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,P2IP.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Please enter correct expression to avoid app crash.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,I2PP.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Please enter correct expression to avoid app crash.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,P2PI.class));
            }
        });
    }
}