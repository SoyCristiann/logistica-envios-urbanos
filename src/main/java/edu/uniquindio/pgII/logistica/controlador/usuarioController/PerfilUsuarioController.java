package edu.uniquindio.pgII.logistica.controlador.usuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.singleton.SesionManagerSingleton;
import edu.uniquindio.pgII.logistica.patrones.fachadas.UsuarioFacade;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PerfilUsuarioController {

    // Perfil
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private Label lblNombreUsuario;

    // Dirección
    @FXML private TextField txtCalle, txtNumero, txtBarrio, txtCiudad, txtCodigoPostal, txtDescripcionDir, txtAlias;
    @FXML private TableView<DireccionDTO> tablaDirecciones;
    @FXML private TableColumn<DireccionDTO, String> colAlias, colCiudad, colBarrio, colDescripcion;

    // Secciones
    @FXML private VBox seccionPerfil;
    @FXML private VBox seccionAgregarDireccion;
    @FXML private VBox seccionTabla;

    private UsuarioDTO usuarioActual;
    private final UsuarioFacade facade = new UsuarioFacade();
    private final ObservableList<DireccionDTO> direccionesUsuario = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        usuarioActual = SesionManagerSingleton.getInstance().getUsuarioActivo();

        // Cargar datos actuales
        lblNombreUsuario.setText(usuarioActual.getNombreCompleto());
        txtNombre.setText(usuarioActual.getNombreCompleto());
        txtCorreo.setText(usuarioActual.getCorreo());
        txtTelefono.setText(usuarioActual.getTelefono());

        // Tabla
        colAlias.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAlias()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        colBarrio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBarrio()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
        tablaDirecciones.setItems(direccionesUsuario);

        // Secciones
        seccionPerfil.setVisible(true);
        seccionPerfil.setManaged(true);
        seccionAgregarDireccion.setVisible(false);
        seccionAgregarDireccion.setManaged(false);
        seccionTabla.setVisible(false);
        seccionTabla.setManaged(false);

        cargarDirecciones();
    }

    // Guardar perfil
    @FXML
    private void guardarPerfil() {
        String idActivo = SesionManagerSingleton.getInstance().getUsuarioActivo().getIdUsuario();
        if (!usuarioActual.getIdUsuario().equals(idActivo)) {
            mostrar("Error de seguridad");
            return;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(idActivo);
        dto.setNombreCompleto(txtNombre.getText().trim());
        dto.setCorreo(txtCorreo.getText().trim());
        dto.setTelefono(txtTelefono.getText().trim());
        dto.setPassword(usuarioActual.getPassword());
        dto.setRolUsuario(usuarioActual.getRolUsuarioEnum());
        dto.setDireccionesFrecuentesDTO(List.copyOf(direccionesUsuario));

        boolean ok = facade.actualizarPerfil(dto);

        if (ok) {
            SesionManagerSingleton.getInstance().setUsuarioActivo(dto);
            usuarioActual = dto;
            lblNombreUsuario.setText(dto.getNombreCompleto());
            mostrar("Perfil actualizado correctamente");
        } else {
            mostrar("No se pudo actualizar el perfil");
        }
    }

    // Cargar direcciones
    private void cargarDirecciones() {
        direccionesUsuario.clear();
        direccionesUsuario.addAll(usuarioActual.getDireccionesFrecuentesDTO());
    }

    // Agregar dirección
    @FXML
    private void agregarDireccion() {
        if (!validarCamposDireccion()) return;

        DireccionDTO dto = new DireccionDTO();
        dto.setCalle(txtCalle.getText().trim());
        dto.setNumero(txtNumero.getText().trim());
        dto.setBarrio(txtBarrio.getText().trim());
        dto.setCiudad(txtCiudad.getText().trim());
        dto.setCodigoPostal(txtCodigoPostal.getText().trim());
        dto.setDescripcion(txtDescripcionDir.getText().trim());
        dto.setAlias(txtAlias.getText().trim());

        boolean creada = facade.registrarDireccion(usuarioActual, dto);

        if (creada) {
            limpiarCamposDireccion();
            cargarDirecciones();
            mostrar("Dirección agregada correctamente");
        } else {
            mostrar("No se pudo agregar la dirección");
        }
    }

    private boolean validarCamposDireccion() {
        if (txtCalle.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtAlias.getText().isEmpty()) {
            mostrar("Calle, Ciudad y Alias son obligatorios");
            return false;
        }
        return true;
    }

    private void limpiarCamposDireccion() {
        txtCalle.clear();
        txtNumero.clear();
        txtBarrio.clear();
        txtCiudad.clear();
        txtCodigoPostal.clear();
        txtDescripcionDir.clear();
        txtAlias.clear();
    }

    // Eliminar dirección
    @FXML
    private void eliminarDireccionSeleccionada() {
        DireccionDTO seleccionada = tablaDirecciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) return;

        boolean eliminado = facade.eliminarDireccion(usuarioActual, seleccionada);
        if (eliminado) {
            cargarDirecciones();
            mostrar("Dirección eliminada");
        } else {
            mostrar("No se pudo eliminar la dirección");
        }

    }

    // Volver al menú
    @FXML
    private void volverMenu(ActionEvent event) throws Exception {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
    }

    // Alternar secciones
    @FXML
    private void togglePerfilSection(MouseEvent e) {
        seccionPerfil.setVisible(!seccionPerfil.isVisible());
        seccionPerfil.setManaged(seccionPerfil.isVisible());
    }

    @FXML
    private void toggleAgregarDireccionSection(MouseEvent e) {
        seccionAgregarDireccion.setVisible(!seccionAgregarDireccion.isVisible());
        seccionAgregarDireccion.setManaged(seccionAgregarDireccion.isVisible());
    }

    @FXML
    private void toggleTablaSection(MouseEvent e) {
        seccionTabla.setVisible(!seccionTabla.isVisible());
        seccionTabla.setManaged(seccionTabla.isVisible());
    }

    public void recargarDirecciones() {
        UsuarioDTO actualizado = facade.obtenerUsuarioPorId(usuarioActual.getIdUsuario());
        if (actualizado != null) {
            usuarioActual = actualizado;
            cargarDirecciones();
        }
    }

    private void mostrar(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
