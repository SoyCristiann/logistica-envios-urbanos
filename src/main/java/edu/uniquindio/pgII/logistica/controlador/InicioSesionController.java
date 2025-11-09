package edu.uniquindio.pgII.logistica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.modelo.dto.InicioSesionDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.*;

public class InicioSesionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistro;

    @FXML
    private TextField userInput;

    @FXML
    private TextField passInput;

    @FXML
    void hacerLogin(ActionEvent event) {
        iniciarSesion(event);
    }

    @FXML private Label lblMensaje;

    @FXML
    void registrarse(ActionEvent event) {
        irARegistro(event);
    }

    @FXML
    void initialize() {
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        //Se obtiene el servicio a través del singleton.
        AdministradorSingleton administrador= AdministradorSingleton.getInstance();
        IUsuarioService usuarioService= administrador.getUsuarioService();


        //Primero se verifica que los campos no sean nulos, de faltar alguno interrumpe el flujo.
        if (!validarCampos()){
            return;
        }

        //Si los campos se validan de forma correcta, extrae las credenciales de inicio de sesión.
        String correo= userInput.getText().trim();
        String pass= passInput.getText().trim();

        //Creación del DTO con los datos ingresados.
        InicioSesionDTO inicioSesionDTO= new InicioSesionDTO();
        inicioSesionDTO.setUser(correo);
        inicioSesionDTO.setPass(pass);

        //Se llama al servicio de login utilizando los parametros indicados anteriormente. Retornará un usuario.
        Usuario usuarioLogueado= usuarioService.iniciarSesion(inicioSesionDTO.getUser(),  inicioSesionDTO.getPass());

        //Validación del loguin exitoso. Si retorna un usuario null, el inicio de sesión es fallido, si retorna un Usuario, el inicio de sesión es exitoso.

        if(usuarioLogueado != null){ // -> Login exitoso.
            // Almacena el usuario en el sesión manager.
            SesionManagerSingleton.getInstance().setUsuarioActivo(usuarioLogueado);
            if (usuarioLogueado.getRolUsuario() == RolUsuario.ADMINISTRADOR ) {
                // menu admin
                System.out.println("Menú de ADMINISTRADOR");
            } else {
                // (Opcional) Muestra un mensaje temporal
                setTextLabel(lblMensaje, "Inicio de sesión exitoso. Bienvenido " + usuarioLogueado.getNombreCompleto());

                // Cambiar a la escena del menú de usuario
                cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
            }

        } else {
            setTextLabel(lblMensaje, "El login ha fallado.");
        }
    }

    @FXML
    void irARegistro(ActionEvent event) {
        cambiarEscena(getClass(), Constantes.registroUsuarioPage, event);
    }



    //valida los campos y la estructura del campo del correo.
    private boolean validarCampos(){
        String correo= userInput.getText().trim();
        String pass= passInput.getText().trim();

        if(correo.isEmpty()){
            setTextLabel(lblMensaje, "El campo de correo es obligatorio.");
            return false;
        }
        if (!correo.contains("@") || !correo.contains(".")){
            setTextLabel(lblMensaje, "El campo correo no tiene la estructura correcta.");
            return false;
        }
        if(pass.isEmpty()){
            setTextLabel(lblMensaje,"El campo de contraseña es obligatorio.");
            return false;
        }

        lblMensaje.setVisible(false);
        return true;
    }

}
