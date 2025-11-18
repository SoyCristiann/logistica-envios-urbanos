package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.ServicioAdicionalDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEnviosController {

    // ===============================
    //  FXML ELEMENTOS
    // ===============================

    @FXML private ComboBox<Direccion> cbOrigenCotizar;
    @FXML private ComboBox<Direccion> cbDestinoCotizar;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAnchoCot;
    @FXML private TextField txtLargoCot;
    @FXML private TextField txtAltoCot;

    @FXML private CheckBox chkSeguroCotizar;
    @FXML private CheckBox chkFragilCotizar;
    @FXML private CheckBox chkFirmaCotizar;
    @FXML private CheckBox chkPrioritarioCotizar;

    @FXML private Label lblResultado;

    // Crear/Modificar/Cancelar
    @FXML private TextField txtIdEnvio;
    @FXML private ComboBox<Direccion> cbOrigenCrear;
    @FXML private ComboBox<Direccion> cbDestinoCrear;
    @FXML private TextField txtPesoCrear;
    @FXML private TextField txtLargoCrear;
    @FXML private TextField txtAnchoCrear;
    @FXML private TextField txtAltoCrear;

    @FXML private CheckBox chkSeguroCrear;
    @FXML private CheckBox chkFragilCrear;
    @FXML private CheckBox chkFirmaCrear;
    @FXML private CheckBox chkPrioritarioCrear;

    @FXML private Button btnModificar;
    @FXML private Button btnCancelar;

    // Rastrear
    @FXML private TextField txtIdRastreo;
    @FXML private Label lblRastreoResultado;

    // Tabla
    @FXML private TableView<EnvioDTO> tablaEnvios;
    @FXML private TableColumn<EnvioDTO, String> colId;
    @FXML private TableColumn<EnvioDTO, String> colOrigen;
    @FXML private TableColumn<EnvioDTO, String> colDestino;
    @FXML private TableColumn<EnvioDTO, String> colEstado;
    @FXML private TableColumn<EnvioDTO, Double> colCosto;

    // Historial
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox<String> cbEstado;

    private final UsuarioFacade facade = new UsuarioFacade();

    // ===============================
    //  INICIALIZACIÓN
    // ===============================
    @FXML
    private void initialize() {
        inicializarColumnas();
        inicializarEstados();
        cargarDirecciones();
        cargarHistorial();

        btnModificar.setDisable(true);
        btnCancelar.setDisable(true);

        escucharSeleccionTabla();
    }

    private void inicializarColumnas() {
        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdEnvio()));
        colOrigen.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getOrigen().getDireccionCompleta()));
        colDestino.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getDestino().getDireccionCompleta()));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().name()));
        colCosto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getCosto()).asObject());
    }

    private void inicializarEstados() {
        cbEstado.getItems().add("Todos");
        for (EstadoEnvio e : EstadoEnvio.values())
            cbEstado.getItems().add(e.name());
        cbEstado.getSelectionModel().select("Todos");
    }

    private void cargarDirecciones() {
        List<Direccion> direcciones = facade.obtenerDirecciones();
        cbOrigenCotizar.getItems().setAll(direcciones);
        cbDestinoCotizar.getItems().setAll(direcciones);
        cbOrigenCrear.getItems().setAll(direcciones);
        cbDestinoCrear.getItems().setAll(direcciones);
    }

    // ===============================
    //  FUNCIONES DE SERVICIOS ADICIONALES
    // ===============================

    private List<ServicioAdicionalDTO> obtenerServiciosCotizar() {
        List<ServicioAdicionalDTO> lista = new ArrayList<>();

        if (chkSeguroCotizar.isSelected()) lista.add(new ServicioAdicionalDTO("Seguro"));
        if (chkFragilCotizar.isSelected()) lista.add(new ServicioAdicionalDTO("Fragil"));
        if (chkFirmaCotizar.isSelected()) lista.add(new ServicioAdicionalDTO("Firma"));
        if (chkPrioritarioCotizar.isSelected()) lista.add(new ServicioAdicionalDTO("Prioritario"));

        return lista;
    }

    private List<ServicioAdicionalDTO> obtenerServiciosCrear() {
        List<ServicioAdicionalDTO> lista = new ArrayList<>();

        if (chkSeguroCrear.isSelected()) lista.add(new ServicioAdicionalDTO("Seguro"));
        if (chkFragilCrear.isSelected()) lista.add(new ServicioAdicionalDTO("Fragil"));
        if (chkFirmaCrear.isSelected()) lista.add(new ServicioAdicionalDTO("Firma"));
        if (chkPrioritarioCrear.isSelected()) lista.add(new ServicioAdicionalDTO("Prioritario"));

        return lista;
    }

    // ===============================
    //  COTIZAR ENVÍO
    // ===============================
    @FXML
    private void cotizarEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCotizar.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCotizar.getValue()));

            dto.setPeso(Double.parseDouble(txtPeso.getText()));
            dto.setLargo(Double.parseDouble(txtLargoCot.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCot.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCot.getText()));

            dto.setServicios(obtenerServiciosCotizar());

            double costo = facade.cotizarEnvio(dto);
            lblResultado.setText("Costo estimado: $" + costo);

        } catch (Exception e) {
            lblResultado.setText("Datos inválidos");
        }
    }

    // ===============================
    //  CREAR ENVÍO
    // ===============================
    @FXML
    private void crearEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));

            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));
            dto.setLargo(Double.parseDouble(txtLargoCrear.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText()));

            dto.setServicios(obtenerServiciosCrear());

            boolean ok = facade.crearEnvio(dto);
            mostrar(ok ? "Envío creado" : "No se pudo crear");
            cargarHistorial();

        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }

    // ===============================
    //  MODIFICAR Y CANCELAR
    // ===============================

    @FXML
    private void modificarEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setIdEnvio(txtIdEnvio.getText());
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));

            dto.setLargo(Double.parseDouble(txtLargoCrear.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText()));

            boolean ok = facade.modificarEnvio(dto);
            mostrar(ok ? "Modificado" : "No se pudo modificar");
            cargarHistorial();

        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }

    @FXML
    private void cancelarEnvio() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(txtIdEnvio.getText());

        boolean ok = facade.cancelarEnvio(dto);
        mostrar(ok ? "Cancelado" : "No se pudo cancelar");
        cargarHistorial();
    }

    // ===============================
    //  HISTORIAL
    // ===============================

    @FXML
    private void filtrarHistorial() {
        String estado = cbEstado.getValue();
        LocalDate ini = dpFechaInicio.getValue();
        LocalDate fin = dpFechaFin.getValue();

        tablaEnvios.getItems().setAll(
                facade.obtenerHistorial(null, ini, fin, estado)
        );
    }

    private void cargarHistorial() {
        tablaEnvios.getItems().setAll(
                facade.obtenerHistorial(null, null, null, "Todos")
        );
    }

    // ===============================
    //  RASTREAR
    // ===============================

    @FXML
    private void rastrearEnvio() {
        EnvioDTO dto = facade.rastrearEnvio(txtIdRastreo.getText());
        lblRastreoResultado.setText(
                dto != null ?
                        "Estado: " + dto.getEstado().name() :
                        "No encontrado"
        );
    }

    // ===============================
    //  SELECCIÓN EN TABLA
    // ===============================

    private void escucharSeleccionTabla() {
        tablaEnvios.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtIdEnvio.setText(sel.getIdEnvio());
                btnModificar.setDisable(false);
                btnCancelar.setDisable(false);
            }
        });
    }

    // ===============================
    //  UTILS
    // ===============================

    private void mostrar(String t) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(t);
        a.showAndWait();
    }

    @FXML
    private void volverAlMenu(javafx.event.ActionEvent e) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, e);
    }
}
