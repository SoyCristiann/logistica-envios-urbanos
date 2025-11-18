package edu.uniquindio.pgII.logistica.controlador.adminController.graficos;

import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ServiciosUsadosController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private final IEnvioService envioService = AdministradorSingleton.getInstance().getEnvioService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosGrafico();
    }

    private void cargarDatosGrafico() {
        barChart.getData().clear(); //-> Limpia el gráfico y asegura que esté vacío

        Map<String, Integer> serviciosUsados = envioService.getServiciosAdicionalesMasUsados(); //-> Se obtienen los datos más usados.
        if (serviciosUsados == null || serviciosUsados.isEmpty()) {
            barChart.setTitle("Frecuencia de Servicios Adicionales (Sin Datos)");
            return;
        }

        // Crea la Serie de datos y le asigna el nombre
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cantidad de Usos");

        // Llena la Serie
        ObservableList<XYChart.Data<String, Number>> chartData = FXCollections.observableArrayList();

        // Itera el Map y se crean los puntos de datos (serviciosUsados)
        serviciosUsados.forEach((servicio, cantidad) -> {
            chartData.add(new XYChart.Data<>(servicio, cantidad));
        });

        series.setData(chartData);

        // Agregar la serie al elemento barchart
        barChart.getData().add(series);
    }
}