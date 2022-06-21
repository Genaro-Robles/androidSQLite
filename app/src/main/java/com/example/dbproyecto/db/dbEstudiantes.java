package com.example.dbproyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dbproyecto.entidades.Estudiantes;

import java.util.ArrayList;

public class dbEstudiantes extends DbHelper{

    Context context;

    public dbEstudiantes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEstudiante(String nombre, String curso1, String curso2, String curso3, String cursoOpcional, int estado){
        long id = 0;
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);
            registro.put("Curso1",curso1);
            registro.put("Curso2",curso2);
            registro.put("Curso3",curso3);
            registro.put("CursoOpcional",cursoOpcional);
            registro.put("estado",estado);

            id = db.insert(TABLE_ESTUDIANTE,null,registro);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Estudiantes> mostrarEstudiantes() {
        DbHelper admin = new DbHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        ArrayList<Estudiantes> listaEstudiantes = new ArrayList<>();
        Estudiantes estudiantes = null;
        Cursor cursorEstudiantes = null;

        cursorEstudiantes = db.rawQuery("select * from estudiante", null);
        if (cursorEstudiantes.moveToFirst()) {
            do {
                estudiantes = new Estudiantes();
                estudiantes.setCodigo(cursorEstudiantes.getInt(0));
                estudiantes.setNombre(cursorEstudiantes.getString(1));
                estudiantes.setCurso1(cursorEstudiantes.getString(2));
                estudiantes.setCurso2(cursorEstudiantes.getString(3));
                estudiantes.setCurso3(cursorEstudiantes.getString(4));
                estudiantes.setCursoOpcional(cursorEstudiantes.getString(5));
                estudiantes.setEstado(cursorEstudiantes.getInt(6));
                listaEstudiantes.add(estudiantes);
            } while (cursorEstudiantes.moveToNext());
        }
        cursorEstudiantes.close();

        return listaEstudiantes;
    }

        public Estudiantes verEstudiante(int id){
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            Estudiantes estudiantes = null;
            Cursor cursorEstudiantes = null;

            cursorEstudiantes = db.rawQuery("select * from "+ TABLE_ESTUDIANTE + " WHERE codigo = " + id + " LIMIT 1" ,null);
            if(cursorEstudiantes.moveToFirst()){
                    estudiantes = new Estudiantes();
                    estudiantes.setCodigo(cursorEstudiantes.getInt(0));
                    estudiantes.setNombre(cursorEstudiantes.getString(1));
                    estudiantes.setCurso1(cursorEstudiantes.getString(2));
                    estudiantes.setCurso2(cursorEstudiantes.getString(3));
                    estudiantes.setCurso3(cursorEstudiantes.getString(4));
                    estudiantes.setCursoOpcional(cursorEstudiantes.getString(5));
                    estudiantes.setEstado(cursorEstudiantes.getInt(6));
            }
            cursorEstudiantes.close();

            return estudiantes;
        }

    public boolean editarEstudiante(int codigo, String nombre, String curso1, String curso2, String curso3, String cursoOpcional, int estado){
        boolean correcto = false;

        DbHelper admin = new DbHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+ TABLE_ESTUDIANTE + " SET nombre= '"+nombre+"', Curso1= '"+curso1+"', Curso2= '"+curso2+"', Curso3= '"+curso3+"', CursoOpcional= '"+cursoOpcional+"', estado= '"+estado+"' WHERE codigo= '"+codigo+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
