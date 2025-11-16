package edu.uniquindio.pgII.logistica.aplicacion;

import edu.uniquindio.pgII.logistica.datos.DatosDummy;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Inicializar datos
        DatosDummy datosDummy = new DatosDummy();


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constantes.inicioSesionPage));
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Constantes.administradorMainPage));
        Scene scene = new Scene(fxmlLoader.load());

        //Tamaño mínimo de la vantana
        stage.setMinHeight(800);
        stage.setMinWidth(1200);

        stage.setResizable(false); //Evita que se maximize la ventana
        stage.centerOnScreen();

        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarVentana(String rutaFXML, String titulo) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(rutaFXML));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

}
