package com.example.dbproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbproyecto.db.dbCursos;
import com.example.dbproyecto.db.dbEstudiantes;
import com.example.dbproyecto.entidades.Cursos;

import java.util.ArrayList;
import java.util.List;

public class RegistrarEstudiantes extends AppCompatActivity {

    Button btnRegistrar;
    EditText txtNyA;
    Spinner cboCurso1, cboCurso2, cboCurso3, cboCursoOpcional;
    CheckBox chkOpcional;
    TextView txtOpcional;
    RadioButton rdbActivo, rdbSuspendido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_estudiantes);

        dbCursos dbCursos = new dbCursos(this);

        //INSTANCIAR CURSO 1

        cboCurso1 = (Spinner)findViewById(R.id.cboCurso1);

        List<Cursos> listaCursosGenerales = llenarCursosGenerales();
        ArrayAdapter<Cursos> adapterC1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosGenerales);
        cboCurso1.setAdapter(adapterC1);

        //INSTANCIAR CURSO 2

        cboCurso2 = (Spinner)findViewById(R.id.cboCurso2);

        List<Cursos> listaCursosDB = llenarCursosDB();
        ArrayAdapter<Cursos> adapterC2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosDB);
        cboCurso2.setAdapter(adapterC2);


        //INSTANCIAR CURSO 3

        cboCurso3 = (Spinner)findViewById(R.id.cboCurso3);

        List<Cursos> listaCursosLenguaje = llenarCursosLenguaje();
        ArrayAdapter<Cursos> adapterC3 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosLenguaje);
        cboCurso3.setAdapter(adapterC3);

        //INSTANCIAR CURSO OPCIONAL

        cboCursoOpcional = (Spinner)findViewById(R.id.cboCursoOpcional);

        List<Cursos> listaCursosOpcional = llenarCursosOpcional();
        ArrayAdapter<Cursos> adapterCOpcional = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosOpcional);
        cboCursoOpcional.setAdapter(adapterCOpcional);


        chkOpcional = findViewById(R.id.chkOpcional);
        txtOpcional = findViewById(R.id.txtOpcional);
        txtNyA = findViewById(R.id.txtNyA);
        rdbActivo = findViewById(R.id.rdbActivo);
        rdbSuspendido = findViewById(R.id.rdbSuspendido);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        txtOpcional.setVisibility(View.INVISIBLE);
        cboCursoOpcional.setVisibility(View.INVISIBLE);
        rdbActivo.setChecked(true);

        chkOpcional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkOpcional.isChecked()){
                    txtOpcional.setVisibility(View.VISIBLE);
                    cboCursoOpcional.setVisibility(View.VISIBLE);
                }else {
                    txtOpcional.setVisibility(View.INVISIBLE);
                    cboCursoOpcional.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNyA.getText().toString();
                String Curso1 = cboCurso1.getSelectedItem().toString();
                String Curso2 = cboCurso2.getSelectedItem().toString();
                String Curso3 = cboCurso3.getSelectedItem().toString();
                String CursoOpcional = null;
                Integer estado = null;

                if(chkOpcional.isChecked()){
                    CursoOpcional = cboCursoOpcional.getSelectedItem().toString();
                }
                if(rdbActivo.isChecked()){
                    estado = 1;
                }else if(rdbSuspendido.isChecked()){
                    estado = 0;
                }


                dbEstudiantes est = new dbEstudiantes(RegistrarEstudiantes.this);
                long id = est.insertarEstudiante(nombre,Curso1,Curso2,Curso3,CursoOpcional,estado);

                if(id>0){
                    Toast.makeText(RegistrarEstudiantes.this, " REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    Limpiar();
                }else {
                    Toast.makeText(RegistrarEstudiantes.this,"ERROR AL GUARDAR REGITRO",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void Limpiar(){
        txtNyA.setText("");
        chkOpcional.setSelected(false);
        rdbActivo.setChecked(true);
    }


    @SuppressLint("Range")
    private List<Cursos> llenarCursosGenerales(){
        List<Cursos> listaCursos = new ArrayList<>();
        dbCursos dbcursos = new dbCursos(RegistrarEstudiantes.this);
        Cursor cursor = dbcursos.CursosGenerales();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    Cursos cursos = new Cursos();
                    cursos.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
                    cursos.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    listaCursos.add(cursos);
                }while (cursor.moveToNext());
            }
        }
        dbcursos.close();
        return listaCursos;
    }
    @SuppressLint("Range")
    private List<Cursos> llenarCursosDB(){
        List<Cursos> listaCursos = new ArrayList<>();
        dbCursos dbcursos = new dbCursos(RegistrarEstudiantes.this);
        Cursor cursor = dbcursos.CursosDB();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    Cursos cursos = new Cursos();
                    cursos.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
                    cursos.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    listaCursos.add(cursos);
                }while (cursor.moveToNext());
            }
        }
        dbcursos.close();
        return listaCursos;
    }

    @SuppressLint("Range")
    private List<Cursos> llenarCursosLenguaje(){
        List<Cursos> listaCursos = new ArrayList<>();
        dbCursos dbcursos = new dbCursos(RegistrarEstudiantes.this);
        Cursor cursor = dbcursos.CursosLenguaje();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    Cursos cursos = new Cursos();
                    cursos.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
                    cursos.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    listaCursos.add(cursos);
                }while (cursor.moveToNext());
            }
        }
        dbcursos.close();
        return listaCursos;
    }

    @SuppressLint("Range")
    private List<Cursos> llenarCursosOpcional(){
        List<Cursos> listaCursos = new ArrayList<>();
        dbCursos dbcursos = new dbCursos(RegistrarEstudiantes.this);
        Cursor cursor = dbcursos.CursosOpcional();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    Cursos cursos = new Cursos();
                    cursos.setCodigo(cursor.getInt(cursor.getColumnIndex("id")));
                    cursos.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    listaCursos.add(cursos);
                }while (cursor.moveToNext());
            }
        }
        dbcursos.close();
        return listaCursos;
    }
}