package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.Usuario;

public interface IUsuarioService {
    public abstract Usuario iniciarSesion(String user, String pass);
}
