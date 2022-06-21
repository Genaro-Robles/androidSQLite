package com.example.dbproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbproyecto.adaptadores.ListaEstudiantesAdapter;
import com.example.dbproyecto.db.dbEstudiantes;
import com.example.dbproyecto.entidades.Estudiantes;

import java.util.ArrayList;

public class ListarEstudiantes extends AppCompatActivity {

    RecyclerView listaEstudiantes;
    ArrayList<Estudiantes> listaArraytEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estudiantes);

        listaEstudiantes = findViewById(R.id.listaEstudiantes);
        listaEstudiantes.setLayoutManager(new LinearLayoutManager(this));

        dbEstudiantes dbestudiantes = new dbEstudiantes(this);
        listaArraytEstudiantes = new ArrayList<>();

        ListaEstudiantesAdapter adapter = new ListaEstudiantesAdapter(dbestudiantes.mostrarEstudiantes());
        listaEstudiantes.setAdapter(adapter);

    }
}