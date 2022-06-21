package com.example.dbproyecto.entidades;

public class Estudiantes {
    private int codigo;
    private String nombre;
    private String Curso1;
    private String Curso2;
    private String Curso3;
    private String CursoOpcional;
    private int estado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso1() {
        return Curso1;
    }

    public void setCurso1(String curso1) {
        Curso1 = curso1;
    }

    public String getCurso2() {
        return Curso2;
    }

    public void setCurso2(String curso2) {
        Curso2 = curso2;
    }

    public String getCurso3() {
        return Curso3;
    }

    public void setCurso3(String curso3) {
        Curso3 = curso3;
    }

    public String getCursoOpcional() {
        return CursoOpcional;
    }

    public void setCursoOpcional(String cursoOpcional) {
        CursoOpcional = cursoOpcional;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
