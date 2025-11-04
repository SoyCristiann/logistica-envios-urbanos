package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.util.Constantes.Rutas;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
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
    private Label userName;

    @FXML
    private Button btnPago;

    @FXML
    private Button btnCotizar;

    @FXML
    private Button btnReportes;

    @FXML
    void abrirPerfil(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.perfilUsuarioPage, event);
    }

    @FXML
    void abrirCotizarEnvio(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.cotizadorEnvioPage, event);
    }

    @FXML
    void abrirCrearEnvio(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.crearEnvioPage, event);
    }

    @FXML
    void abrirMisEnvios(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.misEnviosPage, event);
    }

    @FXML
    void abrirPagos(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.pagoEnvioPage, event);
    }

    @FXML
    void abrirRastreo(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.rastreoEnvioPage, event);
    }

    @FXML
    void abrirServicios(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.serviciosAdicionalesPage, event);
    }

    @FXML
    void abrirHistorial(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.historialEnviosPage, event);
    }

    @FXML
    void abrirReportes(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Rutas.reportesPage, event);
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        //Pendiente definir la logica de cierre de sesión
    }

    SesionManagerSingleton sesionManager= SesionManagerSingleton.getInstance();
    @FXML
    void initialize() {
        VentanaUtil.setUsuarioLogueado(userName);
    }



}
