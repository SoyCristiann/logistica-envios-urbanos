package edu.uniquindio.pgII.logistica.controlador.adminController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoRepartidor;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.cambiarEscena;

public class MainPageAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputEmailUser;

    @FXML
    private TableColumn<RepartidorDTO, String> colIdRep;

    @FXML
    private Button btnCrearRep;

    @FXML
    private TableColumn<UsuarioDTO, String> colEmailUser;

    @FXML
    private Button btnLimpiarFormularioRep;

    @FXML
    private TableColumn<RepartidorDTO, String> colTelefonoRep;

    @FXML
    private TableColumn<UsuarioDTO, String> colRolUser;

    @FXML
    private TextField inputTelefonoUser;

    @FXML
    private ComboBox<RolUsuario> comboRolUser;

    @FXML
    private TableColumn<UsuarioDTO, String> colIdUser;

    @FXML
    private TableColumn<EstadoRepartidor, String> colDispRep;

    @FXML
    private TextField inputNombreRep;

    @FXML
    private Tab tabAdminRepartidores;

    @FXML
    private Button btnCrearUser;

    @FXML
    private Button btnEliminarRep;

    @FXML
    private Button btnActualizarRep;

    @FXML
    private ComboBox<EstadoRepartidor> comboDisRep;

    @FXML
    private Tab tabAdminUsuario;

    @FXML
    private Button btnEliminarUser;

    @FXML
    private TextField inputNombreUser;

    @FXML
    private TableView<UsuarioDTO> tblUsuarios;

    @FXML
    private TextField inputDocumentoRep;

    @FXML
    private TableColumn<UsuarioDTO, String> colNombreUser;

    @FXML
    private TableColumn<RepartidorDTO, String> colCoberturaRep;

    @FXML
    private TableColumn<UsuarioDTO, String> colTelefonoUser;

    @FXML
    private TextField inputIdRep;

    @FXML
    private TextField inputIdUser;

    @FXML
    private Button btnActualizarUser;

    @FXML
    private TableView<RepartidorDTO> tblRepartidores;

    @FXML
    private TextField inputCoberturaRep;

    @FXML
    private TableColumn<RepartidorDTO, String> colNombreRep;

    @FXML
    private Button btnLimpiarFormularioUser;

    @FXML
    private TextField inputTelefonoRep;

    @FXML
    private TableColumn<RepartidorDTO, String> colDocumentoRep;

    @FXML
    void actualizarTblUsuarios(ActionEvent event) {
        actualizarTblUsuarios();
    }

    @FXML
    void actualizarUsuario(ActionEvent event) {
        actualizarUsuario();
    }

    @FXML
    void crearUsuario(ActionEvent event) {

    }

    @FXML
    void limpiarFormularioUsuario(ActionEvent event) {
        limpiarFormularioUsuario();
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        eliminarUsuario();
    }

    @FXML
    void actualizarTblRepartidores(ActionEvent event) {
        actualizarTblRepartidores();

    }

    @FXML
    void actualizarRepartidor(ActionEvent event) {

    }

    @FXML
    void crearRepartidor(ActionEvent event) {

    }

    @FXML
    void limpiarFormularioRepartidor(ActionEvent event) {

    }

    @FXML
    void eliminarRepartidor(ActionEvent event) {

    }

    @FXML
    void initialize() {
        //Administrador de usuarios
        agregarRolesComboBox();
        actualizarTblUsuarios();
        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombreUser.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEmailUser.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefonoUser.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colRolUser.setCellValueFactory(new PropertyValueFactory<>("rolUsuario"));
        tblUsuarios.setOnMouseClicked(mouseEvent -> {
            if (tblUsuarios.getSelectionModel().getSelectedItem() != null) {
                cargarCamposUsuario();
            }
        });


        //Tabla de repartidores
        actualizarTblRepartidores();

    }


    /*
    * Los siguientes métodos son para manipular las diferentes opciones en la pestaña administrador de usuarios
    * */
    IUsuarioService usuarioService= AdministradorSingleton.getInstance().getUsuarioService();
    SesionManagerSingleton sesionManager= SesionManagerSingleton.getInstance();
    Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
    Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);

    private void actualizarTblUsuarios() {
        List<UsuarioDTO> usuariosDTO= new ArrayList<>();
        for (Usuario usuario: usuarioService.getUsuarios()){
            usuariosDTO.add(UsuarioMapper.toDTO(usuario));
        }
        tblUsuarios.getItems().clear();
        tblUsuarios.getItems().addAll(usuariosDTO);
        tblUsuarios.refresh();
    }

    private void limpiarFormularioUsuario(){
        inputIdUser.setText("");
        inputNombreUser.setText("");
        inputEmailUser.setText("");
        inputTelefonoUser.setText("");
        comboRolUser.getSelectionModel().clearSelection();

    }

    private void agregarRolesComboBox(){
        RolUsuario[] roles = RolUsuario.values(); //-> Obtiene los elementos del enum
        comboRolUser.setItems(FXCollections.observableArrayList(roles));

    }

    private void eliminarUsuario(){
        UsuarioDTO usuarioDTO = tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioDTO.getIdUsuario().equals(sesionManager.getUsuarioActivo().getIdUsuario())){
            alertInfo.setTitle("Error.");
            alertInfo.setHeaderText(null);
            alertInfo.setContentText("No es posible elimianr el usuario con el que se ha iniciado sesión.");
            alertInfo.showAndWait();
            alertInfo.setContentText("");
            return;
        }
        confirmAlert.setTitle("Confirmación de borrado de usuario");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Está seguro que desea borrar el usuario: " + usuarioDTO.getIdUsuario() + " - " +  usuarioDTO.getNombreCompleto() + "?");

        Optional<ButtonType> resultado = confirmAlert.showAndWait();
        confirmAlert.setContentText("");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(usuarioService.eliminarUsuario(usuarioDTO)){
                alertInfo.setTitle("Exito.");
                alertInfo.setHeaderText(null);
                alertInfo.setContentText("El usuario se ha eliminado de forma correcta.");
                alertInfo.showAndWait();
                alertInfo.setContentText("");
                actualizarTblUsuarios();
            }else {
                alertInfo.setTitle("Error.");
                alertInfo.setHeaderText(null);
                alertInfo.setContentText("Se ha presentado un error al eliminar el usuario.");
                alertInfo.showAndWait();
                alertInfo.setContentText("");
                actualizarTblUsuarios();
            }
        } else {
            // cuando se presiona el botón cancelar o se cierra el popup, no se hace nada y permanece en la misma ventana.
        }
    }

    private void cargarCamposUsuario() {
        UsuarioDTO usuarioDTO = tblUsuarios.getSelectionModel().getSelectedItem(); //Creo el usuario basado en el seleccionado.
        inputIdUser.setText(usuarioDTO.getIdUsuario());
        inputNombreUser.setText(usuarioDTO.getNombreCompleto());
        inputEmailUser.setText(usuarioDTO.getCorreo());
        inputTelefonoUser.setText(usuarioDTO.getTelefono());
        comboRolUser.getSelectionModel().select(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));
        System.out.println("Id seleccionado: " + usuarioDTO.getIdUsuario());
        System.out.println("Id sesion: " + sesionManager.getUsuarioActivo().getIdUsuario());
        System.out.println("Son iguales: " + usuarioDTO.getIdUsuario().equals(sesionManager.getUsuarioActivo().getIdUsuario()));
        if (usuarioDTO.getIdUsuario().equals(sesionManager.getUsuarioActivo().getIdUsuario())){
            comboRolUser.setDisable(true);
        }else  {
            comboRolUser.setDisable(false);
        }
    }

    private void actualizarUsuario(){
        alertInfo.setHeaderText(null);
        confirmAlert.setHeaderText(null);
        String idUsuarioAntiguo= tblUsuarios.getSelectionModel().getSelectedItem().getIdUsuario(); //Esta línea solo tiene efecto enc aso que se actualice el id del usuario.

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(inputIdUser.getText());
        usuarioDTO.setNombreCompleto(inputNombreUser.getText());
        usuarioDTO.setCorreo(inputEmailUser.getText());
        usuarioDTO.setTelefono(inputTelefonoUser.getText());
        usuarioDTO.setRolUsuario(comboRolUser.getSelectionModel().getSelectedItem());


        confirmAlert.setTitle("Confirmación.");
        confirmAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("¿Está seguro que desea modificar el usuario?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                if(usuarioService.actualizarUsuario(usuarioDTO, idUsuarioAntiguo)!=null){
                    alertInfo.setTitle("Información");
                    alertInfo.setAlertType(Alert.AlertType.INFORMATION);
                    alertInfo.setHeaderText("El usuario se ha actualizado correctamente.");
                    alertInfo.showAndWait();
                    limpiarFormularioUsuario();
                    actualizarTblUsuarios();
                }else  {
                    alertInfo.setTitle("Información.");
                    alertInfo.setAlertType(Alert.AlertType.ERROR);
                    alertInfo.setContentText("Se ha presentado un error al actualizar el usuario.");
                    alertInfo.showAndWait();
                }
            }

    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Los siguientes métodos son para manipular las diferentes opciones en la pestaña administrador de repartidores
     * */

    private void actualizarTblRepartidores() {
        tblRepartidores.getItems().clear();
        //tblRepartidores.getItems().addAll(administrador.getRepartidoresService().getRepartidores);
        tblRepartidores.refresh();
    }
}
