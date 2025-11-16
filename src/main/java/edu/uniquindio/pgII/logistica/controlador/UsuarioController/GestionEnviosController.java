package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class GestionEnviosController {

    // COTIZAR (entrada rápida)
    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtPeso;
    @FXML private TextField txtDimensiones;

    @FXML private CheckBox chkSeguro;       // cotizar
    @FXML private CheckBox chkFragil;       // cotizar
    @FXML private CheckBox chkFirma;        // cotizar
    @FXML private CheckBox chkPrioritario;  // cotizar

    @FXML private Label lblResultado;
    @FXML private Button btnLimpiarCotizacion;

    // CRUD ENVÍOS (crear / modificar)
    @FXML private TextField txtIdEnvio;
    @FXML private TextField txtOrigenCrear;
    @FXML private TextField txtDestinoCrear;
    @FXML private TextField txtPesoCrear;
    @FXML private TextField txtDimensionesCrear;
    @FXML private TextField txtDescripcion;

    // checkboxes para CREAR / MODIFICAR (separados de los de cotizar)
    @FXML private CheckBox chkSeguroCrear;
    @FXML private CheckBox chkFragilCrear;
    @FXML private CheckBox chkFirmaCrear;
    @FXML private CheckBox chkPrioritarioCrear;

    // RASTREO
    @FXML private TextField txtIdRastreo;
    @FXML private Label lblRastreoResultado;

    // HISTORIAL
    @FXML private TableView<EnvioDTO> tablaEnvios;
    @FXML private TableColumn<EnvioDTO,String> colId;
    @FXML private TableColumn<EnvioDTO,String> colOrigen;
    @FXML private TableColumn<EnvioDTO,String> colDestino;
    @FXML private TableColumn<EnvioDTO,String> colEstado;
    @FXML private TableColumn<EnvioDTO,Double> colCosto;

    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox<String> cbEstado;

    // Botones dinámicos junto a la tabla
    @FXML private Button btnModificar;   // habilitado solo si estado == SOLICITADO
    @FXML private Button btnCancelar;    // habilitado solo si estado == SOLICITADO
    @FXML private Button btnDescargarCSV;
    @FXML private Button btnDescargarPDF;

    private final UsuarioFacade facade = new UsuarioFacade();

    @FXML
    public void initialize() {
        // Column factories
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getIdEnvio()));
        colOrigen.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getOrigen()));
        colDestino.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDestino()));
        colEstado.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue() != null && c.getValue().getEstado() != null ? c.getValue().getEstado().name() : "N/A"
        ));
        colCosto.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getCosto()).asObject());

        // Estados en ComboBox
        cbEstado.getItems().clear();
        cbEstado.getItems().add("Todos");
        for (EstadoEnvio e : EstadoEnvio.values()) {
            cbEstado.getItems().add(e.name());
        }
        cbEstado.getSelectionModel().select("Todos");

        // Botones inicialmente deshabilitados
        if (btnModificar != null) btnModificar.setDisable(true);
        if (btnCancelar != null) btnCancelar.setDisable(true);

        // Listener de selección en la tabla
        tablaEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            seleccionarEnvioTabla(newSel);
        });

        // Cargar listado inicial
        cargarHistorial();
    }

    /* =================== COTIZAR =================== */

    @FXML
    private void cotizarEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setOrigen(txtOrigen.getText());
            dto.setDestino(txtDestino.getText());
            dto.setPeso(Double.parseDouble(txtPeso.getText()));
            dto.setDimensiones(txtDimensiones.getText());

            dto.setSeguro(chkSeguro.isSelected());
            dto.setFragil(chkFragil.isSelected());
            dto.setFirmaRequerida(chkFirma.isSelected());
            dto.setPrioritario(chkPrioritario.isSelected());

            double costo = facade.cotizarEnvio(dto);
            lblResultado.setText("Costo estimado: $" + costo);

        } catch (Exception e) {
            lblResultado.setText("Error en los datos.");
        }
    }

    @FXML
    private void limpiarCotizacion(ActionEvent event) {
        txtOrigen.clear();
        txtDestino.clear();
        txtPeso.clear();
        txtDimensiones.clear();
        chkSeguro.setSelected(false);
        chkFragil.setSelected(false);
        chkFirma.setSelected(false);
        chkPrioritario.setSelected(false);
        lblResultado.setText("");
    }

    /* =================== CREAR =================== */

    @FXML
    private void crearEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();

            dto.setOrigen(txtOrigenCrear.getText());
            dto.setDestino(txtDestinoCrear.getText());
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));
            dto.setDimensiones(txtDimensionesCrear.getText());
            dto.setFechaCreacion(LocalDate.now());
            dto.setEstado(EstadoEnvio.SOLICITADO);

            // Servicios adicionales desde la UI de crear
            dto.setSeguro(chkSeguroCrear.isSelected());
            dto.setFragil(chkFragilCrear.isSelected());
            dto.setFirmaRequerida(chkFirmaCrear.isSelected());
            dto.setPrioritario(chkPrioritarioCrear.isSelected());

            boolean ok = facade.crearEnvio(dto);
            mostrar(ok ? "Envío creado" : "No se pudo crear el envío");

            limpiarCamposCrear();
            cargarHistorial();

        } catch (Exception ex) {
            mostrar("Datos inválidos");
        }
    }

    private void limpiarCamposCrear() {
        txtIdEnvio.clear();
        txtOrigenCrear.clear();
        txtDestinoCrear.clear();
        txtPesoCrear.clear();
        txtDimensionesCrear.clear();
        txtDescripcion.clear();
        chkSeguroCrear.setSelected(false);
        chkFragilCrear.setSelected(false);
        chkFirmaCrear.setSelected(false);
        chkPrioritarioCrear.setSelected(false);
    }

    /* =================== MODIFICAR =================== */

    @FXML
    private void modificarEnvio() {
        try {
            EnvioDTO dto = new EnvioDTO();
            dto.setIdEnvio(txtIdEnvio.getText());
            dto.setOrigen(txtOrigenCrear.getText());
            dto.setDestino(txtDestinoCrear.getText());
            dto.setPeso(Double.parseDouble(txtPesoCrear.getText()));
            dto.setDimensiones(txtDimensionesCrear.getText());

            // Servicios en modificación
            dto.setSeguro(chkSeguroCrear.isSelected());
            dto.setFragil(chkFragilCrear.isSelected());
            dto.setFirmaRequerida(chkFirmaCrear.isSelected());
            dto.setPrioritario(chkPrioritarioCrear.isSelected());

            boolean ok = facade.modificarEnvio(dto);
            mostrar(ok ? "Envío modificado" : "No se pudo modificar");

            cargarHistorial();

        } catch (Exception e) {
            mostrar("Datos inválidos");
        }
    }

    /* =================== CANCELAR =================== */

    @FXML
    private void cancelarEnvio() {
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(txtIdEnvio.getText());

        boolean ok = facade.cancelarEnvio(dto);
        mostrar(ok ? "Envío cancelado" : "No se puede cancelar");

        cargarHistorial();
    }

    /* =================== RASTREAR =================== */

    @FXML
    private void rastrearEnvio() {
        EnvioDTO dto = facade.rastrearEnvio(txtIdRastreo.getText());

        if (dto != null) {
            lblRastreoResultado.setText("Estado: " + (dto.getEstado() != null ? dto.getEstado().name() : "N/A")
                    + " | Destino: " + dto.getDestino());
        } else {
            lblRastreoResultado.setText("No encontrado.");
        }
    }

    /* =================== HISTORIAL =================== */

    @FXML
    private void filtrarHistorial() {
        String estado = cbEstado.getValue();
        if (estado == null) estado = "Todos";
        LocalDate ini = dpFechaInicio.getValue();
        LocalDate fin = dpFechaFin.getValue();

        List<EnvioDTO> lista = facade.obtenerHistorial(null, ini, fin, estado);
        tablaEnvios.getItems().setAll(lista);
    }

    private void cargarHistorial() {
        List<EnvioDTO> lista = facade.obtenerHistorial(null, null, null, "Todos");
        tablaEnvios.getItems().setAll(lista);
    }

    private void mostrar(String texto) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(texto);
        a.showAndWait();
    }

    /* =================== TABLA: selección y acciones dinámicas =================== */

    private void seleccionarEnvioTabla(EnvioDTO seleccionado) {
        if (seleccionado == null) {
            // Desactivar acciones si no hay selección
            if (btnModificar != null) btnModificar.setDisable(true);
            if (btnCancelar != null) btnCancelar.setDisable(true);
            return;
        }

        // Rellenar campos de edición con datos del DTO seleccionado
        txtIdEnvio.setText(seleccionado.getIdEnvio() != null ? seleccionado.getIdEnvio() : "");
        txtOrigenCrear.setText(seleccionado.getOrigen() != null ? seleccionado.getOrigen() : "");
        txtDestinoCrear.setText(seleccionado.getDestino() != null ? seleccionado.getDestino() : "");
        txtPesoCrear.setText(String.valueOf(seleccionado.getPeso()));
        txtDimensionesCrear.setText(seleccionado.getDimensiones() != null ? seleccionado.getDimensiones() : "");
        txtDescripcion.setText(""); // si tienes descripción en DTO, mapearla

        // Servicios: setear checkboxes de la sección crear/modificar
        chkSeguroCrear.setSelected(seleccionado.isSeguro());
        chkFragilCrear.setSelected(seleccionado.isFragil());
        chkFirmaCrear.setSelected(seleccionado.isFirmaRequerida());
        chkPrioritarioCrear.setSelected(seleccionado.isPrioritario());

        // Habilitar o deshabilitar botones según estado
        EstadoEnvio estado = seleccionado.getEstado();
        boolean habilitarAcciones = estado != null && estado == EstadoEnvio.SOLICITADO;

        if (btnModificar != null) btnModificar.setDisable(!habilitarAcciones);
        if (btnCancelar != null) btnCancelar.setDisable(!habilitarAcciones);
    }

    /* =================== AUX: descargar CSV / PDF (stubs seguros) =================== */

    @FXML
    private void descargarCSV(ActionEvent event) {
        // Stub: implementar export real si lo deseas
        mostrar("Funcionalidad Descargar CSV (pendiente implementar export real).");
    }

    @FXML
    private void descargarPDF(ActionEvent event) {
        // Stub: implementar export real si lo deseas
        mostrar("Funcionalidad Descargar PDF (pendiente implementar export real).");
    }

    /* =================== VOLVER =================== */

    @FXML
    private void volverAlMenu(ActionEvent event) {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
        System.out.println("Volver al menú…");
    }
}
