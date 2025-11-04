package edu.uniquindio.pgII.logistica.patrones;

import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.RolUsuario;

public class SesionManagerSingleton {
    private static SesionManagerSingleton instance;
    private Usuario usuarioActivo;

    //constructor privado según lo describe el patrón de diseño
    private SesionManagerSingleton() {}

    //Acceso a través del metodo getInstance
    public static SesionManagerSingleton getInstance() {
        if(instance==null){
            instance= new SesionManagerSingleton();
        }
        return instance;
    }

    //Metodo para establecer el usuario activo en la sesión
    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    //Metodo para identificar el rol del usuario
    public boolean esAdministrador(){
        if(!(usuarioActivo.getRolUsuario() == null) && !usuarioActivo.getRolUsuario().equals(RolUsuario.ADMINISTRADOR)){
            return false;
        }
        return true;
    }




}
