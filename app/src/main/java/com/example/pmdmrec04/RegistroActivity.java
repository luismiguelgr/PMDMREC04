package com.example.pmdmrec04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmdmrec04.R;

import sqlite.MyOpenHelper;
public class RegistroActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    SQLiteDatabase db;
    Button btnRegistrarse;
    EditText textNombre, textApellidos, textEmail, textUsuario, textContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

        btnRegistrarse = (Button) findViewById(R.id.botonRegistrarse);
        textNombre = (EditText) findViewById(R.id.textRegistroNombre);
        textApellidos = (EditText) findViewById(R.id.textRegistroApellidos);
        textEmail = (EditText) findViewById(R.id.textRegistroEmail);
        textUsuario = (EditText) findViewById(R.id.textRegistroUsuario);
        textContrasena = (EditText) findViewById(R.id.textRegistroContrasena);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tUsuario = textUsuario.getText().toString().trim();
                String tNombre = textNombre.getText().toString().trim();
                String tApellidos = textApellidos.getText().toString().trim();
                String tEmail = textEmail.getText().toString().trim();
                String tContrasena = textContrasena.getText().toString().trim();

                if(existeUsuario(tUsuario)){
                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }else{
                    if(tUsuario.isEmpty() || tNombre.isEmpty() || tContrasena.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Los campos de usuario,nombre y contraseña deben rellenarse", Toast.LENGTH_SHORT).show();
                    }else{
                        if (db != null) {
                            ContentValues cv = new ContentValues();
                            cv.put("nombre", tNombre);
                            cv.put("apellidos", tApellidos);
                            cv.put("email", tEmail);
                            cv.put("usuario", tUsuario);
                            cv.put("contrasena", tContrasena);
                            db.insert("usuarios", null, cv);
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            startActivity(intent);
                            textNombre.setText("");
                            textApellidos.setText("");
                            textEmail.setText("");
                            textUsuario.setText("");
                            textContrasena.setText("");
                        }
                    }
                }
            }
        });
    }

    protected boolean existeUsuario(String tUsuario) {
        boolean existe = false;
        String usuarioBd = "";
        Cursor c = db.rawQuery("SELECT usuario FROM usuarios WHERE usuario='" + tUsuario + "'", null);

        if (c.moveToFirst()) {
            usuarioBd = c.getString(0);

            if (tUsuario.equals(usuarioBd)) {
                existe = true;
            } else {
                existe = false;
            }
        }
        return existe;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}