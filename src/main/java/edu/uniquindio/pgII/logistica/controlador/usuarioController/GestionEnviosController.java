package edu.uniquindio.pgII.logistica.controlador.usuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioUsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.ServicioAdicionalDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.ServicioAdicionalMapper;
import edu.uniquindio.pgII.logistica.patrones.Strategy.FirmaRequeridaStrategy;
import edu.uniquindio.pgII.logistica.patrones.Strategy.FragilStrategy;
import edu.uniquindio.pgII.logistica.patrones.Strategy.PrioridadStrategy;
import edu.uniquindio.pgII.logistica.patrones.Strategy.SeguroStrategy;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEnviosController {

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

    @FXML private TextField txtIdRastreo;
    @FXML private Label lblRastreoResultado;

    @FXML private TableView<EnvioUsuarioDTO> tablaEnvios;
    @FXML private TableColumn<EnvioUsuarioDTO, String> colId;
    @FXML private TableColumn<EnvioUsuarioDTO, String> colOrigen;
    @FXML private TableColumn<EnvioUsuarioDTO, String> colDestino;
    @FXML private TableColumn<EnvioUsuarioDTO, String> colEstado;
    @FXML private TableColumn<EnvioUsuarioDTO, String> colCosto;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox<String> cbEstado;

    private final UsuarioFacade facade = new UsuarioFacade();
    private final UsuarioDTO usuarioActual = SesionManagerSingleton.getInstance().getUsuarioActivo();

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
        colOrigen.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrigen().getCiudad()));
        colDestino.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino().getCiudad()));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().name()));
        colCosto.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getCosto())));
    }

    private void inicializarEstados() {
        cbEstado.getItems().add("Todos");
        for (EstadoEnvio e : EstadoEnvio.values()) cbEstado.getItems().add(e.name());
        cbEstado.getSelectionModel().select("Todos");
    }

    private void cargarDirecciones() {
        List<Direccion> direcciones = facade.obtenerDirecciones();
        cbOrigenCotizar.getItems().setAll(direcciones);
        cbDestinoCotizar.getItems().setAll(direcciones);
        cbOrigenCrear.getItems().setAll(direcciones);
        cbDestinoCrear.getItems().setAll(direcciones);
    }

    private List<ServicioAdicional> obtenerServiciosCotizar() {
        List<ServicioAdicional> lista = new ArrayList<>();
        if (chkSeguroCotizar.isSelected()) lista.add(new ServicioAdicional("", "Seguro", new SeguroStrategy()));
        if (chkFragilCotizar.isSelected()) lista.add(new ServicioAdicional("", "Fragil", new FragilStrategy()));
        if (chkFirmaCotizar.isSelected()) lista.add(new ServicioAdicional("", "Firma", new FirmaRequeridaStrategy()));
        if (chkPrioritarioCotizar.isSelected()) lista.add(new ServicioAdicional("", "Prioritario", new PrioridadStrategy()));
        return lista;
    }

    private List<ServicioAdicional> obtenerServiciosCrear() {
        List<ServicioAdicional> lista = new ArrayList<>();
        if (chkSeguroCrear.isSelected()) lista.add(new ServicioAdicional("", "Seguro", new SeguroStrategy()));
        if (chkFragilCrear.isSelected()) lista.add(new ServicioAdicional("", "Fragil", new FragilStrategy()));
        if (chkFirmaCrear.isSelected()) lista.add(new ServicioAdicional("", "Firma", new FirmaRequeridaStrategy()));
        if (chkPrioritarioCrear.isSelected()) lista.add(new ServicioAdicional("", "Prioritario", new PrioridadStrategy()));
        return lista;
    }

    @FXML
    private void cotizarEnvio() {
        try {
            EnvioUsuarioDTO dto = new EnvioUsuarioDTO();
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCotizar.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCotizar.getValue()));
            dto.setPeso(Double.parseDouble(txtPeso.getText()));
            dto.setLargo(Double.parseDouble(txtLargoCot.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCot.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCot.getText()));
            dto.setUsuario(usuarioActual);

            dto.setServiciosAdicionales(
                    obtenerServiciosCotizar()
            );

            double costo = facade.cotizarEnvio(dto);
            lblResultado.setText("Costo estimado: $" + costo);

        } catch (Exception e) {
            lblResultado.setText("Datos inválidos");
        }
    }


    @FXML
    private void limpiarCotizacion() {
        cbOrigenCotizar.setValue(null);
        cbDestinoCotizar.setValue(null);
        txtPeso.clear();
        txtLargoCot.clear();
        txtAnchoCot.clear();
        txtAltoCot.clear();

        chkSeguroCotizar.setSelected(false);
        chkFragilCotizar.setSelected(false);
        chkFirmaCotizar.setSelected(false);
        chkPrioritarioCotizar.setSelected(false);

        lblResultado.setText("");
    }


    @FXML
    private void crearEnvio() {
        try {
            EnvioUsuarioDTO dto = new EnvioUsuarioDTO();
            dto.setUsuario(usuarioActual);
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));
            dto.setLargo(Double.parseDouble(txtLargoCrear.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText()));
            dto.setServiciosAdicionales(obtenerServiciosCrear());

            boolean ok = facade.crearEnvioUsuario(dto);
            mostrar(ok ? "Envío creado" : "No se pudo crear");
            cargarHistorial();

        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }



    @FXML
    private void modificarEnvio() {
        try {
            EnvioUsuarioDTO dto = new EnvioUsuarioDTO();
            dto.setIdEnvio(txtIdEnvio.getText());
            dto.setUsuario(usuarioActual);
            dto.setOrigen(DireccionMapper.toDTO(cbOrigenCrear.getValue()));
            dto.setDestino(DireccionMapper.toDTO(cbDestinoCrear.getValue()));
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));
            dto.setLargo(Double.parseDouble(txtLargoCrear.getText()));
            dto.setAncho(Double.parseDouble(txtAnchoCrear.getText()));
            dto.setAlto(Double.parseDouble(txtAltoCrear.getText()));

            dto.setServiciosAdicionales(obtenerServiciosCrear());

            boolean ok = facade.modificarEnvio(dto);
            mostrar(ok ? "Modificado" : "No se pudo modificar");
            cargarHistorial();

        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }



    @FXML
    private void cancelarEnvio() {
        EnvioUsuarioDTO dto = new EnvioUsuarioDTO();
        dto.setIdEnvio(txtIdEnvio.getText());

        boolean ok = facade.cancelarEnvio(dto);
        mostrar(ok ? "Cancelado" : "No se pudo cancelar");
        cargarHistorial();
    }

    @FXML
    private void filtrarHistorial() {
        tablaEnvios.getItems().setAll(
                facade.obtenerHistorial(
                        usuarioActual,
                        dpFechaInicio.getValue(),
                        dpFechaFin.getValue(),
                        cbEstado.getValue()
                )
        );
    }

    private void cargarHistorial() {
        tablaEnvios.getItems().setAll(
                facade.obtenerHistorial(usuarioActual, null, null, "Todos")
        );
    }

    @FXML
    private void rastrearEnvio() {
        EnvioUsuarioDTO dto = facade.rastrearEnvio(txtIdRastreo.getText());
        lblRastreoResultado.setText(dto != null ? "Estado: " + dto.getEstado().name() : "No encontrado");
    }

    private void escucharSeleccionTabla() {
        tablaEnvios.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                txtIdEnvio.setText(sel.getIdEnvio());
                btnModificar.setDisable(false);
                btnCancelar.setDisable(false);
            }
        });
    }

    private void mostrar(String t) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(t);
        a.showAndWait();
    }

    @FXML
    private void volverAlMenu(javafx.event.ActionEvent e) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, e);
    }

    @FXML
    private void descargarCSV(javafx.event.ActionEvent e) {
        System.out.println("Descargar CSV");
    }

    @FXML
    private void descargarPDF(javafx.event.ActionEvent e) {
        System.out.println("Descargar PDF");
    }

    @FXML
    private void pagarEnvio(javafx.event.ActionEvent e) {
        System.out.println("Pagar Envio");
    }

}
