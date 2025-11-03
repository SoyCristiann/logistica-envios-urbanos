package edu.uniquindio.pgII.logistica.datos;

import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.IUsuarioService;
import edu.uniquindio.pgII.logistica.patrones.AdministradorSingleton;

public class DatosDummy {
    private AdministradorSingleton administrador = AdministradorSingleton.getInstance();
    private IUsuarioService usuarioService= administrador.getUsuarioService();

    public DatosDummy(){
        crearUsuariosDummy();
    }

    public void crearUsuariosDummy(){
        usuarioService.registrarUsuario(new Usuario("admin", "admin", "admin@admin.com", "3105555555", "admin", RolUsuario.ADMINISTRADOR));
        usuarioService.registrarUsuario(new Usuario("user01","Sofía Herrera","sofia@mail.com","3157778899","pass1",RolUsuario.USUARIO));
        usuarioService.registrarUsuario(new Usuario("user02","Laura García","laura@mail.com","3112223344","pass2",RolUsuario.USUARIO));
        usuarioService.registrarUsuario(new Usuario("user03","Andrés Pérez","andres@mail.com","3209876543","pass3",RolUsuario.USUARIO));
        usuarioService.registrarUsuario(new Usuario("user04","Mateo Cruz","mateo@mail.com","3051112233","pass4",RolUsuario.USUARIO));
        usuarioService.registrarUsuario(new Usuario("user05","Valentina Rojas","vale@mail.com","3016667788","pass5",RolUsuario.USUARIO));
    }
}
