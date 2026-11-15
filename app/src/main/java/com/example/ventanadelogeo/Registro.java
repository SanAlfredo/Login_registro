package com.example.ventanadelogeo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Registro extends AppCompatActivity {
    private EditText e3, e4, e5, e6, e7, e8, e9;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        e3 = (EditText) findViewById(R.id.ET3);
        e4 = (EditText) findViewById(R.id.ET4);
        e5 = (EditText) findViewById(R.id.ET5);
        e6 = (EditText) findViewById(R.id.ET6);
        //e7 = (EditText) findViewById(R.id.ET7);
        e8 = (EditText) findViewById(R.id.ET8);
        e9 = (EditText) findViewById(R.id.ET9);
    }
    public void Verif(View view){
        String nombre=e3.getText().toString();
        String apellido=e4.getText().toString();
        String usuario=e5.getText().toString();
        String contraseña=e6.getText().toString();
        //String contraseña2=e7.getText().toString();
        String celular=e8.getText().toString();
        String correo=e9.getText().toString();
        String estado="0";
        if(nombre.isEmpty() ||apellido.isEmpty() || usuario.isEmpty() ||contraseña.isEmpty() ||fecha.isEmpty() ||
                celular.isEmpty() ||correo.isEmpty()){
            Toast.makeText(Registro.this,"Debe llenar todos los campos",Toast.LENGTH_LONG).show();
        }else{
            SharedPreferences p=getSharedPreferences("Archivo",MODE_PRIVATE);
            String dato=p.getString("Usuario","");
            if(dato==usuario){
                Toast.makeText(Registro.this,"El usuario ya existe por favor ingrese otro usuario",
                        Toast.LENGTH_LONG).show();
            }else{
                    Guardar(nombre,apellido,usuario,contraseña,fecha,celular,correo,estado);
            }
        }
    }

    public void Guardar(String nombre, String apellido, String usuario, String contraseña, String fecha, String celular, String correo, String estado) {
        SharedPreferences p=getSharedPreferences("Archivo",MODE_PRIVATE);
        SharedPreferences.Editor dato=p.edit();
        dato.putString("Nombre",nombre);
        dato.putString("Apellido",apellido);
        dato.putString("Usuario",usuario);
        dato.putString("Contraseña",contraseña);
        dato.putString("Fecha",fecha);
        dato.putString("Celular",celular);
        dato.putString("Correo",correo);
        dato.putString("Estado",estado);
        dato.commit();
        Toast.makeText(Registro.this,"Guardado exitosamente",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Registro.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void fecha_nacimiento(View view)
    {
        final Calendar c =Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int año = c.get(Calendar.YEAR);
        DatePickerDialog dialogofecha=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                fecha=i2+"/"+(i1+1)+"/"+i;
            }
        }
                ,año,mes,dia);
        dialogofecha.show();
    }
}