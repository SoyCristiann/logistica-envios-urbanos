package edu.uniquindio.pgII.logistica.modelo.util;

import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VentanaUtil {
    private static SesionManagerSingleton sesionManager= SesionManagerSingleton.getInstance();
    /**
     * Este método está creado para cambiar a una nueva ventana, reemplazando la ventana actual
     * @param callingClass La clase del controlador que llama. Siempre se debe pasar como "getClass()" desde el mismo controlador.
     * @param fxmlPath La ruta al archivo FXML. Usar la clase Rutas, Ej: Rutas.menuUsuarioPath.
     * @param event El ActionEvent que contiene el Stage actual.
     */

    public static void cambiarEscena(Class<?> callingClass, String fxmlPath, ActionEvent event) {
        try {
            // 1. Verificar y cargar la URL usando la clase que llama
            java.net.URL location = callingClass.getResource(fxmlPath);

            // Diagnóstico y Verificación
            System.out.println("Intentando cargar ruta FXML: " + fxmlPath);
            System.out.println("Resultado de URL (debe ser NO nulo): " + location);

            if (location == null) {
                throw new RuntimeException("FXML no encontrado. El recurso fue nulo en la ruta: " + fxmlPath);
            }

            // 2. Obtener el Stage actual a partir del ActionEvent
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            // 3. Cargar el nuevo archivo FXML
            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();

            // 4. Crear y establecer la nueva escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.setTitle("Logística - Pantalla Principal.");
            stage.show();

        } catch (Exception e) {
            System.err.println("No se pudo mostrar la pantalla solicitada:" + e.getMessage());
            e.printStackTrace();
        }
    }

    //Este metodo identifica el usuario logueado y lo muestra en la pantalla del sistema
    public static void setUsuarioLogueado(Label label){

            if (sesionManager.getUsuarioActivo() != null) {
                String usuarioLogueado = sesionManager.getUsuarioActivo().getNombreCompleto();
                RolUsuario rolUsuario = sesionManager.getUsuarioActivo().getRolUsuario();
                label.setText("Bienvenido " + usuarioLogueado + " - Rol: " + rolUsuario);
                label.setVisible(true);
            }
    }

    /**
     * Este metodo setea un mensaje en un elemento label.
     * @param idLabel es el id o identificador del elemento label (Ej: mensajeConfirmacion)
     * @param mensaje es el mensaje que se va a setear en el elemento label.
     * */
    public static void setTextLabel(Label idLabel, String mensaje) {
        if (idLabel != null) {
            idLabel.setText(mensaje);
            idLabel.setVisible(true);
        }
    }

}
