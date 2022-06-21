package com.example.dbproyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.dbproyecto.entidades.Cursos;


import java.util.ArrayList;

public class dbCursos extends DbHelper{

    Context context;

    public dbCursos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCursosGenerales(String nombre){
        long id = 0;
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);

            id = db.insert(TABLE_CURSOS_GENERALES,null,registro);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }
    public long insertarCursosDB(String nombre){
        long id = 0;
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);

            id = db.insert(TABLE_CURSOS_DB,null,registro);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }
    public long insertarCursosL(String nombre){
        long id = 0;
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);

            id = db.insert(TABLE_CURSOS_LENGUAJE,null,registro);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public long insertarCursosOp(String nombre){
        long id = 0;
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("nombre",nombre);

            id = db.insert(TABLE_CURSOS_OPCIONALES,null,registro);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public Cursor CursosGenerales() {
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor cursorCursos = db.rawQuery("select * from "+TABLE_CURSOS_GENERALES, null);
            if (cursorCursos.moveToFirst()) {
                return cursorCursos;
            }else{
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }

    public Cursor CursosDB() {
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor cursorCursos = db.rawQuery("select * from "+TABLE_CURSOS_DB, null);
            if (cursorCursos.moveToFirst()) {
                return cursorCursos;
            }else{
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }

    public Cursor CursosLenguaje() {
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor cursorCursos = db.rawQuery("select * from "+TABLE_CURSOS_LENGUAJE, null);
            if (cursorCursos.moveToFirst()) {
                return cursorCursos;
            }else{
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }

    public Cursor CursosOpcional() {
        try {
            DbHelper admin = new DbHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            Cursor cursorCursos = db.rawQuery("select * from "+TABLE_CURSOS_OPCIONALES, null);
            if (cursorCursos.moveToFirst()) {
                return cursorCursos;
            }else{
                return null;
            }
        }catch (Exception ex){
            return null;
        }
    }
}
