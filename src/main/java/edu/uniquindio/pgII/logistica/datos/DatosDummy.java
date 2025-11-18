package edu.uniquindio.pgII.logistica.datos;

import edu.uniquindio.pgII.logistica.modelo.dto.DireccionDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.EnvioDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.RepartidorDTO;
import edu.uniquindio.pgII.logistica.modelo.dto.UsuarioDTO;
import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.DisponibilidadRepartidor;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.*;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatosDummy {
    private AdministradorSingleton administrador = AdministradorSingleton.getInstance();
    private IUsuarioService usuarioService= administrador.getUsuarioService();
    private IRepartidorService repartidorService= administrador.getRepartidorService();
    private IEnvioService envioService= administrador.getEnvioService();
    private IDireccionService direccionService= administrador.getDireccionService();
    private IServicioAdicionalService serviciosService= administrador.getServiciosAdicionalesService();

    public DatosDummy(){
        crearUsuariosDummy();
        crearRepartidoresDummy();
        crearDireccionesDummy();
        crearServiciosDummy();
        crearEnviosDummy();
    }

    public void crearUsuariosDummy(){
        usuarioService.registrarUsuario(new UsuarioDTO("admin", "admin", "admin@admin.com", "3105555555", "admin"));
        for (UsuarioDTO usuario: usuarioService.getUsuarios()){
            if(usuario.getIdUsuario().equals("admin")){
                usuario.setRolUsuario(RolUsuario.ADMINISTRADOR);
                usuarioService.actualizarUsuario(usuario, "admin");
            }
        }

        UsuarioDTO usuario1= usuarioService.registrarUsuario(new UsuarioDTO("1025485798","Sofía Herrera","sofia@mail.com","3157778899","pass1"));
        UsuarioDTO usuario2= usuarioService.registrarUsuario(new UsuarioDTO("1064857412","Laura García","laura@mail.com","3112223344","pass2"));
        UsuarioDTO usuario3= usuarioService.registrarUsuario(new UsuarioDTO("1032632323","Andrés Pérez","andres@mail.com","3209876543","pass3"));
        UsuarioDTO usuario4= usuarioService.registrarUsuario(new UsuarioDTO("1024512458","Mateo Cruz","mateo@mail.com","3051112233","pass4"));
        UsuarioDTO usuario5= usuarioService.registrarUsuario(new UsuarioDTO("1024125478","Valentina Rojas","vale@mail.com","3016667788","pass5"));
        UsuarioDTO usuario6= usuarioService.registrarUsuario(new UsuarioDTO("1030625854","Alejandro Gómez","alejandro@mail.com","3001234567","pass6"));
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

    //Crear direcciones
    public void crearDireccionesDummy(){
        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1025485798"),
                new DireccionDTO("D001", "Calle 5", "12-12", "La esmeralda", "Bogotá", "120021", "Casa esquinera", "Casa de mamá"));

        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1025485798"),
                new DireccionDTO("D002", "Carrera 10", "20-30", "La Castellana", "Armenia", "630001", "Casa con rejas verdes", "Casa Principal")
        );

        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1064857412"),
                new DireccionDTO("D003", "Calle 5", "45-12, Apto 501", "El Peñón", "Cali", "760045", "Edificio de ladrillo", "Apartamento Laura")
        );

        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1032632323"),
                new DireccionDTO("D004", "Avenida 33", "78-15, Bodega 4", "Zona Industrial", "Medellín", "050015", "Recepción por la puerta lateral", "Bodega Andrés")
        );

        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1024512458"),
                new DireccionDTO("D005", "Carrera 2", "99-10", "Altos de Riomar", "Barranquilla", "080001", "Casa blanca", "Casa Abuelos")
        );

        direccionService.registrarDireccion(
                usuarioService.buscarUsuarioPorId("1024125478"),
                new DireccionDTO("D006", "Calle 100", "1-1", "Centro", "Bogotá", "110111", "Torre A, Oficina 702", "Oficina Central")
        );
    }

    // Servicios base por defecto - No entiendo cuál es la función de esto en envio
    /*public void crearServiciosAdicionalDummy(){
        envioService.registrarServicio(new ServicioAdicional("SEG", "Seguro", "Protege el valor del envío", 3000));
        envioService.registrarServicio(new ServicioAdicional("FRA", "Frágil", "Manejo cuidadoso", 1500));
        envioService.registrarServicio(new ServicioAdicional("FIR", "Firma Requerida", "Entrega solo con firma", 2000));
        envioService.registrarServicio(new ServicioAdicional("PRI", "Prioritario", "Entrega más rápida", 2500));
    }*/

    public void crearServiciosDummy(){
        serviciosService.registrarServicioAdicional(new ServicioAdicional("SEG", "Seguro", "Protege el valor del envío", 3000));
        serviciosService.registrarServicioAdicional(new ServicioAdicional("FRA", "Frágil", "Manejo cuidadoso", 1500));
        serviciosService.registrarServicioAdicional(new ServicioAdicional("FIR", "Firma Requerida", "Entrega solo con firma", 2000));
        serviciosService.registrarServicioAdicional(new ServicioAdicional("PRI", "Prioritario", "Entrega más rápida", 2500));
    }

    // Crear envíos
    public void crearEnviosDummy(){

        //Envios abiertos

        List<String> serviciosEnvio1 = new ArrayList<>();
        serviciosEnvio1.add("SEG");
        envioService.crearEnvio(new EnvioDTO(
                "1025485798",
                direccionService.buscarDireccionPorId("D001"),
                direccionService.buscarDireccionPorId("D002"),
                5, 10, 20, 40,
                LocalDate.now().minusDays(10),
                serviciosEnvio1
        ));

        List<String> serviciosEnvio2 = new ArrayList<>();
        serviciosEnvio2.add("SEG");
        serviciosEnvio2.add("FRA");
        serviciosEnvio2.add("PRI");
        envioService.crearEnvio(new EnvioDTO(
                "1032632323", // ID Usuario (Andrés)
                direccionService.buscarDireccionPorId("D004"),
                direccionService.buscarDireccionPorId("D001"),
                25.0, 50.0, 50.0, 60.0,
                LocalDate.now().minusDays(3),
                serviciosEnvio2
        ));


        List<String> serviciosEnvio3 = new ArrayList<>();
        serviciosEnvio3.add("FIR");
        envioService.crearEnvio(new EnvioDTO(
                "1024125478",
                direccionService.buscarDireccionPorId("D006"),
                direccionService.buscarDireccionPorId("D005"),
                1.2, 15.0, 10.0, 5.0,
                LocalDate.now().minusDays(1),
                serviciosEnvio3
        ));

        List<String> serviciosEnvio4 = new ArrayList<>();
        serviciosEnvio4.add("SEG");
        serviciosEnvio4.add("FIR");
        envioService.crearEnvio(new EnvioDTO(
                "1030625854",
                direccionService.buscarDireccionPorId("D001"),
                direccionService.buscarDireccionPorId("D002"),
                20, 20, 20, 30,
                LocalDate.now().minusDays(2),
                serviciosEnvio4
        ));

    }

}
