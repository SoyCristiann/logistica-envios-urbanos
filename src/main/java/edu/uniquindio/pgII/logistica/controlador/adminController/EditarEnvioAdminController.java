package edu.uniquindio.pgII.logistica.controlador.adminController;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioAdminDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IRepartidorService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.EnvioAdminMapper;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.RepartidorMapper;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.EnvioBuilder;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.setTextLabel;

public class EditarEnvioAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Formulario
    @FXML private TextField inputOrigenEnvio;
    @FXML private TextField inputPesoEnvio;
    @FXML private TextField inputFechaCreacionEnvio;
    @FXML private TextField inputAnchoEnvio;
    @FXML private TextField inputCostoEnvio;
    @FXML private TextField inputDestinoEnvio;
    @FXML private TextField inputLargoEnvio;
    @FXML private TextField inputAltoEnvio;
    @FXML private ComboBox<RepartidorDTO> comboRepartidorEnvio;
    @FXML private TextField inputUsuarioSolicitanteEnvio;
    @FXML private ComboBox<EstadoEnvio> comboEstadoEnvio;
    @FXML private TextField inputIdEnvio;
    @FXML private DatePicker calendarFechaEntregaEnvio;

    //Tabla
    @FXML private TableView<?> tblServiciosEnvio;
    @FXML private TableColumn<?, ?> colIdServicio;
    @FXML private TableColumn<?, ?> colNombreServicio;
    @FXML private TableColumn<?, ?> colDescripcionServicio;
    @FXML private TableColumn<?, ?> colCostoServicio;

    //Botones
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    //Acciones
    @FXML
    void actualizarEnvio(ActionEvent event) {
        actualizarEnvio();
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        closeWindow(event);
    }

    @FXML private Label lblMensaje;

    @FXML
    void initialize() {
        cargarEstados();
        cargarRepartidores();
    }

    private EnviosAdminController controladorPrincipal;
    private EnvioAdminDTO envioOriginal;

    public void cargarDatosEnvio(Envio envio) {
        this.envioOriginal= EnvioAdminMapper.toDTO(envio); //Se guarda el envío original para poderlo usar posteriomente en actualización.

        inputIdEnvio.setText(envio.getIdEnvio());
        inputOrigenEnvio.setText(envio.getOrigen().toString());
        inputDestinoEnvio.setText(envio.getDestino().toString());
        inputPesoEnvio.setText(String.valueOf(envio.getPeso()));
        inputAltoEnvio.setText(String.valueOf(envio.getAlto()));
        inputLargoEnvio.setText(String.valueOf(envio.getLargo()));
        inputAnchoEnvio.setText(String.valueOf(envio.getAncho()));
        inputCostoEnvio.setText(String.valueOf(envio.getCosto()));
        comboEstadoEnvio.getSelectionModel().select(envio.getEstado());
        inputFechaCreacionEnvio.setText(String.valueOf(envio.getFechaCreacion()));
        calendarFechaEntregaEnvio.setValue(envio.getFechaEntrega());
        inputUsuarioSolicitanteEnvio.setText(envio.getUsuario().getNombreCompleto());
        comboRepartidorEnvio.getSelectionModel().select(RepartidorMapper.toDTO(envio.getRepartidor()));

    }

    IRepartidorService repartidorService = AdministradorSingleton.getInstance().getRepartidorService();
    IEnvioService envioService = AdministradorSingleton.getInstance().getEnvioService();

    public void setPadreController(EnviosAdminController controller) {
        this.controladorPrincipal = controller;
    }

    private void cargarRepartidores(){
        List<RepartidorDTO> repartidores = repartidorService.getRepartidores();
        comboRepartidorEnvio.setItems(FXCollections.observableArrayList(repartidores));
    }

    private void cargarEstados(){
        EstadoEnvio[] estados= EstadoEnvio.values();
        comboEstadoEnvio.setItems(FXCollections.observableArrayList(estados));
    }

    private void actualizarEnvio(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);

        EnvioAdminDTO envio = new EnvioAdminDTO();
        envio.setIdEnvio(inputIdEnvio.getText());
        envio.setOrigen(envioOriginal.getOrigen());
        envio.setDestino(envioOriginal.getDestino());
        envio.setPeso(Double.parseDouble(inputPesoEnvio.getText()));
        envio.setAlto(Double.parseDouble(inputAltoEnvio.getText()));
        envio.setLargo(Double.parseDouble(inputLargoEnvio.getText()));
        envio.setAncho(Double.parseDouble(inputAnchoEnvio.getText()));
        //Costo se calcula al llamar al servicio para actualizar
        envio.setEstado(comboEstadoEnvio.getSelectionModel().getSelectedItem());
        envio.setFechaCreacion(LocalDate.parse(inputFechaCreacionEnvio.getText()));
        envio.setFechaEntrega(calendarFechaEntregaEnvio.getValue());
        envio.setNombreUsuario(inputUsuarioSolicitanteEnvio.getText());
        envio.setRepartidor(comboRepartidorEnvio.getSelectionModel().getSelectedItem());

        System.out.println("Repartidor en DTO: " + envio.getRepartidor().getNombre());


        if(!validarCampos()){
            return;
        }

        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Está seguro que desea modificar el envío con los datos ingresados?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(envioService.actualizarEnvioAdmin(envio)!=null){
                alertInfo.setTitle("Información");
                alertInfo.setAlertType(Alert.AlertType.INFORMATION);
                alertInfo.setHeaderText("El envío se ha actualizado correctamente.");
                alertInfo.showAndWait();
                lblMensaje.setText("");
                lblMensaje.setVisible(false);
                if (controladorPrincipal != null) {
                    controladorPrincipal.actualizarTblEnvios();
                }
            }else  {
                alertInfo.setTitle("Error");
                alertInfo.setAlertType(Alert.AlertType.ERROR);
                alertInfo.setHeaderText("Se ha presentado un error al actualizar el envío.");
                alertInfo.showAndWait();
                lblMensaje.setText("");
                lblMensaje.setVisible(false);
                if (controladorPrincipal != null) {
                    controladorPrincipal.actualizarTblEnvios();
                }
            }
        }
    }

    private void closeWindow(ActionEvent event) {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);

        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Está seguro que desea cerrar la ventana?. Los cambios no guardados se perderán.");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    private boolean validarCampos(){
        String peso= inputPesoEnvio.getText().trim();
        String alto= inputAltoEnvio.getText().trim();
        String largo= inputLargoEnvio.getText().trim();
        String ancho= inputAnchoEnvio.getText().trim();
        EstadoEnvio estado= comboEstadoEnvio.getSelectionModel().getSelectedItem();
        LocalDate fechaentrega= calendarFechaEntregaEnvio.getValue();
        RepartidorDTO repartidor= comboRepartidorEnvio.getSelectionModel().getSelectedItem();


        if(peso.isEmpty() || alto.isEmpty() || largo.isEmpty() || ancho.isEmpty() || estado == null || fechaentrega == null || repartidor == null){
            setTextLabel(lblMensaje, "Todos los campos del formulario son obligatorios.");
            lblMensaje.setVisible(true);
            return false;
        }

        if (fechaentrega.isBefore(LocalDate.now())) {
            setTextLabel(lblMensaje, "La fecha de entrega no puede ser anterior a la fecha actual.");
            lblMensaje.setVisible(true);
            return false;
        }
        if(!peso.matches("\\d+(\\.\\d+)?") || !alto.matches("\\d+(\\.\\d+)?") || !largo.matches("\\d+(\\.\\d+)?") || !ancho.matches("\\d+(\\.\\d+)?")) {
            setTextLabel(lblMensaje, "Los campos peso, alto, largo y ancho solo pueden contener números enteros o décimales separados por punto.");
            lblMensaje.setVisible(true);
            return false;
        }
        return true;
    }
}