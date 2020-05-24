package com.example.pmdmrec04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pmdmrec04.R;

public class AdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}