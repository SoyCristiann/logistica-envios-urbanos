package edu.uniquindio.pgII.logistica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import edu.uniquindio.pgII.logistica.util.MetodoPago;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRegistrarseUsuario;

    @FXML
    private CheckBox checkBoxMetodoPagoUsuario;

    @FXML
    private ComboBox<MetodoPago> comboMetodoPagoUsuario; //Pasar la lista de metodos de pago

    @FXML
    private TextField inputDireccionTelefono;

    @FXML
    private TextField inputEmailUsuario;

    @FXML
    private TextField inputIdUsuario;

    @FXML
    private TextField inputNombreUsuario;

    @FXML
    private TextField inputTelefonoUsuario;

    @FXML
    void registrarUsuario(ActionEvent event) {

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
                        inputIdUsuario.getText().trim().isEmpty() ||
                        inputNombreUsuario.getText().trim().isEmpty() ||
                        inputEmailUsuario.getText().trim().isEmpty() ||
                        inputTelefonoUsuario.getText().trim().isEmpty() ||
                        inputDireccionTelefono.getText().trim().isEmpty(),

                        // Sección de propiedades
                        inputIdUsuario.textProperty(),
                        inputNombreUsuario.textProperty(),
                        inputEmailUsuario.textProperty(),
                        inputTelefonoUsuario.textProperty(),
                        inputDireccionTelefono.textProperty()
        );
        //Sección final que sincroniza el valor de la propiedad 'disabled' con lo obtenido en camposObligatoriosVacios
        btnRegistrarseUsuario.disableProperty().bind(camposObligatoriosVacios);


    }

    void agregarOpcionesComboBox(){
        MetodoPago[] metodos = MetodoPago.values(); //-> Obtiene los elementos del enum
        comboMetodoPagoUsuario.setItems(FXCollections.observableArrayList(metodos));
    }

    public void habilitarMetodoPago(ActionEvent event){
        boolean isSelected= checkBoxMetodoPagoUsuario.isSelected(); //Trae el valor actual marcado o desmarcado.
        comboMetodoPagoUsuario.setDisable(!isSelected); //Dependiendo el valor actual, aplica el valor contrario.

        //Si el usuario desmarca el checkbox, debe limpiarse cualquier selección previa
        if(!isSelected){
            comboMetodoPagoUsuario.getSelectionModel().clearSelection();
        }
    }

}
