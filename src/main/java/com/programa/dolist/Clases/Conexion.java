package com.programa.dolist.Clases;


import java.sql.*;

public class Conexion {

    private String url = "jdbc:sqlite:dolist.db";

    private Connection cn;

    public Connection abrir() {

        try {

            return cn = DriverManager.getConnection(url);

        } catch (SQLException ex) {

            System.out.println("ERROR METODO CONEXION: " + ex.getMessage());

        }

        return null;

    }



}
