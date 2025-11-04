package edu.uniquindio.pgII.logistica.modelo.dto;

public class InicioSesionDTO {
    private String user;
    private String pass;

    public InicioSesionDTO(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public InicioSesionDTO() {
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setUser(String user) {
        this.user=user;
    }

    public void setPass(String pass) {
        this.pass=pass;    }
}
