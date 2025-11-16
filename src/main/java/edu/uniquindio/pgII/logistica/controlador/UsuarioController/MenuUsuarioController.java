package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGestionEnvios;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label lblUsuario;

    private final SesionManagerSingleton sesionManager = SesionManagerSingleton.getInstance();

    // ---------------- EVENTOS ----------------

    @FXML
    void abrirPerfil(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.perfilUsuarioPage, event);
    }

    @FXML
    void abrirGestionEnvios(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.gestionEnviosPage, event);
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.inicioSesionPage, event);
    }

    // ---------------- INICIALIZACIÓN ----------------
    @FXML
    void initialize() {
        VentanaUtil.setUsuarioLogueado(lblUsuario);
    }
}
