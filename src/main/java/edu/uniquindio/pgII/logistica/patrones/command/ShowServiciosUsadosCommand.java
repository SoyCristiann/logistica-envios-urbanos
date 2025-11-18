package edu.uniquindio.pgII.logistica.patrones.command;

import edu.uniquindio.pgII.logistica.controlador.adminController.MetricasAdminController;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;

public class ShowServiciosUsadosCommand implements IMetricCommand {

    private final MetricasAdminController receiver;
    private final String fxmlPath = Constantes.modalServiciosMasUsados;
    private final String titulo = "Servicios Adicionales Más Usados";

    public ShowServiciosUsadosCommand(MetricasAdminController receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        // Delega la acción de apertura al Receptor (MetricasAdminController)
        receiver.abrirModalGrafico(fxmlPath, titulo);
    }
}