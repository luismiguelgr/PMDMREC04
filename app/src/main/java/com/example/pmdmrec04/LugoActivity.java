package com.example.pmdmrec04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmdmrec04.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sqlite.MyOpenHelper;

public class LugoActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    SQLiteDatabase db;

    String rutaAbsoluta = "";
    final int FOTO_CONST = 1;
    ImageView imageViewLugo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugo);

        TextView textLugo = (TextView) findViewById(R.id.textLugo);
        TextView descripcionLugo = (TextView) findViewById(R.id.descripcionLugo);

        descripcionLugo.setMovementMethod(new ScrollingMovementMethod());

        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();


        String fase = getIntent().getStringExtra("fase");
        String descripcion = getIntent().getStringExtra("descripcion");
        descripcionLugo.setText("Fase: "+fase+":\n "+descripcion);
        imageViewLugo = (ImageView) findViewById(R.id.imageViewLugo);

        existeImagen();

        imageViewLugo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacarFoto();
            }
        });
    }

    private void sacarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            File foto = null;

            try {
                foto = crearArchivoFoto();
            }catch (Exception e){
                e.printStackTrace();
            }
            if(foto != null){
                Uri fotoUri = FileProvider.getUriForFile(LugoActivity.this, "com.example.pmdmrec04", foto);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intent, FOTO_CONST);
            }
        }
    }

    private File crearArchivoFoto(){
        String nombreImagen = "imagen";
        File fotoFile = null;
        File rutaArchivo = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            fotoFile = File.createTempFile(nombreImagen, ".jpg", rutaArchivo);
            guardarFotoBaseDatos(fotoFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        rutaAbsoluta = fotoFile.getAbsolutePath();
        return fotoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FOTO_CONST && resultCode == RESULT_OK){
            Uri uriPath = Uri.parse(rutaAbsoluta);
            imageViewLugo.setImageURI(uriPath);
        }
    }

    private void guardarFotoBaseDatos(String nombre){
        if (db != null) {
            db.execSQL("UPDATE imagenes SET imagen_lugo = '"+ nombre + "' WHERE id = 1");
        }
    }

    protected boolean existeImagen() {
        boolean existe = false;
        String imagen = "";
        Cursor c = db.rawQuery("SELECT imagen_lugo FROM imagenes WHERE id=1", null);

        if (c.moveToFirst()) {
            imagen = c.getString(0);
            if(!imagen.isEmpty()){
                File img = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+imagen);
                Bitmap b = null;
                try {
                    b = BitmapFactory.decodeStream(new FileInputStream(img));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imageViewLugo.setImageBitmap(b);
                existe = true;
            }
        }else{
            existe = false;
        }
        return existe;
    }
}