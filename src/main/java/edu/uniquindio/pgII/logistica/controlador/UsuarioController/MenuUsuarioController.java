package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.aplicacion.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuUsuarioController {

    private void cambiarVentana(String ruta, ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(ruta));
        Scene nuevaScene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(nuevaScene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML private void abrirPerfil(ActionEvent e) throws Exception { cambiarVentana("/fxml/PerfilUsuarioPage.fxml", e); }

    @FXML private void abrirCotizarEnvio(ActionEvent e) throws Exception { cambiarVentana("/fxml/CotizarEnvioPage.fxml", e); }

    @FXML private void abrirCrearEnvio(ActionEvent e) throws Exception { cambiarVentana("/fxml/CrearEnvioPage.fxml", e); }

    @FXML private void abrirMisEnvios(ActionEvent e) throws Exception { cambiarVentana("/fxml/MisEnviosPage.fxml", e); }

    @FXML private void abrirPagos(ActionEvent e) throws Exception { cambiarVentana("/fxml/PagoEnvioPage.fxml", e); }

    @FXML private void abrirRastreo(ActionEvent e) throws Exception { cambiarVentana("/fxml/RastreoEnvioPage.fxml", e); }

    @FXML private void abrirServicios(ActionEvent e) throws Exception { cambiarVentana("/fxml/ServiciosAdicionalesPage.fxml", e); }

    @FXML private void abrirHistorial(ActionEvent e) throws Exception { cambiarVentana("/fxml/HistorialEnviosPage.fxml", e); }

    @FXML private void abrirReportes(ActionEvent e) throws Exception { cambiarVentana("/fxml/ReportesPage.fxml", e); }

    @FXML private void cerrarSesion(ActionEvent e) throws Exception { cambiarVentana("/fxml/InicioSesionPage.fxml", e); }
}
