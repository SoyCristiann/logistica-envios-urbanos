package edu.uniquindio.pgII.logistica.patrones;

public class AdministradorSingleton {
    private static AdministradorSingleton instance;

    private AdministradorSingleton() {}


    public static AdministradorSingleton getInstance() {
        if(instance==null){
            return instance= new AdministradorSingleton();
        }
        return instance;
    }
}
