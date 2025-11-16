package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.aplicacion.App;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PerfilUsuarioController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<String> comboDirecciones;

    @FXML
    void initialize() {
        System.out.println("Vista cargada correctamente");
    }


    @FXML
    private void guardarPerfil() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();

        SesionManagerSingleton sesion = SesionManagerSingleton.getInstance();
        UsuarioDTO usuarioActual = sesion.getUsuarioActivo();

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuarioActual.getIdUsuario());
        usuarioDTO.setNombreCompleto(nombre);
        usuarioDTO.setCorreo(correo);
        usuarioDTO.setTelefono(telefono);

        UsuarioFacade fachada = new UsuarioFacade();
        fachada.actualizarPerfil(usuarioDTO);

        System.out.println("Perfil actualizado correctamente"); // confirmacion
        // aun falta confirmar
    }

    @FXML
    private void agregarDireccion() {
        comboDirecciones.getItems().add("Nueva dirección " + (comboDirecciones.getItems().size() + 1));
    }

    @FXML
    private void eliminarDireccion() {
        String seleccion = comboDirecciones.getSelectionModel().getSelectedItem();
        if (seleccion != null) {
            comboDirecciones.getItems().remove(seleccion);
        }
    }

    @FXML
    private void volverMenu(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/MenuUsuarioPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menú Principal del Usuario");
        stage.centerOnScreen();
        stage.show();
    }
}
