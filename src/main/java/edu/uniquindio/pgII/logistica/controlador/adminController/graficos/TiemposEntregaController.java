package edu.uniquindio.pgII.logistica.controlador.adminController.graficos;

import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;

public class TiemposEntregaController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private final IEnvioService envioService = AdministradorSingleton.getInstance().getEnvioService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuramos el eje Y para mostrar solo un decimal (para tiempo promedio)
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
            @Override
            public String toString(Number value) {
                // Formato para mostrar solo un decimal, ejemplo: 2.5
                return String.format("%.1f", value.doubleValue());
            }
        });

        cargarDatosGrafico();
    }

    private void cargarDatosGrafico() {
        barChart.getData().clear();

        // Obtener el tiempo promedio global con el método del servicio
        double promedioDias = envioService.getTiempoPromedioEntregaGlobal();

        if (promedioDias <= 0.0) {
            barChart.setTitle("Promedio Global de Días de Entrega (Sin Datos Entregados)");
            return;
        }

        // Se crea la serie de datos, en este caso no se necesita más que una sola barra.
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Promedio en Días");

        // Agregar el único punto de datos
        series.getData().add(new XYChart.Data<>("Promedio Global", promedioDias));

        // Se agrega la serie al barchar
        barChart.getData().add(series);

        // Se le agrega un punto más al liminte del eje y del gráfico apra una mejor visual
        yAxis.setUpperBound(Math.max(1.0, promedioDias + 1.0));
    }
}