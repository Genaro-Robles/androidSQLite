package com.example.dbproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbproyecto.db.dbCursos;
import com.example.dbproyecto.db.dbEstudiantes;

public class RegistrarCursos extends AppCompatActivity {

    Button btnRegistrarCurso;
    TextView txtNombreCurso;
    RadioButton rdbCG,rdbCDB,rdbCL,rdbCOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cursos);


        btnRegistrarCurso = findViewById(R.id.btnRegistrarCurso);
        txtNombreCurso = findViewById(R.id.txtNombreCurso);
        rdbCG = findViewById(R.id.rdbCG);
        rdbCDB = findViewById(R.id.rdbCDB);
        rdbCL = findViewById(R.id.rdbCL);
        rdbCOp = findViewById(R.id.rdbCOp);

        rdbCG.setChecked(true);

        btnRegistrarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CursoNombre = txtNombreCurso.getText().toString();

                dbCursos curs = new dbCursos(RegistrarCursos.this);

                long id = -1;

                if(rdbCG.isChecked()){
                    id = curs.insertarCursosGenerales(CursoNombre);
                }else if(rdbCDB.isChecked()){
                    id = curs.insertarCursosDB(CursoNombre);
                }else if(rdbCL.isChecked()){
                    id = curs.insertarCursosL(CursoNombre);
                }else if(rdbCOp.isChecked()){
                    id = curs.insertarCursosOp(CursoNombre);
                }

                if(id>0){
                    txtNombreCurso.setText("");
                    Toast.makeText(RegistrarCursos.this, " REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegistrarCursos.this,"ERROR AL GUARDAR REGITRO",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}