package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRastreo;

    @FXML
    private Button btnServicios;

    @FXML
    private Button btnCrearEnvio;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnHistorial;

    @FXML
    private Button btnMisEnvios;

    @FXML
    private Label lblUsuario;

    @FXML
    private Button btnPago;

    @FXML
    private Button btnCotizar;

    @FXML
    private Button btnReportes;

    private final SesionManagerSingleton sesionManager = SesionManagerSingleton.getInstance();

    // EVENTOS
    @FXML
    void abrirPerfil(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.perfilUsuarioPage, event);
    }

    @FXML
    void abrirCotizarEnvio(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.cotizadorEnvioPage, event);
    }

    @FXML
    void abrirCrearEnvio(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.crearEnvioPage, event);
    }

    @FXML
    void abrirMisEnvios(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.misEnviosPage, event);
    }

    @FXML
    void abrirPagos(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.pagoEnvioPage, event);
    }

    @FXML
    void abrirRastreo(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.rastreoEnvioPage, event);
    }

    @FXML
    void abrirServicios(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.serviciosAdicionalesPage, event);
    }

    @FXML
    void abrirHistorial(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.historialEnviosPage, event);
    }

    @FXML
    void abrirReportes(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.reportesPage, event);
    }


    // ------------------ INICIALIZACIÓN ------------------
    @FXML
    void initialize() {
        VentanaUtil.setUsuarioLogueado(lblUsuario);
    }



    @FXML
    void cerrarSesion(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Cierre de Sesión");
        alert.setHeaderText("Está a punto de cerrar su sesión.");
        alert.setContentText("¿Está seguro de que desea cerrar la sesión actual?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            sesionManager.cerrarSesion();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.inicioSesionPage));
                Parent root = loader.load();

                Stage loginStage = new Stage();
                loginStage.setTitle("Logística Urbana - Iniciar Sesión");
                loginStage.setScene(new Scene(root));
                loginStage.show();

            } catch (IOException e) {
                System.out.println("Error al cargar la vista de Login.");
            }
        }
    }
}
