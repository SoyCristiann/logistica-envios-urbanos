package edu.uniquindio.pgII.logistica.controlador.UsuarioController;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.VentanaUtil;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.modelo.util.mappers.DireccionMapper;
import edu.uniquindio.pgII.logistica.patrones.SesionManagerSingleton;
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

    // CAMPOS DE PERFIL
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;

    // CAMPOS DIRECCIONES
    @FXML private TextField txtCalle, txtNumero, txtBarrio, txtCiudad, txtCodigoPostal, txtDescripcionDir, txtAlias;
    @FXML private TableView<DireccionDTO> tablaDirecciones;
    @FXML private TableColumn<DireccionDTO, String> colAlias, colCiudad, colBarrio, colDescripcion;

    // Secciones (vistas Desplegables)
    @FXML private VBox seccionPerfil;
    @FXML private VBox seccionAgregarDireccion;
    @FXML private VBox seccionTabla;

    private Usuario usuarioActual;
    private UsuarioFacade facade = new UsuarioFacade();
    private List<DireccionDTO> direccionesUsuario = new ArrayList<>();

    @FXML
    public void initialize() {

        usuarioActual = SesionManagerSingleton.getInstance().getUsuarioActivo();

        // Configurar columnas tabla
        colAlias.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAlias()));
        colCiudad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCiudad()));
        colBarrio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBarrio()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));

        // Visibilidad inicial de secciones
        // Perfil visible por defecto, las otras dos ocultas.
        if (seccionPerfil != null) {
            seccionPerfil.setVisible(true);
            seccionPerfil.setManaged(true);
        }
        if (seccionAgregarDireccion != null) {
            seccionAgregarDireccion.setVisible(false);
            seccionAgregarDireccion.setManaged(false);
        }
        if (seccionTabla != null) {
            seccionTabla.setVisible(false);
            seccionTabla.setManaged(false);
        }

        cargarDirecciones();
    }


    // PERFIL

    @FXML
    private void guardarPerfil() {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuarioActual.getIdUsuario());
        dto.setNombreCompleto(txtNombre.getText());
        dto.setCorreo(txtCorreo.getText());
        dto.setTelefono(txtTelefono.getText());
        dto.setPassword(usuarioActual.getPassword());
        dto.setRolUsuario(usuarioActual.getRolUsuario());
        dto.setDireccionesFrecuentesDTO(direccionesUsuario);

        boolean ok = facade.actualizarPerfil(dto);

        System.out.println(ok ? "Perfil actualizado" : "No se pudo actualizar");
    }


    // DIRECCIONES

    private void cargarDirecciones() {
        direccionesUsuario.clear();

        usuarioActual.getDireccionesFrecuentes()
                .forEach(d -> direccionesUsuario.add(DireccionMapper.toDTO(d)));

        tablaDirecciones.getItems().setAll(direccionesUsuario);
    }

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

    @FXML
    private void eliminarDireccionSeleccionada() {

        DireccionDTO seleccionada = tablaDirecciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            System.out.println("Debe seleccionar una dirección");
            return;
        }

        boolean eliminado = facade.eliminarDireccion(usuarioActual, seleccionada);

        if (eliminado) {
            cargarDirecciones();
        }
    }


    // VOLVER

    @FXML
    private void volverMenu(ActionEvent event) throws Exception {
        VentanaUtil.cambiarEscena(getClass(), Constantes.menuUsuarioPage, event);
    }

    // TOGGLE SECTIONS (UI Helpers)

    /**
     * Muestra/oculta la sección de perfil.
     * Está enlazado al onMouseClicked del botón "Editar Perfil" en el FXML.
     */
    @FXML
    private void togglePerfilSection(MouseEvent event) {
        if (seccionPerfil == null) return;
        boolean nuevoEstado = !seccionPerfil.isVisible();
        seccionPerfil.setVisible(nuevoEstado);
        seccionPerfil.setManaged(nuevoEstado);
    }

    /**
     * Muestra/oculta la sección de agregar dirección.
     * Está enlazado al onMouseClicked del botón "Agregar Dirección" en el FXML.
     */
    @FXML
    private void toggleAgregarDireccionSection(MouseEvent event) {
        if (seccionAgregarDireccion == null) return;
        boolean nuevoEstado = !seccionAgregarDireccion.isVisible();
        seccionAgregarDireccion.setVisible(nuevoEstado);
        seccionAgregarDireccion.setManaged(nuevoEstado);
    }

    /**
     * Muestra/oculta la sección de tabla de direcciones.
     * Está enlazado al onMouseClicked del botón "Ver Direcciones Registradas" en el FXML.
     */
    @FXML
    private void toggleTablaSection(MouseEvent event) {
        if (seccionTabla == null) return;
        boolean nuevoEstado = !seccionTabla.isVisible();
        seccionTabla.setVisible(nuevoEstado);
        seccionTabla.setManaged(nuevoEstado);
    }

}
