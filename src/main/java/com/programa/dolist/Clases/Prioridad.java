package com.programa.dolist.Clases;

public class Prioridad {

    private int  id_prioridad;
    private String nombre;


    public Prioridad(){}

    public Prioridad(int id_prioridad, String nombre){
        this.id_prioridad=id_prioridad;
        this.nombre=nombre;

    }

    public int getId_prioridad() {
        return id_prioridad;
    }

    public void setId_prioridad(int id_prioridad) {
        this.id_prioridad = id_prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }

}
