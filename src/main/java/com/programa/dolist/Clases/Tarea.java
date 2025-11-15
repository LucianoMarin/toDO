package com.programa.dolist.Clases;

public class Tarea {

    private int id_tarea;
    private String descripcion;
    private Prioridad prioridad;
    private boolean estado;
    private String fecha;


    public Tarea() {
    }

    public Tarea(int id_tarea, Prioridad prioridad, String descripcion, boolean estado, String fecha) {
        this.id_tarea = id_tarea;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {

        return String.format("""
                ID: %s
                DESCRIPCION: %s
                PRIORIDAD: %d
                ESTADO: %d
                FECHA: %s
                """, id_tarea, descripcion, prioridad, estado, fecha);

    }


}
