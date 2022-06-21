package com.example.dbproyecto.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbproyecto.R;
import com.example.dbproyecto.Visualizar;
import com.example.dbproyecto.entidades.Estudiantes;

import java.util.ArrayList;

public class ListaEstudiantesAdapter extends RecyclerView.Adapter<ListaEstudiantesAdapter.EstudiantesViewHolder> {

    ArrayList<Estudiantes> listaEstudiantes;
    public ListaEstudiantesAdapter(ArrayList<Estudiantes> listaEstudiantes){
        this.listaEstudiantes = listaEstudiantes;
    }

    @NonNull
    @Override
    public EstudiantesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, null, false);
        return new EstudiantesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudiantesViewHolder holder, int position) {
        holder.viewNombre.setText(listaEstudiantes.get(position).getNombre());
        holder.viewCurso1.setText(listaEstudiantes.get(position).getCurso1());
        holder.viewCurso2.setText(listaEstudiantes.get(position).getCurso2());
        holder.viewCurso3.setText(listaEstudiantes.get(position).getCurso3());
        holder.viewCursoOpcional.setText(listaEstudiantes.get(position).getCursoOpcional());
        if(listaEstudiantes.get(position).getEstado() == 1){
            holder.viewEstado.setText("Activo");
        }else if(listaEstudiantes.get(position).getEstado() == 0){
            holder.viewEstado.setText("Suspendido");
        }

    }

    @Override
    public int getItemCount() {
        return listaEstudiantes.size();
    }

    public class EstudiantesViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre,viewCurso1,viewCurso2,viewCurso3,viewCursoOpcional,viewEstado;

        public EstudiantesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.ViewNombre);
            viewCurso1 = itemView.findViewById(R.id.ViewCurso1);
            viewCurso2 = itemView.findViewById(R.id.ViewCurso2);
            viewCurso3 = itemView.findViewById(R.id.ViewCurso3);
            viewCursoOpcional = itemView.findViewById(R.id.ViewCursoOpcional);
            viewEstado = itemView.findViewById(R.id.ViewEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Visualizar.class);
                    intent.putExtra("ID",listaEstudiantes.get(getAdapterPosition()).getCodigo());
                    context.startActivity(intent);
                }
            });

        }
    }
}
