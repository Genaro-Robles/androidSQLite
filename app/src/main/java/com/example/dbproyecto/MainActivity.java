package com.example.dbproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dbproyecto.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar, btnListar, btnSetearDB, btnRegistroCursos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnListar = findViewById(R.id.btnListar);
        btnSetearDB = findViewById(R.id.btnSetearDB);
        btnRegistroCursos = findViewById(R.id.btnAbrirRegistroCursos);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrarEstudiantes.class);
                startActivity(intent);

            }
        });

        btnSetearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if(db != null){
                    Toast.makeText(MainActivity.this,"BASES DE DATOS CREADAS", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListarEstudiantes.class);
                startActivity(intent);
            }
        });

        btnRegistroCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrarCursos.class);
                startActivity(intent);
            }
        });
    }
}
