package com.example.dbproyecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "Senati.db";
    public static final String TABLE_ESTUDIANTE = "estudiante";
    public static final String TABLE_CURSOS_GENERALES = "cursosGenerales";
    public static final String TABLE_CURSOS_DB = "cursosDB";
    public static final String TABLE_CURSOS_LENGUAJE = "cursosLenguaje";
    public static final String TABLE_CURSOS_OPCIONALES = "cursosOpcionales";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_ESTUDIANTE +"(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "Curso1 TEXT NOT NULL," +
                "Curso2 TEXT NOT NULL," +
                "Curso3 TEXT NOT NULL," +
                "CursoOpcional TEXT," +
                "estado INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_CURSOS_GENERALES +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO "+ TABLE_CURSOS_GENERALES+"(nombre) VALUES" +
                "('Matematica')," +
                "('Fisica')," +
                "('Quimica')," +
                "('Geometria')," +
                "('Trigonometria')");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_CURSOS_DB +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO "+ TABLE_CURSOS_DB+"(nombre) VALUES" +
                "('MySQL')," +
                "('SQLite')," +
                "('SQLServer')," +
                "('Firebase')");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_CURSOS_LENGUAJE +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO "+ TABLE_CURSOS_LENGUAJE+"(nombre) VALUES" +
                "('C++')," +
                "('Python')," +
                "('C#')," +
                "('Java')," +
                "('C')," +
                "('Javascript')," +
                "('PHP')");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_CURSOS_OPCIONALES +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO "+ TABLE_CURSOS_OPCIONALES+"(nombre) VALUES" +
                "('Domotica')," +
                "('Arduino')," +
                "('RasperryPi')," +
                "('Redes')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_ESTUDIANTE);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_CURSOS_GENERALES);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_CURSOS_DB);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_CURSOS_LENGUAJE);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_CURSOS_OPCIONALES);
        onCreate(sqLiteDatabase);
    }
}
