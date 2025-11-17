package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEnviosController {

    @FXML private ComboBox<Direccion> cbOrigenCotizar;
    @FXML private ComboBox<Direccion> cbDestinoCotizar;
    @FXML private TextField txtPeso;
    // Mantengo txtDimensiones por compatibilidad (no lo borré)
    @FXML private TextField txtDimensiones;
    @FXML private Label lblResultado;

    @FXML private CheckBox chkSeguroCotizar;
    @FXML private CheckBox chkFragilCotizar;
    @FXML private CheckBox chkFirmaCotizar;
    @FXML private CheckBox chkPrioritarioCotizar;

    // --- NUEVOS campos para COTIZAR (tipo double en la UI -> parseo en controlador) ---
    @FXML private TextField txtAncho;
    @FXML private TextField txtLargo;
    @FXML private TextField txtAlto;

    @FXML private ComboBox<Direccion> cbOrigenCrear;
    @FXML private ComboBox<Direccion> cbDestinoCrear;
    @FXML private TextField txtPesoCrear;

    @FXML private CheckBox chkSeguroCrear;
    @FXML private CheckBox chkFragilCrear;
    @FXML private CheckBox chkFirmaCrear;
    @FXML private CheckBox chkPrioritarioCrear;

    @FXML private TextField txtAnchoCrear;
    @FXML private TextField txtLargoCrear;
    @FXML private TextField txtAltoCrear;

    @FXML private TextField txtIdRastreo;
    @FXML private Label lblRastreoResultado;

    @FXML private TableView<EnvioDTO> tablaEnvios;
    @FXML private TableColumn<Envio, String> colId;
    @FXML private TableColumn<Envio, String> colOrigen;
    @FXML private TableColumn<Envio, String> colDestino;
    @FXML private TableColumn<Envio, String> colEstado;
    @FXML private TableColumn<Envio, Double> colCosto;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox<String> cbEstado;

    @FXML private Button btnModificar;
    @FXML private Button btnCancelar;

    private final UsuarioFacade facade = new UsuarioFacade();

    // Inicializar vista
    @FXML
    private void initialize() {
        inicializarColumnas();
        inicializarEstados();
        cargarDirecciones();
        cargarHistorial();
        btnModificar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    // Inicializar columnas de la tabla
    private void inicializarColumnas() {
        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdEnvio()));
        colOrigen.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrigen().getDireccionCompleta()));
        colDestino.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino().getDireccionCompleta()));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().name()));
        colCosto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getCosto()).asObject());
    }

    // Inicializar combo de estados
    private void inicializarEstados() {
        cbEstado.getItems().add("Todos");
        for (EstadoEnvio e : EstadoEnvio.values()) cbEstado.getItems().add(e.name());
        cbEstado.getSelectionModel().select("Todos");
    }

    // Cargar direcciones del usuario
    private void cargarDirecciones() {
        List<Direccion> direcciones = facade.obtenerDirecciones();
        cbOrigenCotizar.getItems().setAll(direcciones);
        cbDestinoCotizar.getItems().setAll(direcciones);
        cbOrigenCrear.getItems().setAll(direcciones);
        cbDestinoCrear.getItems().setAll(direcciones);
    }

    // Cotizar envío
    @FXML
    private void cotizarEnvio() { // Falta la implementacion de la calculacion de los costos
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCotizar.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCotizar.getValue()));

            dto.setPeso(Double.parseDouble(txtPeso.getText().trim()));

            dto.setAncho(Double.parseDouble(txtAncho.getText().trim()));
            dto.setLargo(Double.parseDouble(txtLargo.getText().trim()));
            dto.setAlto(Double.parseDouble(txtAlto.getText().trim()));

            double costo = facade.cotizarEnvio(dto);
            lblResultado.setText("Costo estimado: $" + costo);
        } catch (Exception e) {
            lblResultado.setText("Datos inválidos");
        }
    }

    // limpiar formulario de cotización
    @FXML
    private void limpiarCotizacion() {
        cbOrigenCotizar.setValue(null);
        cbDestinoCotizar.setValue(null);
        txtPeso.clear();
        txtDimensiones.clear();

        if (txtAncho != null) txtAncho.clear();
        if (txtLargo != null) txtLargo.clear();
        if (txtAlto != null) txtAlto.clear();

        chkSeguroCotizar.setSelected(false);
        chkFragilCotizar.setSelected(false);
        chkFirmaCotizar.setSelected(false);
        chkPrioritarioCotizar.setSelected(false);
        lblResultado.setText("");
    }

    // Crear envío
    @FXML
    private void crearEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            // uso DireccionMapper para mantener DTO consistente
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));

            dto.setPeso(Double.parseDouble(txtPesoCrear.getText().trim()));

            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText().trim()));
            dto.setLargo(Double.parseDouble(txtLargoCrear.getText().trim()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText().trim()));


            boolean ok = facade.crearEnvio(dto);
            mostrar(ok ? "Envío creado" : "No se pudo crear");
            cargarHistorial();
        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }

    // Modificar envío
    @FXML
    private void modificarEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            // si en tu flujo necesitas id, puedes setearlo aquí: dto.setIdEnvio(txtIdEnvio.getText());
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText().trim()));

            // agrego dimensiones en la modificación también
            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText().trim()));
            dto.setLargo(Double.parseDouble(txtLargoCrear.getText().trim()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText().trim()));

            boolean ok = facade.modificarEnvio(dto);
            mostrar(ok ? "Modificado" : "No se pudo modificar");
            cargarHistorial();
        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }

    // Cancelar envío
    @FXML
    private void cancelarEnvio() {
        EnvioDTO dto = new EnvioDTO();

        boolean ok = facade.cancelarEnvio(dto);
        mostrar(ok ? "Cancelado" : "No se pudo cancelar");
        cargarHistorial();
    }

    // Rastrear
    @FXML
    private void rastrearEnvio() {
        EnvioDTO dto = facade.rastrearEnvio(txtIdRastreo.getText());
        if (dto != null) {
            lblRastreoResultado.setText("Estado: " + dto.getEstado().name());
        } else {
            lblRastreoResultado.setText("No encontrado");
        }
    }

    // Filtrar historial
    @FXML
    private void filtrarHistorial() {
        String estado = cbEstado.getValue();
        LocalDate ini = dpFechaInicio.getValue();
        LocalDate fin = dpFechaFin.getValue();
        tablaEnvios.getItems().setAll(facade.obtenerHistorial(null, ini, fin, estado));

    }

    // Cargar historial completo
    private void cargarHistorial() {
        tablaEnvios.getItems().setAll(facade.obtenerHistorial(null, null, null, "Todos"));
    }

    //descargar CSV (PENDIENTE)
    @FXML
    private void descargarCSV() {
        System.out.print("Generar CSV");
    }

    // descargar PDF (PENDIENTE)
    @FXML
    private void descargarPDF() {
        System.out.print("Generar PDF");
    }

    // Alertas
    private void mostrar(String t) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(t);
        a.showAndWait();
    }

    // Volver al menú
    @FXML
    private void volverAlMenu(javafx.event.ActionEvent e) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, e);
    }
}
