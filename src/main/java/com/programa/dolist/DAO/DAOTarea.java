package com.programa.dolist.DAO;

import com.programa.dolist.Clases.Conexion;
import com.programa.dolist.Clases.Prioridad;
import com.programa.dolist.Clases.Tarea;
import com.programa.dolist.Interfaces.InterfaceMensaje;
import com.programa.dolist.Interfaces.InterfaceTarea;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTarea extends Conexion implements InterfaceTarea, InterfaceMensaje {


    @Override
    public int agregar(Tarea tarea) {

        String sql = "INSERT INTO TAREA (prioridad,descripcion,estado,fecha) VALUES(?,?,?,?)";
        try (PreparedStatement pstm = abrir().prepareStatement(sql);) {


            pstm.setInt(1, tarea.getPrioridad().getId_prioridad());
            pstm.setString(2, tarea.getDescripcion());
            pstm.setBoolean(3, tarea.getEstado());
            pstm.setString(4, tarea.getFecha());

            return pstm.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("ERROR DAO TAREA AGREGAR: " + ex.getMessage());

        }

        return -1;
    }

    public ArrayList<Tarea> cargarTareasDB() {
        String sql = "SELECT * FROM TAREA ORDER BY PRIORIDAD DESC";
        ArrayList<Tarea> listaTareas = new ArrayList<>();

        try (PreparedStatement pstm = abrir().prepareStatement(sql);
        ) {


            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                Prioridad prioridad = new Prioridad();

                tarea.setId_tarea(rs.getInt("id_tarea"));

                prioridad.setId_prioridad(rs.getInt("prioridad"));
                tarea.setPrioridad(prioridad);

                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getBoolean("estado"));
                tarea.setFecha(rs.getString("fecha"));

                listaTareas.add(tarea);
            }


            return listaTareas;

        } catch (SQLException ex) {

            System.out.println("ERROR CARGAR TAREAS DB: " + ex.getMessage());
        }

        return null;
    }


    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM TAREA WHERE id_tarea=?";
        try (PreparedStatement pstm = abrir().prepareStatement(sql);) {
            pstm.setInt(1, id);

            return pstm.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("ERROR METODO ELIMINAR TAREA: " + ex.getMessage());
        }

        return -1;
    }

    @Override
    public void mensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setGraphic(null);
        alert.show();

    }
}
