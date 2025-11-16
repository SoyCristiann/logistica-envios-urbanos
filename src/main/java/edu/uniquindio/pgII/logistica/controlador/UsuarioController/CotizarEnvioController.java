package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil.*;

public class CotizarEnvioController {

    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtPeso;
    @FXML private TextField txtDimensiones;

    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkFragil;
    @FXML private CheckBox chkFirma;
    @FXML private CheckBox chkPrioritario;

    @FXML private Label lblResultado;

    @FXML
    void cotizarEnvio(ActionEvent event) {
        try {
            // Crear DTO
            EnvioDTO envioDTO = new EnvioDTO();
            envioDTO.setOrigen(txtOrigen.getText().trim());
            envioDTO.setDestino(txtDestino.getText().trim());
            envioDTO.setPeso(Double.parseDouble(txtPeso.getText().trim()));
            envioDTO.setDimensiones(txtDimensiones.getText().trim());
            envioDTO.setSeguro(chkSeguro.isSelected());
            envioDTO.setFragil(chkFragil.isSelected());
            envioDTO.setFirmaRequerida(chkFirma.isSelected());
            envioDTO.setPrioritario(chkPrioritario.isSelected());

            // Calcular costo usando la fachada
            UsuarioFacade fachada = new UsuarioFacade();
            double costo = fachada.cotizarEnvio(envioDTO);

            lblResultado.setText(String.format("Costo estimado del envío: $%.2f", costo));
        } catch (Exception e) {
            lblResultado.setText("Error: Verifica los datos ingresados.");
        }
    }

    @FXML
    void volverMenu(ActionEvent event) {
        cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
    }
}
