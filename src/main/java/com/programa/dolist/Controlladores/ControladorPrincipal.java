package com.programa.dolist.Controlladores;

import com.programa.dolist.Clases.Prioridad;
import com.programa.dolist.Clases.Tarea;
import com.programa.dolist.DAO.DAOTarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {


    @FXML
    public ChoiceBox listadoPrioridad;


    @FXML
    public VBox vboxPrincipal;


    @FXML
    public Button botonAgregar;

    @FXML
    public TextArea texareaTarea;


    DAOTarea daoTarea = new DAOTarea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        texareaTarea.textProperty().addListener((obs, textoAntiguo, textoNuevo) -> {

            texareaTarea.setText(textoNuevo.toUpperCase());
            if (textoNuevo.length() > 80) {
                texareaTarea.setText(textoNuevo.substring(0, 80));
            }

        });


        try {
            cargarPrioridades();
            listadoTareas();

            botonAgregar.setOnAction(e -> {

                try {

                    agregarTarea();

                } catch (Exception ex) {

                    System.out.println("ERROR AL AGREGAR TAREA: " + ex.getMessage());

                }
            });

        } catch (Exception ex) {

            System.out.println("ERROR INITIALIZE PRINCIPAL" + ex.getStackTrace());

        }
    }


    public void listadoTareas() throws Exception {
        System.out.println("ENTRO");
        vboxPrincipal.getChildren().clear();
        ArrayList<Tarea> lista = daoTarea.cargarTareasDB();

        for (Tarea m : lista) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistaDoList/VistaTarea.fxml"));
            Pane pane = loader.load();
            ControladorTarea contTarea = loader.getController();

            contTarea.setDatosTareas(m);
            cambiarColorPrioridad(m.getPrioridad().getId_prioridad(), contTarea.textoPrioridad);

            contTarea.setControladorPrincipal(this);
            vboxPrincipal.getChildren().add(pane);
        }


    }

    public void cambiarColorPrioridad(int prioridad, Label texto) {

        String idEstiloTexto = prioridad == 1 ? "textoPrioridadBaja" : prioridad == 2 ? "textoPrioridadMedia" : "textoPrioridadAlta";
        texto.setId(idEstiloTexto);

    }

    public void agregarTarea() throws Exception {

        Tarea tarea = new Tarea();

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fecha = LocalDateTime.now();

        Prioridad prioridad = (Prioridad) listadoPrioridad.getValue();

        tarea.setDescripcion(texareaTarea.getText());
        tarea.setPrioridad(prioridad);
        tarea.setFecha(fecha.format(formatoHora));

        if (validarCampos()) {
            if (daoTarea.agregar(tarea) != -1) {

                limpiarCampos();
                daoTarea.mensaje("NUEVA TAREA GENERADA");
                listadoTareas();
            }
        }else
        {
            daoTarea.mensaje("TIENE QUE ESCRIBIR UNA TAREA Y ASIGNAR UNA PRIORIDAD");
        }

    }

    public boolean validarCampos() {
        return texareaTarea.getLength() > 0 && !listadoPrioridad.getSelectionModel().isEmpty();
    }


    public void cargarPrioridades() {
        ArrayList<Prioridad> prioridades = new ArrayList();

        prioridades.add(new Prioridad(1, "BAJA"));
        prioridades.add(new Prioridad(2, "MEDIA"));
        prioridades.add(new Prioridad(3, "ALTA"));


        ObservableList lista = FXCollections.observableList(prioridades);
        listadoPrioridad.setItems(lista);


    }


    public void limpiarCampos() {
        texareaTarea.clear();
        listadoPrioridad.setValue(null);

    }

}
