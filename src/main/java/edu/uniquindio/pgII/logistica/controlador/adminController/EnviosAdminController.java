package edu.uniquindio.pgII.logistica.controlador.adminController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import edu.uniquindio.pgII.logistica.modelo.entidades.Direccion;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.patrones.builder.repartidores.Repartidor;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EnviosAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    // Tabla admin envíos
    @FXML private TableView<Envio> tblAdminEnvios;
    @FXML private TableColumn<Envio, String> colId;
    @FXML private TableColumn<Envio, EstadoEnvio> colEstado;
    @FXML private TableColumn<Envio, Direccion> colOrigen;
    @FXML private TableColumn<Envio, Direccion> colDestino;
    @FXML private TableColumn<Envio, Double> colCosto;
    @FXML private TableColumn<Envio, Repartidor> colRepartidor;
    @FXML private TableColumn<Envio, LocalDate> colFechaCreacion;
    @FXML private TableColumn<Envio, LocalDate> colFechaEntrega;
    @FXML private TableColumn<Envio, Void> colEditar;

    @FXML
    void initialize() {
        actualizarTblEnvios();
        colId.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        configurarColumnaDireccion(colOrigen);
        configurarColumnaDireccion(colDestino);
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        colRepartidor.setCellValueFactory(new PropertyValueFactory<>("repartidor"));
        colFechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        colFechaEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
        configurarBotonEditar();
    }

    IEnvioService envioService= AdministradorSingleton.getInstance().getEnvioService();

    public void actualizarTblEnvios() {
        tblAdminEnvios.getItems().clear();
        tblAdminEnvios.getItems().addAll(envioService.getEnvios());
        tblAdminEnvios.refresh();
    }

    /***
     * este métdodo se crea para ajustar el campo dirección que será visible en la tabla y que solo muestre la ciudad.
     * */
    private void configurarColumnaDireccion(TableColumn<Envio, Direccion> columna) {
        columna.setCellFactory(new Callback<TableColumn<Envio, Direccion>, TableCell<Envio, Direccion>>() {

            @Override
            public TableCell<Envio, Direccion> call(TableColumn<Envio, Direccion> param) {
                return new TableCell<Envio, Direccion>() {
                    @Override
                    protected void updateItem(Direccion item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getCiudad());
                        }
                    }
                };
            }
        });
    }

    /**
     * Este métdodo se crea para que el botón editar sea visible en las filas
     * */
    private void configurarBotonEditar() {
        colEditar.setCellFactory(new Callback<TableColumn<Envio, Void>, TableCell<Envio, Void>>() {

            @Override
            public TableCell<Envio, Void> call(TableColumn<Envio, Void> param) {

                //Crea la celda
                final TableCell<Envio, Void> cell = new TableCell<Envio, Void>() {

                    //Crea el botón y se le ingresa el valor.
                    private final Button btn = new Button("Editar");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                //Se obtiene el envío
                                Envio envioAEditar = getTableView().getItems().get(getIndex());

                                //El manejo se delega al método que está más abajo
                                handleEditarEnvio(envioAEditar);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };

                return cell;
            }
        });
    }

    /**
     * Método de tu controlador para manejar el Envío seleccionado y abrir la vista de edición.
     */
    private void handleEditarEnvio(Envio envioAEditar) {
        try {
            Envio envioActualizado = envioService.buscarEnvioPorId(envioAEditar.getIdEnvio());
            if (envioActualizado == null) {
                System.out.println("Error: Envío no encontrado en el servicio.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.editarEnviosAdminPage));
            Parent root = loader.load();
            EditarEnvioAdminController controller = loader.getController();
            controller.setPadreController(this);
            controller.cargarDatosEnvio(envioActualizado);

            //Nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Editar Envío: " + envioAEditar.getIdEnvio());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error al cargar la ventana de edición: " + e.getMessage());
        }
    }
}

