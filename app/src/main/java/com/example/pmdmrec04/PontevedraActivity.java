package com.example.pmdmrec04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.pmdmrec04.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import sqlite.MyOpenHelper;

public class PontevedraActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    SQLiteDatabase db;

    ImageView imagePontevedra;
    String rutaAbsoluta = "";
    final int FOTO_CONST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontevedra);

        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

        TextView descripcionPontevedra = (TextView) findViewById(R.id.descripcionPontevedra);
        descripcionPontevedra.setMovementMethod(new ScrollingMovementMethod());

        String fase = getIntent().getStringExtra("fase");
        String descripcion = getIntent().getStringExtra("descripcion");
        descripcionPontevedra.setText("Fase: "+fase+":\n "+descripcion);
        imagePontevedra = (ImageView) findViewById(R.id.imagePontevedra);

        existeImagen();

        imagePontevedra.setOnClickListener(new View.OnClickListener() {
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
                Uri fotoUri = FileProvider.getUriForFile(PontevedraActivity.this, "com.example.pmdmrec04", foto);

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
            imagePontevedra.setImageURI(uriPath);
            //imagePontevedra.setImageURI(data.getData());
        }
    }

    private void guardarFotoBaseDatos(String nombre){
        if (db != null) {
            db.execSQL("UPDATE imagenes SET imagen_pontevedra = '"+ nombre + "' WHERE id = 1");
        }
    }

    protected boolean existeImagen() {
        boolean existe = false;
        String imagen = "";
        Cursor c = db.rawQuery("SELECT imagen_pontevedra FROM imagenes WHERE id=1", null);

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

                imagePontevedra.setImageBitmap(b);
                existe = true;
            }
        }else{
            existe = false;
        }
        return existe;
    }
}