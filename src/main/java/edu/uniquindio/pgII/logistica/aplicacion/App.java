package edu.uniquindio.pgII.logistica.aplicacion;

import edu.uniquindio.pgII.logistica.datos.DatosDummy;
import edu.uniquindio.pgII.logistica.modelo.util.Constantes.Rutas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Inicializar datos
        DatosDummy datosDummy = new DatosDummy();


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Rutas.inicioSesionPath));
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
