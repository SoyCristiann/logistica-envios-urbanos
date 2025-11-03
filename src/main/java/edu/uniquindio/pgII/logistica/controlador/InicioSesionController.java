package edu.uniquindio.pgII.logistica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.dto.dtoInicioSesion.InicioSesionDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InicioSesionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

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

        if(usuarioLogueado!=null){ //-> Login exitoso.
            //Almacena el usuario en el sesión manager.
            SesionManagerSingleton.getInstance().setUsuarioActivo(usuarioLogueado);
            mostrarMensaje("El login ha sido exitoso: Usuario -> " + usuarioLogueado.getNombreCompleto(), false); // Mensaje temporal para validación.
        }else{
            mostrarMensaje("El login ha fallado.", false);
        }
    }


    //valida los campos y la estructura del campo del correo.
    private boolean validarCampos(){
        String correo= userInput.getText().trim();
        String pass= passInput.getText().trim();

        System.out.println("Correo es vacio: " + correo.isEmpty());
        if(correo.isEmpty()){
            mostrarMensaje("El campo de correo es obligatorio.", true);
            return false;
        }
        if (!correo.contains("@") || !correo.contains(".")){
            mostrarMensaje("El campo correo no tiene la estructura correcta.", true);
            return false;
        }
        if(pass.isEmpty()){
            mostrarMensaje("El campo de contraseña es obligatorio.", true);
            return false;
        }

        lblMensaje.setVisible(false);
        return true;
    }

    //Mensaje que se muestra en caso de error.
    private void mostrarMensaje(String mensaje, boolean esError) {
        if (lblMensaje != null) {
            lblMensaje.setText(mensaje);
            lblMensaje.setVisible(true);
        }
        System.out.println("InicioSesionController: " + mensaje);
    }
}
