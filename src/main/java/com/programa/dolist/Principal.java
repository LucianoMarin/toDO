package com.programa.dolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/vistaDoList/VistaDoList.fxml"));
            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception ex) {

            System.out.println("ERROR VISTA PRINCIPAL: " + ex.getCause());

        }
    }


    public static void main(String[] args) {

        launch(args);
    }


}
