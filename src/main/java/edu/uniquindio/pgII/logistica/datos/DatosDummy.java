package edu.uniquindio.pgII.logistica.datos;

import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IEnvioService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IRepartidorService;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

public class DatosDummy {
    private AdministradorSingleton administrador = AdministradorSingleton.getInstance();
    private IUsuarioService usuarioService= administrador.getUsuarioService();
    private IRepartidorService repartidorService= administrador.getRepartidorService();
    private IEnvioService envioService= administrador.getEnvioService();

    public DatosDummy(){
        crearUsuariosDummy();
        crearRepartidoresDummy();
    }

    public void crearUsuariosDummy(){
        usuarioService.registrarUsuario(new UsuarioDTO("admin", "admin", "admin@admin.com", "3105555555", "admin"));
        for (UsuarioDTO usuario: usuarioService.getUsuarios()){
            if(usuario.getIdUsuario().equals("admin")){
                usuario.setRolUsuario(RolUsuario.ADMINISTRADOR);
                usuarioService.actualizarUsuario(usuario, "admin");
            }
        }

        usuarioService.registrarUsuario(new UsuarioDTO("user01","Sofía Herrera","sofia@mail.com","3157778899","pass1"));
        usuarioService.registrarUsuario(new UsuarioDTO("user02","Laura García","laura@mail.com","3112223344","pass2"));
        usuarioService.registrarUsuario(new UsuarioDTO("user03","Andrés Pérez","andres@mail.com","3209876543","pass3"));
        usuarioService.registrarUsuario(new UsuarioDTO("user04","Mateo Cruz","mateo@mail.com","3051112233","pass4"));
        usuarioService.registrarUsuario(new UsuarioDTO("user05","Valentina Rojas","vale@mail.com","3016667788","pass5"));
        usuarioService.registrarUsuario(new UsuarioDTO("user02","Alejandro Gómez","alejandro@mail.com","3001234567","pass6"));
        /*
        usuarioService.registrarUsuario(new UsuarioDTO("user03","Valentina López","vale@mail.com","3109876543","pass7"));
        usuarioService.registrarUsuario(new UsuarioDTO("user04","Daniel Restrepo","daniel.r@mail.com","3216549870","pass8"));
        usuarioService.registrarUsuario(new UsuarioDTO("user05","Camila Vargas","camilav@mail.com","3182223344","pass9"));
        usuarioService.registrarUsuario(new UsuarioDTO("user06","Juan Pablo Soto","juanp@mail.com","3015556677","pass10"));
        usuarioService.registrarUsuario(new UsuarioDTO("user07","Isabella Cruz","isabelac@mail.com","3054445566","pass11"));
        usuarioService.registrarUsuario(new UsuarioDTO("user08","Sebastián Mora","sebastianm@mail.com","3137778800","pass12"));
        usuarioService.registrarUsuario(new UsuarioDTO("user09","Mariana Ríos","marianar@mail.com","3203332211","pass13"));
        usuarioService.registrarUsuario(new UsuarioDTO("user10","Felipe Torres","felipe.t@mail.com","3161110099","pass14"));
        usuarioService.registrarUsuario(new UsuarioDTO("user11","Andrea Pérez","andrea@mail.com","3049991122","pass15"));
        */
    }

    public void crearRepartidoresDummy(){
        repartidorService.registrarRepartidor(new RepartidorDTO("Alonso Gonalez", "1045865987", "3509854687","Norte", DisponibilidadRepartidor.NO_DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Brenda Ramírez", "7945123658", "3105689012", "Sur", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Carlos Velez", "1020347895", "3201478523", "Centro", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Diana Ortiz", "5214789630", "3004569871", "Occidente", DisponibilidadRepartidor.NO_DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Ernesto Pérez", "1030654789", "3157894560", "Oriente", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Fátima Castro", "9876543210", "3112345678", "Norte", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Gabriel Múnera", "1001502403", "3016789012", "Sur", DisponibilidadRepartidor.NO_DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Hilda Londoño", "5102030405", "3218901234", "Centro", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Iván Rojas", "1056789012", "3045678901", "Occidente", DisponibilidadRepartidor.DISPONIBLE));
        repartidorService.registrarRepartidor(new RepartidorDTO("Juliana Soto", "6090807060", "3141234567", "Oriente", DisponibilidadRepartidor.NO_DISPONIBLE));
    }


    // Servicios base por defecto
    public void crearServiciosAdicionalDummy(){

        envioService.registrarServicio(new ServicioAdicional("SEG", "Seguro", "Protege el valor del envío", 3000));
        envioService.registrarServicio(new ServicioAdicional("FRA", "Frágil", "Manejo cuidadoso", 1500));
        envioService.registrarServicio(new ServicioAdicional("FIR", "Firma Requerida", "Entrega solo con firma", 2000));
        envioService.registrarServicio(new ServicioAdicional("PRI", "Prioritario", "Entrega más rápida", 2500));
    }
}
