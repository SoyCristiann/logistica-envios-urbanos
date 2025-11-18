package edu.uniquindio.pgII.logistica.controlador.adminController;

import edu.uniquindio.pgII.logistica.patrones.command.IMetricCommand;
import edu.uniquindio.pgII.logistica.patrones.command.ShowServiciosUsadosCommand;
import edu.uniquindio.pgII.logistica.patrones.command.ShowTiemposEntregaCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MetricasAdminController {

    // --- Métodos de Acción (INICIALIZADOR / INVOKER) ---

    @FXML
    private void handleServiciosUsadosClick(ActionEvent event) {
        // Ejecuta el comando específico
        IMetricCommand command = new ShowServiciosUsadosCommand(this);
        command.execute();
    }

    @FXML
    private void handleTiemposEntregaClick(ActionEvent event) {
        IMetricCommand command = new ShowTiemposEntregaCommand(this);
        command.execute();
    }

    @FXML
    private void handleIngresosClick(ActionEvent event) {
        // Ejemplo para futuras implementaciones
        // MetricCommand command = new ShowIngresosCommand(this);
        // command.execute();
        System.out.println("Ingresos click");
    }

    /**
     * Lógica para abrir un modal, utilizada por todos los comandos.
     */
    public void abrirModalGrafico(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error al cargar el modal: " + e.getMessage());
        }
    }
}
