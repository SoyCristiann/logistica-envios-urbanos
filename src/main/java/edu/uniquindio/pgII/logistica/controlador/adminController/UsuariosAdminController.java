package edu.uniquindio.pgII.logistica.controlador.adminController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.setTextLabel;

public class UsuariosAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tabAdminRepartidores;

    @FXML
    private TextField inputEmailUser;


    @FXML
    private Button btnCrearRep;

    @FXML
    private TableColumn<UsuarioDTO, String> colEmailUser;

    @FXML
    private Label lblMensaje;

    @FXML
    private TableColumn<UsuarioDTO, String> colRolUser;

    @FXML
    private TextField inputTelefonoUser;

    @FXML
    private ComboBox<RolUsuario> comboRolUser;

    @FXML
    private TableColumn<UsuarioDTO, String> colIdUser;

    @FXML
    private Button btnCrearUser;

    @FXML
    private Tab tabAdminUsuario;

    @FXML
    private Button btnEliminarUser;

    @FXML
    private TextField inputNombreUser;

    @FXML
    private TableView<UsuarioDTO> tblUsuarios;

    @FXML
    private TableColumn<UsuarioDTO, String> colNombreUser;

    @FXML
    private TableColumn<UsuarioDTO, String> colTelefonoUser;

    @FXML
    private TextField inputIdUser;

    @FXML
    private Button btnActualizarUser;

    @FXML
    private Button btnLimpiarFormularioUser;

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
        crearUsuario();
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
    void initialize() {
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
    }


    /*
    * Los siguientes métodos son para manipular las diferentes opciones en la pestaña administrador de usuarios
    * */
    IUsuarioService usuarioService= AdministradorSingleton.getInstance().getUsuarioService();
    SesionManagerSingleton sesionManager= SesionManagerSingleton.getInstance();

    //CRUD de usuarios

    //Crear usuario
    private void crearUsuario() {
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(inputIdUser.getText());
        usuarioDTO.setNombreCompleto(inputNombreUser.getText());
        usuarioDTO.setCorreo(inputEmailUser.getText());
        usuarioDTO.setTelefono(inputTelefonoUser.getText());
        usuarioDTO.setRolUsuario(comboRolUser.getSelectionModel().getSelectedItem());
        if(!validarCampos()){
            return;
        }
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("¿Está seguro que desea crear el usuario con los datos ingresados?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(usuarioService.registrarUsuario(usuarioDTO)!=null){
                alertInfo.setTitle("Información");
                alertInfo.setAlertType(Alert.AlertType.INFORMATION);
                alertInfo.setHeaderText("El usuario se ha registrado correctamente.");
                alertInfo.showAndWait();
                limpiarFormularioUsuario();
                actualizarTblUsuarios();
            }else  {
                alertInfo.setTitle("Error");
                alertInfo.setAlertType(Alert.AlertType.ERROR);
                alertInfo.setHeaderText("Se ha presentado un error al crear el usuario.");
                alertInfo.showAndWait();
            }
        }
    }



    //Eliminar Usuario
    private void eliminarUsuario(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);

        UsuarioDTO usuarioDTO = tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioDTO.getIdUsuario().equals(sesionManager.getUsuarioActivo().getIdUsuario())){
            alertInfo.setTitle("Error");
            alertInfo.setHeaderText("No es posible elimianr el usuario con el que se ha iniciado sesión.");
            alertInfo.showAndWait();
            return;
        }

        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Está seguro que desea borrar el usuario: " + usuarioDTO.getIdUsuario() + " - " +  usuarioDTO.getNombreCompleto() + "?");
        Optional<ButtonType> resultado = confirmAlert.showAndWait();


        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            if(usuarioService.eliminarUsuario(usuarioDTO)){
                alertInfo.setTitle("Exito");
                alertInfo.setHeaderText("El usuario se ha eliminado de forma correcta.");
                alertInfo.showAndWait();
                limpiarFormularioUsuario();
                actualizarTblUsuarios();
            }else {
                alertInfo.setTitle("Error");
                alertInfo.setHeaderText("Se ha presentado un error al eliminar el usuario.");
                alertInfo.showAndWait();
                limpiarFormularioUsuario();
                actualizarTblUsuarios();
            }
        } else {
            // cuando se presiona el botón cancelar o se cierra el popup, no se hace nada y permanece en la misma ventana.
        }
    }

    //Actualizar Usuario
    private void actualizarUsuario(){
        Alert alertInfo= new Alert(Alert.AlertType.INFORMATION);
        Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);

        String idUsuarioAntiguo= tblUsuarios.getSelectionModel().getSelectedItem().getIdUsuario(); //Esta línea solo tiene efecto enc aso que se actualice el id del usuario.

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(inputIdUser.getText());
        usuarioDTO.setNombreCompleto(inputNombreUser.getText());
        usuarioDTO.setCorreo(inputEmailUser.getText());
        usuarioDTO.setTelefono(inputTelefonoUser.getText());
        usuarioDTO.setRolUsuario(comboRolUser.getSelectionModel().getSelectedItem());

        if(!validarCampos()){
            return;
        }
        confirmAlert.setTitle("Confirmación");
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
                alertInfo.setTitle("Error");
                alertInfo.setAlertType(Alert.AlertType.ERROR);
                alertInfo.setHeaderText("Se ha presentado un error al actualizar el usuario.");
                alertInfo.showAndWait();
                limpiarFormularioUsuario();
                actualizarTblUsuarios();
            }
        }
    }

    //Métodos complementarios para la vista usuarios

    /**
     * Este método hace un refresh de la tabla usuarios. No recibe parámetros, solo debe ser llamado para refrescar.
     * **/
    private void actualizarTblUsuarios() {
        List<UsuarioDTO> usuariosDTO= new ArrayList<>();
        for (UsuarioDTO usuario: usuarioService.getUsuarios()){
            usuariosDTO.add(usuario);
        }
        tblUsuarios.getItems().clear();
        tblUsuarios.getItems().addAll(usuariosDTO);
        tblUsuarios.refresh();
    }

    /**
     * Este método limpia los datos del formulario usuarios. Está enlazado al botón "Limpiar formulario" y funciona como una acción.
     * **/
    private void limpiarFormularioUsuario(){
        inputIdUser.setText("");
        inputNombreUser.setText("");
        inputEmailUser.setText("");
        inputTelefonoUser.setText("");
        comboRolUser.getSelectionModel().clearSelection();
        lblMensaje.setVisible(false);
        setTextLabel(lblMensaje, "");
    }

    /**
     * Este método se inicializa con la clase y agrega las opciones del Enum RolesUsuario al combobox "Rol".
     * */
    private void agregarRolesComboBox(){
        RolUsuario[] roles = RolUsuario.values(); //-> Obtiene los elementos del enum
        comboRolUser.setItems(FXCollections.observableArrayList(roles));
    }

    /**
     * Este método espera un elemento usuario seleccionado de la tabla y automáticamente diligencia los campos en el formulario para actualizar.
     * Funciona solo al seleccioanr un usuario de la tabla.
     * */
    private void cargarCamposUsuario() {
        UsuarioDTO usuarioDTO = tblUsuarios.getSelectionModel().getSelectedItem(); //Creo el usuario basado en el seleccionado.
        inputIdUser.setText(usuarioDTO.getIdUsuario());
        inputNombreUser.setText(usuarioDTO.getNombreCompleto());
        inputEmailUser.setText(usuarioDTO.getCorreo());
        inputTelefonoUser.setText(usuarioDTO.getTelefono());
        comboRolUser.getSelectionModel().select(RolUsuario.valueOf(usuarioDTO.getRolUsuario()));
        if (usuarioDTO.getIdUsuario().equals(sesionManager.getUsuarioActivo().getIdUsuario())){
            comboRolUser.setDisable(true);
        }else  {
            comboRolUser.setDisable(false);
        }
    }


    /**
     * Este método verifica que los campos obligatorios estén diligenciados y que el correo y teléfono tengan la longitud y el formato correcto.
     * */
    private boolean validarCampos(){
        String id= inputIdUser.getText();
        String nombre= inputNombreUser.getText();
        String email= inputEmailUser.getText();
        String telefono= inputTelefonoUser.getText();
        RolUsuario rol= comboRolUser.getSelectionModel().getSelectedItem();

        if(id.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || rol == null){
            setTextLabel(lblMensaje, "Todos los campos del formulario son obligatorios.");
            lblMensaje.setVisible(true);
            return false;
        }

        if (!email.contains("@") || !email.contains(".")){
            setTextLabel(lblMensaje, "El campo correo no tiene la estructura correcta.");
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

    @FXML
    private void cerrarSesion(Event event) {
        Tab tab = (Tab) event.getSource();

        if (tab.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Cierre de Sesión");
            alert.setHeaderText("Está a punto de cerrar su sesión.");
            alert.setContentText("¿Está seguro de que desea cerrar la sesión actual?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                SesionManagerSingleton.getInstance().cerrarSesion();
                Stage currentStage = (Stage) tab.getTabPane().getScene().getWindow();
                currentStage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.inicioSesionPage));
                    Parent root = loader.load();

                    Stage loginStage = new Stage();
                    loginStage.setTitle("Logística Urbana - Login");
                    loginStage.setScene(new Scene(root));
                    loginStage.show();


                } catch (IOException e) {
                    System.err.println("Error al cargar la vista de Login.");
                    e.printStackTrace();
                }
            } else {
                //Si el usuario presiona cancelar, se devuelve al primer tab
                tab.getTabPane().getSelectionModel().select(0);
            }
        }
    }
}
