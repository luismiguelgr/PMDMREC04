package com.example.pmdmrec04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import sqlite.MyOpenHelper;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    SQLiteDatabase db;
    Button  btnInvitado, btnAdmin, btnRegistro;
    EditText textUsuario, textContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

        btnInvitado = (Button) findViewById(R.id.buttonInvitado);
        btnAdmin = (Button) findViewById(R.id.buttonAdmin);
        btnRegistro = (Button) findViewById(R.id.buttonRegistro);
        textContrasena = (EditText) findViewById(R.id.textContrasena);
        textUsuario = (EditText) findViewById(R.id.textUsuario);

        btnInvitado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InvitadoActivity.class);
                startActivity(intent);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(existeUsuario()){
                    Intent intent = new Intent(v.getContext(), AdministradorActivity.class);
                    startActivity(intent);
                    textUsuario.setText("");
                    textContrasena.setText("");
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RegistroActivity.class);
                    startActivity(intent);
                    textUsuario.setText("");
                    textContrasena.setText("");
            }
        });

    }

    private boolean existeUsuario(){
        boolean existe = false;
        String usuarioBd ="";
        String passBd = "";
        String tUsuario = textUsuario.getText().toString().trim();
        String tContrasena = textContrasena.getText().toString().trim();

        Cursor c =db.rawQuery("SELECT usuario, contrasena FROM usuarios WHERE usuario='"+tUsuario+"'", null);

        if(c.moveToFirst()){
            usuarioBd = c.getString(0);
            passBd = c.getString(1);

            if(tContrasena.equals(passBd)){
                existe = true;

            }else{
                Toast.makeText(getApplicationContext(),"Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
        return existe;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
