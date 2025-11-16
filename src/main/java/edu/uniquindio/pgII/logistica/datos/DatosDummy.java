package edu.uniquindio.pgII.logistica.datos;

import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.ServicioAdicionalDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.Envio;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoEnvio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;

import java.time.LocalDate;

public class DatosDummy {
    private AdministradorSingleton administrador = AdministradorSingleton.getInstance();
    private IUsuarioService usuarioService= administrador.getUsuarioService();
    private IEnvioService envioService= administrador.getEnvioService();

    public DatosDummy(){
        crearUsuariosDummy();
        crearEnvioDummy();
        crearServiciosAdicionalDummy();
    }

    public void crearUsuariosDummy(){
        usuarioService.registrarUsuario(new Usuario("admin", "admin", "admin@admin.com", "3105555555", "admin"));
        for (Usuario usuario: usuarioService.getUsuarios()){
            if(usuario.getIdUsuario().equals("admin")){
                usuario.setRolUsuario(RolUsuario.ADMINISTRADOR);
            }
        }
        usuarioService.registrarUsuario(new Usuario("user01","Sofía Herrera","sofia@mail.com","3157778899","pass1"));
        usuarioService.registrarUsuario(new Usuario("user02","Laura García","laura@mail.com","3112223344","pass2"));
        usuarioService.registrarUsuario(new Usuario("user03","Andrés Pérez","andres@mail.com","3209876543","pass3"));
        usuarioService.registrarUsuario(new Usuario("user04","Mateo Cruz","mateo@mail.com","3051112233","pass4"));
        usuarioService.registrarUsuario(new Usuario("user05","Valentina Rojas","vale@mail.com","3016667788","pass5"));
        usuarioService.registrarUsuario(new Usuario("user06","Fabes Rodriguez","Fabes@mail.com","3016667788","pass6"));
    }

    // Servicios base por defecto
    public void crearServiciosAdicionalDummy(){

        envioService.registrarServicio(new ServicioAdicional("SEG", "Seguro", "Protege el valor del envío", 3000));
        envioService.registrarServicio(new ServicioAdicional("FRA", "Frágil", "Manejo cuidadoso", 1500));
        envioService.registrarServicio(new ServicioAdicional("FIR", "Firma Requerida", "Entrega solo con firma", 2000));
        envioService.registrarServicio(new ServicioAdicional("PRI", "Prioritario", "Entrega más rápida", 2500));
    }


    private void crearEnvioDummy () {
        // Envíos simulados
        envioService.crearEnvio(new Envio("Armenia", "Pereira", 2.5, "30x20x10", 10000 , EstadoEnvio.SOLICITADO));
        envioService.crearEnvio( new Envio("Armenia", "Cali", 4.0, "40x30x20", 20000,EstadoEnvio.SOLICITADO));
        envioService.crearEnvio(new Envio("Calarca", "Bogotá", 6.5, "30x20x10", 30000 , EstadoEnvio.EN_RUTA));

    }

}
