package com.example.ventanadelogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.ET1);
        e2=(EditText)findViewById(R.id.ET2);
        SharedPreferences p=getSharedPreferences("Archivo",MODE_PRIVATE);
        String pref=p.getString("Usuario","");
        //String log=p.getString("Estado","");
        if(!pref.isEmpty() /*&& log=="1"*/){
            Intent i=new Intent(MainActivity.this,Principal.class);
            startActivity(i);
            finish();
        }
    }
    public void Verifica(View view){
        String usuario=e1.getText().toString();
        String contra=e2.getText().toString();
        if(usuario.isEmpty() || contra.isEmpty()){
            Toast.makeText(MainActivity.this,"Debe llenar todos los campos",Toast.LENGTH_LONG).show();
        }else{
            Logear(usuario,contra);
        }
    }

    public void Logear(String usuario, String contra) {
        SharedPreferences p=getSharedPreferences("Archivo",MODE_PRIVATE);
        String user=p.getString("Usuario","");
        String pass=p.getString("Contraseña","");
        if(user==usuario && pass==contra){
            SharedPreferences.Editor editor=p.edit();
            String nombre=p.getString("Nombre","");
            String apellido=p.getString("Apellido","");
            String celu=p.getString("Celular","");
            String correo=p.getString("Correo","");
            String dato="Bienvenido: "+nombre+" "+apellido+"\n"+
                    "Su usuario es: "+usuario+"\n"+
                    "Su número de celular es: "+celu+"\n"+
                    "y su correo es: "+correo;
            editor.putString("Estado","1");
            editor.commit();
            Intent i =new Intent(MainActivity.this,Principal.class);
            i.putExtra("Datos",dato);
            i.putExtra("Dato2",usuario);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(MainActivity.this,"El usuario o la contraseña son inválidos",Toast.LENGTH_LONG).show();
        }
    }

    public void Limpia(View view){
        e1.setText("");
        e2.setText("");
    }
    public void Salir(View view){
        finish();
    }
    public void IrRegistro(View view){
        Intent i=new Intent(MainActivity.this,Registro.class);
        startActivity(i);
        finish();
    }
    public void Recuperar (View view){
        Intent i=new Intent(MainActivity.this,Recuperacion.class);
        startActivity(i);
        finish();
    }
}