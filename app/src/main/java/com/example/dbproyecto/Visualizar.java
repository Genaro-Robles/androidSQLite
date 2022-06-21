package com.example.dbproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.dbproyecto.entidades.Estudiantes;

import java.util.ArrayList;
import java.util.List;

public class Visualizar extends AppCompatActivity {

    Button btnGuardar;
    EditText txtNyA;
    Spinner cboCurso1, cboCurso2, cboCurso3, cboCursoOpcional;
    CheckBox chkOpcional;
    TextView txtOpcional;
    RadioButton rdbActivo, rdbSuspendido;
    boolean correcto = false;

    Estudiantes estudiantes;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        dbCursos dbCursos = new dbCursos(this);

        //INSTANCIAR CURSO 1

        cboCurso1 = (Spinner)findViewById(R.id.cboCurso1_1);

        List<Cursos> listaCursosGenerales = llenarCursosGenerales();
        ArrayAdapter<Cursos> adapterC1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosGenerales);
        cboCurso1.setAdapter(adapterC1);

        //INSTANCIAR CURSO 2

        cboCurso2 = (Spinner)findViewById(R.id.cboCurso2_1);

        List<Cursos> listaCursosDB = llenarCursosDB();
        ArrayAdapter<Cursos> adapterC2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosDB);
        cboCurso2.setAdapter(adapterC2);


        //INSTANCIAR CURSO 3

        cboCurso3 = (Spinner)findViewById(R.id.cboCurso3_1);

        List<Cursos> listaCursosLenguaje = llenarCursosLenguaje();
        ArrayAdapter<Cursos> adapterC3 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosLenguaje);
        cboCurso3.setAdapter(adapterC3);

        //INSTANCIAR CURSO OPCIONAL

        cboCursoOpcional = (Spinner)findViewById(R.id.cboCursoOpcional_1);

        List<Cursos> listaCursosOpcional = llenarCursosOpcional();
        ArrayAdapter<Cursos> adapterCOpcional = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCursosOpcional);
        cboCursoOpcional.setAdapter(adapterCOpcional);

        chkOpcional = findViewById(R.id.chkOpcional_1);
        txtOpcional = findViewById(R.id.txtOpcional_1);
        txtNyA = findViewById(R.id.txtNyA1);
        rdbActivo = findViewById(R.id.rdbActivo_1);
        rdbSuspendido = findViewById(R.id.rdbSuspendido_1);
        btnGuardar = findViewById(R.id.btnGuardar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

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

        dbEstudiantes dbestudiantes = new dbEstudiantes(Visualizar.this);
        estudiantes = dbestudiantes.verEstudiante(id);



        if(estudiantes != null){
            txtNyA.setText(estudiantes.getNombre());
            int idCG = -1;
            int idCDB = -1;
            int idCL = -1;
            int idCOp = -1;

            for(int i = 0; i<listaCursosGenerales.size(); i++){
             if((listaCursosGenerales.get(i)+"").equals(estudiantes.getCurso1())){
                 idCG = i;
                 break;
             }
            }

            for(int i = 0; i<listaCursosDB.size(); i++){
                if((listaCursosDB.get(i)+"").equals(estudiantes.getCurso2())){
                    idCDB = i;
                    break;
                }
            }

            for(int i = 0; i<listaCursosLenguaje.size(); i++){
                if((listaCursosLenguaje.get(i)+"").equals(estudiantes.getCurso3())){
                    idCL = i;
                    break;
                }
            }

            for(int i = 0; i<listaCursosOpcional.size(); i++){
                if((listaCursosOpcional.get(i)+"").equals(estudiantes.getCursoOpcional())){
                    idCOp = i;
                    break;
                }
            }

            cboCurso1.setSelection(adapterC1.getPosition(listaCursosGenerales.get(idCG)));
            cboCurso2.setSelection(adapterC2.getPosition(listaCursosDB.get(idCDB)));
            cboCurso3.setSelection(adapterC3.getPosition(listaCursosLenguaje.get(idCL)));
            if(estudiantes.getEstado() == 1){
                rdbActivo.setChecked(true);
            }else if(estudiantes.getEstado() == 0){
                rdbSuspendido.setChecked(true);
            }

            if(estudiantes.getCursoOpcional() == null || estudiantes.getCursoOpcional().equals("")){
                txtOpcional.setVisibility(View.INVISIBLE);
                cboCursoOpcional.setVisibility(View.INVISIBLE);
                chkOpcional.setChecked(false);
            }else{
                txtOpcional.setVisibility(View.VISIBLE);
                cboCursoOpcional.setVisibility(View.VISIBLE);
                chkOpcional.setChecked(true);
                cboCursoOpcional.setSelection(adapterCOpcional.getPosition(listaCursosOpcional.get(idCOp)));

            }

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNyA.getText().toString();
                String Curso1 = cboCurso1.getSelectedItem().toString();
                String Curso2 = cboCurso2.getSelectedItem().toString();
                String Curso3 = cboCurso3.getSelectedItem().toString();
                String CursoOpcional;
                Integer estado = null;

                if(chkOpcional.isChecked()){
                    CursoOpcional = cboCursoOpcional.getSelectedItem().toString();
                }else{
                    CursoOpcional = "";
                }
                if(rdbActivo.isChecked()){
                    estado = 1;
                }else if(rdbSuspendido.isChecked()){
                    estado = 0;
                }

                correcto = dbestudiantes.editarEstudiante(id,nombre,Curso1,Curso2,Curso3,CursoOpcional,estado);
                if(correcto){
                    Toast.makeText(Visualizar.this,"REGISTRO MODIFICADO CORRECTAMENTE",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Visualizar.this,"ERROR AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public static int BuscarCurso(String nome, List<Cursos> lista) {
        int resultado = -1;

        for (Cursos curso : lista) {
            if (curso.getNombre().equals(nome)){
                //resultado = curso;
                break;
            }
        }
        return resultado;
    }

    @SuppressLint("Range")
    private List<Cursos> llenarCursosGenerales(){
        List<Cursos> listaCursos = new ArrayList<>();
        dbCursos dbcursos = new dbCursos(Visualizar.this);
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
        dbCursos dbcursos = new dbCursos(Visualizar.this);
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
        dbCursos dbcursos = new dbCursos(Visualizar.this);
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
        dbCursos dbcursos = new dbCursos(Visualizar.this);
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