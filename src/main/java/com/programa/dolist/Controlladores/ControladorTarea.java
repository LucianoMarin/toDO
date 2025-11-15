package com.programa.dolist.Controlladores;

import com.programa.dolist.Clases.Prioridad;
import com.programa.dolist.Clases.Tarea;
import com.programa.dolist.DAO.DAOTarea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTarea implements Initializable {

    @FXML
    public Label textoTodo;

    @FXML
    public Label textoPrioridad;

    @FXML
    public Button botonEliminar;

    private ControladorPrincipal controladorPrincipal;

    DAOTarea daoTarea = new DAOTarea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        botonEliminar.setOnAction(e -> {
            try {


                if (daoTarea.eliminar((Integer) botonEliminar.getUserData()) != -1) {

                    daoTarea.mensaje("SE ELIMINO LA TAREA CORRECTAMENTE!");
                }

                controladorPrincipal.listadoTareas();

            } catch (Exception ex) {
                System.out.println("ERROR CONTROLADOR TAREA: " + ex.getMessage());

            }
        });

    }


    public void setDatosTareas(Tarea tarea) {
        textoTodo.setText(tarea.getDescripcion());
        textoPrioridad.setText(textoEstado(tarea.getPrioridad().getId_prioridad()));
        botonEliminar.setUserData(tarea.getId_tarea());

    }


    public String textoEstado(int id_prioridad) {

        String estado;
        return estado = id_prioridad == 1 ? "*" : id_prioridad == 2 ? "**" : "***";

    }


    public void setControladorPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }



}
