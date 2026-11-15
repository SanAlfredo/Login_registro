package com.example.ventanadelogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tv1=(TextView)findViewById(R.id.TV1);
        String datos=getIntent().getStringExtra("Datos");
        tv1.setText(datos);
    }
    public void Cerrar(View view){
        SharedPreferences p=getSharedPreferences("Archivo",MODE_PRIVATE);
        String usuario1=p.getString("Usuario","");
        String usuario=getIntent().getStringExtra("Datos2");
        SharedPreferences.Editor editor=p.edit();
        if(usuario==usuario1){
            editor.putString("Estado","0");
            editor.commit();
            Intent i =new Intent(Principal.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}