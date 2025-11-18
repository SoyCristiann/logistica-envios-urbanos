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
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
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
    private UsuarioFacade facade = new UsuarioFacade();
    private List<DireccionDTO> direccionesUsuario = new ArrayList<>();

    @FXML
    public void initialize() {

        usuarioActual = SesionManagerSingleton.getInstance().getUsuarioActivo();

        // Cargar datos actuales
        if (lblNombreUsuario != null)
            lblNombreUsuario.setText(usuarioActual.getNombreCompleto());

        txtNombre.setText(usuarioActual.getNombreCompleto());
        txtCorreo.setText(usuarioActual.getCorreo());
        txtTelefono.setText(usuarioActual.getTelefono());

        // Tabla
        colAlias.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAlias()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        colBarrio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBarrio()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));

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
            System.out.println("Error de seguridad");
            return;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(idActivo);
        dto.setNombreCompleto(txtNombre.getText());
        dto.setCorreo(txtCorreo.getText());
        dto.setTelefono(txtTelefono.getText());
        dto.setPassword(usuarioActual.getPassword());
        dto.setRolUsuario(usuarioActual.getRolUsuarioEnum());
        dto.setDireccionesFrecuentesDTO(direccionesUsuario);

        boolean ok = facade.actualizarPerfil(dto);

        if (ok) {
            // Actualizar sesión
            SesionManagerSingleton.getInstance().setUsuarioActivo(dto);
            usuarioActual = dto;

            // Actualizar label
            lblNombreUsuario.setText(dto.getNombreCompleto());
        }
    }

    // Cargar direcciones
    private void cargarDirecciones() {
        direccionesUsuario.clear();
        direccionesUsuario.addAll(usuarioActual.getDireccionesFrecuentesDTO());
        tablaDirecciones.getItems().setAll(direccionesUsuario);
    }

    // Agregar dirección
    @FXML
    private void agregarDireccion() {

        DireccionDTO dto = new DireccionDTO();
        dto.setCalle(txtCalle.getText());
        dto.setNumero(txtNumero.getText());
        dto.setBarrio(txtBarrio.getText());
        dto.setCiudad(txtCiudad.getText());
        dto.setCodigoPostal(txtCodigoPostal.getText());
        dto.setDescripcion(txtDescripcionDir.getText());
        dto.setAlias(txtAlias.getText());

        boolean creada = facade.registrarDireccion(usuarioActual, dto);

        if (creada) {
            limpiarCamposDireccion();
            cargarDirecciones();
        }
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

        if (eliminado) cargarDirecciones();
    }

    // Volver
    @FXML
    private void volverMenu(ActionEvent event) throws Exception {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
    }

    // Alternar secciones
    @FXML
    private void togglePerfilSection(MouseEvent e) {
        boolean b = !seccionPerfil.isVisible();
        seccionPerfil.setVisible(b);
        seccionPerfil.setManaged(b);
    }

    @FXML
    private void toggleAgregarDireccionSection(MouseEvent e) {
        boolean b = !seccionAgregarDireccion.isVisible();
        seccionAgregarDireccion.setVisible(b);
        seccionAgregarDireccion.setManaged(b);
    }

    @FXML
    private void toggleTablaSection(MouseEvent e) {
        boolean b = !seccionTabla.isVisible();
        seccionTabla.setVisible(b);
        seccionTabla.setManaged(b);
    }
}
