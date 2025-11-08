package edu.uniquindio.pgII.logistica.controlador;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.MetodoPago;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.UsuarioMapper;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistroUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnIrInicioSesion;

    @FXML
    private Button btnRegistrarseUsuario;

    @FXML
    private CheckBox checkBoxMetodoPagoUsuario;

    @FXML
    private ComboBox<MetodoPago> comboMetodoPagoUsuario; //Pasar la lista de metodos de pago

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputPass;

    @FXML private Label lblMensaje;

    @FXML
    private TextField inputTelefono;

    @FXML
    private TextField inputDescripcionDir;

    @FXML
    private TextField inputCodPostalDir;

    @FXML
    private TextField idDireccion;

    @FXML
    private TextField inputBarrioDir;

    @FXML
    private TextField inputCiudadDir;

    @FXML
    private TextField inputCalleDir;

    @FXML
    private TextField inputAliasDir;

    @FXML
    private TextField inputNumeroDir;

    @FXML
    void registrarUsuario(ActionEvent event) {
        registroUsuario(event);
    }

    @FXML
    void irInicioSesion(ActionEvent event) {
        irInicioSesionPage(event);
    }

    @FXML
    void initialize() {
        agregarOpcionesComboBox();
        habilitarMetodoPago(null);


        // Se utiliza BooleanBilding para realizar el monitoreo a los campos.
        //El primer bloque es la evaluación. Si al menos un campo está vacío retorna true que se guarda en camposObligatoriosVacios.
        //Si todos los campos están llenos, devuelve false y lo guarda en camposObligatoriosVacios.
        //La sección de textProperty, se monitorean las propiedades (campos), ante cualqiuier cambio, notifica que se deben revisar los campos de nuevo (punto anterior)

        BooleanBinding camposObligatoriosVacios = Bindings.createBooleanBinding(()
                        ->
                        //Sección lamba de regla lógica
                        inputId.getText().trim().isEmpty() ||
                        inputNombre.getText().trim().isEmpty() ||
                        inputPass.getText().trim().isEmpty() ||
                        inputEmail.getText().trim().isEmpty() ||
                        inputTelefono.getText().trim().isEmpty() ||
                        inputCalleDir.getText().trim().isEmpty() ||
                        inputNumeroDir.getText().trim().isEmpty() ||
                        inputBarrioDir.getText().trim().isEmpty() ||
                        inputCiudadDir.getText().trim().isEmpty() ||
                        //inputCodPostalDir.getText().trim().isEmpty() ||
                        //inputDescripcionDir.getText().trim().isEmpty() ||
                        inputAliasDir.getText().trim().isEmpty(),

                        // Sección de propiedades
                        inputId.textProperty(),
                        inputNombre.textProperty(),
                        inputPass.textProperty(),
                        inputEmail.textProperty(),
                        inputTelefono.textProperty(),
                        inputCalleDir.textProperty(),
                        inputNumeroDir.textProperty(),
                        inputBarrioDir.textProperty(),
                        inputCiudadDir.textProperty(),
                        //inputCodPostalDir.textProperty(),
                        //inputDescripcionDir.textProperty(),
                        inputAliasDir.textProperty()
        );
        //Sección final que sincroniza el valor de la propiedad 'disabled' con lo obtenido en camposObligatoriosVacios
        btnRegistrarseUsuario.disableProperty().bind(camposObligatoriosVacios);


    }

    void agregarOpcionesComboBox(){
        MetodoPago[] metodos = MetodoPago.values(); //-> Obtiene los elementos del enum
        comboMetodoPagoUsuario.setItems(FXCollections.observableArrayList(metodos));
    }

    @FXML
    void registroUsuario(ActionEvent event){
        if (!validarCampos()){
            return;
        }
        //Datos de usuario
        String id= inputId.getText();
        String pass= inputPass.getText();
        String nombre= inputNombre.getText();
        String email= inputEmail.getText();
        String telefono= inputTelefono.getText();

        //Direccion
        String idDir= idDireccion.getText();
        String calleDir= inputCalleDir.getText();
        String numeroDir= inputNumeroDir.getText();
        String barrioDir= inputBarrioDir.getText();
        String ciudadDir= inputCiudadDir.getText();
        String codigoPostalDir= inputCodPostalDir.getText();
        String descripcionDir= inputDescripcionDir.getText();
        String aliasDir= inputAliasDir.getText();

        UsuarioDTO usuarioNuevo= new UsuarioDTO(id, nombre, email, telefono, pass, RolUsuario.USUARIO);
        DireccionDTO direccionNuevo= new DireccionDTO(idDir, calleDir, numeroDir, barrioDir, ciudadDir, codigoPostalDir, descripcionDir, aliasDir);
        usuarioNuevo.getDireccionesFrecuentesDTO().add(direccionNuevo);

        if(checkBoxMetodoPagoUsuario.isSelected()){
            MetodoPago metodoPago= comboMetodoPagoUsuario.getSelectionModel().getSelectedItem();
            usuarioNuevo.getMetodosPago().add(metodoPago);
        }

        AdministradorSingleton administrador= AdministradorSingleton.getInstance();
        IUsuarioService usuarioService= administrador.getUsuarioService();

        if(usuarioService.registrarUsuario(UsuarioMapper.toEntity(usuarioNuevo)) != null){
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Usuario Exitoso.");
            alert.setHeaderText(null);
            alert.setContentText("Usuario registrado correctamente, puede iniciar sesión.");

            alert.showAndWait(); //Muestra la alerta y espera.
            limpiarCampos(); //Una vez se acepta el mensaje, se limpian los campos.

            for (Usuario u: usuarioService.getUsuarios()){
                System.out.println(u.getIdUsuario());
                System.out.println(u.getPassword());
                System.out.println(u.getNombreCompleto());
                System.out.println(u.getCorreo());
                System.out.println(u.getTelefono());
                System.out.println(u.getDireccionesFrecuentes());
            }

            Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("¿Volver a inincio de sesión?");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("¿Desea volver al menú principal?");

            Optional<ButtonType> resultado = confirmAlert.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                cambiarEscena(getClass(), Rutas.inicioSesionPage, event);
            } else {
                // cuando se presiona el botón cancelar o se cierra el popup, no se hace nada y la ventana de RegistroUsuario permanece abierta.
            }
        }


    }

    @FXML
    void habilitarMetodoPago(ActionEvent event){
        boolean isSelected= checkBoxMetodoPagoUsuario.isSelected(); //Trae el valor actual marcado o desmarcado.
        comboMetodoPagoUsuario.setDisable(!isSelected); //Dependiendo el valor actual, aplica el valor contrario.

        //Si el usuario desmarca el checkbox, debe limpiarse cualquier selección previa
        if(!isSelected){
            comboMetodoPagoUsuario.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void irInicioSesionPage(ActionEvent event){
        cambiarEscena(getClass(), Rutas.inicioSesionPage, event);
    }

    private boolean validarCampos(){

        String id= inputId.getText();
        String pass= inputPass.getText();
        String nombre= inputNombre.getText();
        String email= inputEmail.getText();
        String telefono= inputTelefono.getText();

        if(checkBoxMetodoPagoUsuario.isSelected()){
            MetodoPago metodoPago= comboMetodoPagoUsuario.getSelectionModel().getSelectedItem();
        }

        if(pass.length()<8){
            setTextLabel(lblMensaje, "La contraseña debe tener mínimo 8 caracteres");
            return false;
        }
        if (!email.contains("@") || !email.contains(".")){
            setTextLabel(lblMensaje, "El campo correo no tiene la estructura correcta.");
            return false;
        }
        if(telefono.length()!=10 || !telefono.matches("\\d+")){
            setTextLabel(lblMensaje, "El teléfono debe contener 10 digitos númericos.");
            return false;
        }
        return true;
    }

    //Limpiar campos del formulario
    private void limpiarCampos() {
        inputId.setText("");
        inputPass.setText("");
        inputNombre.setText("");
        inputEmail.setText("");
        inputTelefono.setText("");

        // Campos de Dirección
        idDireccion.setText("");
        inputCalleDir.setText("");
        inputNumeroDir.setText("");
        inputBarrioDir.setText("");
        inputCiudadDir.setText("");
        inputCodPostalDir.setText("");
        inputDescripcionDir.setText("");
        inputAliasDir.setText("");

        // Limpiar CheckBox y ComboBox (Metodo de Pago)
        checkBoxMetodoPagoUsuario.setSelected(false);
        habilitarMetodoPago(null);

        // Limpiar mensaje de feedback
        setTextLabel(lblMensaje, "");
    }

}
