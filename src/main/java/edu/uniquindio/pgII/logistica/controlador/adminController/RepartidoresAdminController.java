package edu.uniquindio.pgII.logistica.controlador.adminController;

import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IRepartidorService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.setTextLabel;

public class RepartidoresAdminController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Tabla y columnas de la tabla repartidores

    @FXML
    private TableView<RepartidorDTO> tblRepartidores;
    @FXML
    private TableColumn<RepartidorDTO, String> colIdRep;
    @FXML
    private TableColumn<RepartidorDTO, String> colDocumentoRep;
    @FXML
    private TableColumn<RepartidorDTO, String> colNombreRep;
    @FXML
    private TableColumn<RepartidorDTO, String> colTelefonoRep;
    @FXML
    private TableColumn<DisponibilidadRepartidor, String> colDispRep;
    @FXML
    private TableColumn<RepartidorDTO, String> colCoberturaRep;

    //Formulario
    @FXML
    private TextField inputIdRep;
    @FXML
    private TextField inputDocumentoRep;
    @FXML
    private TextField inputNombreRep;
    @FXML
    private TextField inputTelefonoRep;
    @FXML
    private ComboBox<DisponibilidadRepartidor> comboDisRep;
    @FXML
    private TextField inputCoberturaRep;

    //Botones
    @FXML
    private Button btnActualizarRep;
    @FXML
    private Button btnLimpiarFormularioRep;
    @FXML
    private Button btnEliminarRep;

    @FXML
    private Label lblMensaje;

    //Métodos - acciones
    /*@FXML
    void actualizarTblRepartidores(ActionEvent event) {
        actualizarTblRepartidores();
    }*/

    @FXML
    void actualizarRepartidor(ActionEvent event) {
        actualizarRepartidor();
    }

    @FXML
    void crearRepartidor(ActionEvent event) {
        crearRepartidor();
    }

    @FXML
    void limpiarFormularioRepartidor(ActionEvent event) {
        limpiarFormularioRepartidor();
    }

    @FXML
    void eliminarRepartidor(ActionEvent event) {
        eliminarRepartidor();
    }

    @FXML
    void initialize(){
        agregarDisponibilidadesComboBox();
        actualizarTblRepartidores();

        colIdRep.setCellValueFactory(new PropertyValueFactory<>("idRepartidor"));
        colNombreRep.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDocumentoRep.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colTelefonoRep.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDispRep.setCellValueFactory(new PropertyValueFactory<>("estadoDisponibilidad"));
        colCoberturaRep.setCellValueFactory(new PropertyValueFactory<>("zonaCobertura"));

        tblRepartidores.setOnMouseClicked(mouseEvent -> {
            if (tblRepartidores.getSelectionModel().getSelectedItem() != null) {
                cargarCamposRepartidores();
            }
        });
    }

    IRepartidorService repartidorService= AdministradorSingleton.getInstance().getRepartidorService();

    //Métodos para el CRUD de repartidores

    /*
     *crearRepartidor()
     * Este metodo crea un repartidor a partir de los datos ingresados. Válida los campos, y de ser correctos permite la creación y/o registro
     * a través del servicio.
     */
    public void crearRepartidor(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);
        RepartidorDTO repartidorDTO = new RepartidorDTO();
        repartidorDTO.setIdRepartidor(inputIdRep.getText());
        repartidorDTO.setNombre(inputNombreRep.getText());
        repartidorDTO.setDocumento(inputDocumentoRep.getText());
        repartidorDTO.setTelefono(inputTelefonoRep.getText());
        repartidorDTO.setEstadoDisponibilidad(comboDisRep.getSelectionModel().getSelectedItem());
        repartidorDTO.setZonaCobertura(inputCoberturaRep.getText());

        if(!validarCampos()){
            return;
        }

        confirmAlert.setTitle("Confirmación");
        confirmAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("¿Está seguro que desea crear el repartidor con los datos ingresados?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if(resultado.isPresent() && resultado.get() == ButtonType.OK){
            RepartidorDTO esRegistrado= repartidorService.registrarRepartidor(repartidorDTO);
            System.out.println("Es registrado: " + esRegistrado );
            if(esRegistrado != null){
                alertInfo.setTitle("Información");
                alertInfo.setAlertType(Alert.AlertType.INFORMATION);
                alertInfo.setHeaderText("El repartidor se ha registrado correctamente.");
                alertInfo.showAndWait();
                limpiarFormularioRepartidor();
                actualizarTblRepartidores();
            }else {
                alertInfo.setTitle("Error");
                alertInfo.setAlertType(Alert.AlertType.ERROR);
                alertInfo.setHeaderText("Error al crear el repartidor.");
                alertInfo.showAndWait();
                limpiarFormularioRepartidor();
                actualizarTblRepartidores();
            }
        }
    }


    /**
     * Este metodo identifica un repartido seleccionado de la tabla y lo eliminina de la base de datos.
     * **/
    public void eliminarRepartidor(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);
        RepartidorDTO repartidorDTO = tblRepartidores.getSelectionModel().getSelectedItem();
        confirmAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("¿Está seguro que desea borrar el repartidor: " + repartidorDTO.getIdRepartidor() + " - " +  repartidorDTO.getNombre() + "?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(repartidorService.eliminarRepartidor(repartidorDTO)){
                alertInfo.setTitle("Exito");
                alertInfo.setHeaderText("El repartidor se ha eliminado de forma correcta.");
                alertInfo.showAndWait();
                limpiarFormularioRepartidor();
                actualizarTblRepartidores();
            }else {
                alertInfo.setTitle("Error");
                alertInfo.setHeaderText("Se ha presentado un error al eliminar el usuario.");
                alertInfo.showAndWait();
                limpiarFormularioRepartidor();
                actualizarTblRepartidores();
            }
        } else {
            // cuando se presiona el botón cancelar o se cierra el popup, no se hace nada y permanece en la misma ventana.
        }
        confirmAlert.setTitle("");
        alertInfo.setContentText("");
    }

    /**
     * actualizarUsuario()
     * Este método actualiza un usuario seleccionado de la tabla.
     **/

    public void actualizarRepartidor(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);
        String idAntiguo= tblRepartidores.getSelectionModel().getSelectedItem().getIdRepartidor(); //Esta línea solo tiene efecto en caso que se actualice el id del repartidor.

        RepartidorDTO repartidorDTO = new RepartidorDTO();
        repartidorDTO.setIdRepartidor(inputIdRep.getText());
        repartidorDTO.setNombre(inputNombreRep.getText());
        repartidorDTO.setDocumento(inputDocumentoRep.getText());
        repartidorDTO.setTelefono(inputTelefonoRep.getText());
        repartidorDTO.setEstadoDisponibilidad(comboDisRep.getSelectionModel().getSelectedItem());
        repartidorDTO.setZonaCobertura(inputCoberturaRep.getText());

        if(!validarCampos()){
            return;
        }

        confirmAlert.setTitle("Confirmación");
        confirmAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("¿Está seguro que desea modificar el repartidor con los datos ingresados?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(repartidorService.actualizarRepartidor(repartidorDTO, idAntiguo)!=null){
                alertInfo.setTitle("Información");
                alertInfo.setAlertType(Alert.AlertType.INFORMATION);
                alertInfo.setHeaderText("El repartidor se ha actualizado correctamente.");
                alertInfo.showAndWait();
                limpiarFormularioRepartidor();
                actualizarTblRepartidores();
            }else  {
                alertInfo.setTitle("Error");
                alertInfo.setAlertType(Alert.AlertType.ERROR);
                alertInfo.setHeaderText("Se ha presentado un error al actualizar el repartidor.");
                alertInfo.showAndWait();
            }
        }
    }

    /**
     * Este método hace un refresh de la tabla usuarios. No recibe parámetros, solo debe ser llamado para refrescar.
     * **/
    private void actualizarTblRepartidores() {
        List<RepartidorDTO> repartidoresDTO= new ArrayList<>();
        for (RepartidorDTO repartidorDTO: repartidorService.getRepartidores()){
            repartidoresDTO.add(repartidorDTO);
        }
        tblRepartidores.getItems().clear();
        tblRepartidores.getItems().addAll(repartidoresDTO);
        tblRepartidores.refresh();
    }

    /**
     * Este método espera un elemento Repartidor seleccionado de la tabla y automáticamente diligencia los campos en el formulario para actualizar.
     * Funciona solo al seleccionar un repartidor de la tabla.
     * */
    private void cargarCamposRepartidores() {
        RepartidorDTO repartidorDTO = tblRepartidores.getSelectionModel().getSelectedItem(); //Creo el repartidor basado en el seleccionado.
        inputIdRep.setText(repartidorDTO.getIdRepartidor());
        inputNombreRep.setText(repartidorDTO.getNombre());
        inputDocumentoRep.setText(repartidorDTO.getDocumento());
        inputTelefonoRep.setText(repartidorDTO.getTelefono());
        comboDisRep.getSelectionModel().select(repartidorDTO.getEstadoDisponibilidad());
        inputCoberturaRep.setText(repartidorDTO.getZonaCobertura());
    }

    /**
     * Este método se inicializa con la clase y agrega las opciones del Enum DisponibilidadUsuario al combobox "Disponibilidad".
     * */
    private void agregarDisponibilidadesComboBox(){
        DisponibilidadRepartidor[] disponibilidades = DisponibilidadRepartidor.values(); //-> Obtiene los elementos del enum
        comboDisRep.setItems(FXCollections.observableArrayList(disponibilidades));
    }

    /**
     * Este métdodo verifica que la información de los camposo sea correcta y acorde.
     * */
    private boolean validarCampos(){
        String nombre= inputNombreRep.getText();
        String documento= inputDocumentoRep.getText();
        String telefono= inputTelefonoRep.getText();
        DisponibilidadRepartidor disponibilidad= comboDisRep.getSelectionModel().getSelectedItem();
        String cobertura= inputCoberturaRep.getText();

        if(nombre.isEmpty() || documento.isEmpty() || telefono.isEmpty() || disponibilidad == null || cobertura == null){
            setTextLabel(lblMensaje, "Todos los campos del formulario son obligatorios.");
            lblMensaje.setVisible(true);
            return false;
        }

        if(telefono.length()!=10 || !telefono.matches("\\d+")){
            setTextLabel(lblMensaje, "El teléfono debe contener 10 digitos númericos.");
            lblMensaje.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Este método limpia los datos del formulario repartidores. Está enlazado al botón "Limpiar formulario" y funciona como una acción.
     * **/
    private void limpiarFormularioRepartidor(){
        inputIdRep.setText("");
        inputNombreRep.setText("");
        inputDocumentoRep.setText("");
        inputTelefonoRep.setText("");
        comboDisRep.getSelectionModel().clearSelection();
        inputCoberturaRep.setText("");
        lblMensaje.setVisible(false);
        setTextLabel(lblMensaje, "");
    }
}
