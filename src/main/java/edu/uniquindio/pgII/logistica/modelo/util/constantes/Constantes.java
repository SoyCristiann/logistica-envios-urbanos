package edu.uniquindio.pgII.logistica.modelo.util.constantes;

public class Constantes {

    // RUTAS DE INTERFACES GRÁFICAS

    // Sesión y registro
    public static final String inicioSesionPage = "/fxml/InicioSesionPage.fxml";
    public static final String registroUsuarioPage = "/fxml/RegistroUsuarioPage.fxml";

    // Usuario
    public static final String menuUsuarioPage = "/fxml/UsuarioViews/MenuUsuarioPage.fxml";
    public static final String perfilUsuarioPage = "/fxml/UsuarioViews/PerfilUsuarioPage.fxml";

    // Gestión de Envíos
    public static final String gestionEnviosPage = "/fxml/UsuarioViews/GestionEnviosPage.fxml";

    // Subvistas dentro de la gestión de envíos
    public static final String crearEnvioPage = "/fxml/UsuarioViews/CrearEnvioPage.fxml";
    public static final String cotizadorEnvioPage = "/fxml/UsuarioViews/CotizarEnvioPage.fxml";
    public static final String modificarEnvioPage = "/fxml/UsuarioViews/ModificarEnvioPage.fxml";
    public static final String rastreoEnvioPage = "/fxml/UsuarioViews/RastreoEnvioPage.fxml";
    public static final String serviciosAdicionalesPage = "/fxml/UsuarioViews/ServiciosAdicionalesPage.fxml";
    public static final String historialEnviosPage = "/fxml/UsuarioViews/HistorialEnviosPage.fxml";
    public static final String pagoEnvioPage = "/fxml/UsuarioViews/PagoEnvioPage.fxml";
    public static final String reportesPage = "/fxml/UsuarioViews/ReportesPage.fxml";

    // Administrador
    public static final String administradorMainPage = "/fxml/adminViews/MainPageAdmin.fxml";

    // CONSTANTES DE COSTOS BASE
    public static final double precioBase = 5000;
    public static final double precioPorPeso = 200;
    public static final double precioPorVolumen = 100;
    public static final double precioPorPrioridad = 500;
    public static final double precioPorDistancia = 50;
}
