package edu.uniquindio.pgII.logistica.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/InicioSesionPage.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/RegistroUsuarioPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //Tamaño mínimo de la vantana
        stage.setMinHeight(800);
        stage.setMinWidth(1200);

        // Tamaño máximo de la ventana
        stage.setMaxHeight(800);
        stage.setMaxWidth(1200);

        stage.setResizable(false); //Evita que se maximize la ventana
        stage.centerOnScreen();

        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }
}
