package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Tarifa;

import java.util.ArrayList;
import java.util.List;

public class TarifaService {

    private List<Tarifa> tarifas;
    private int contador;

    // CONSTRUCTOR

    public TarifaService() {
        this.tarifas = new ArrayList<>();
        this.contador = 0;
    }

    public int generarId(){


        return 0;
    }

    public Tarifa calcularTarifa(double peso, double volumen, double distancia, double prioridad) {


        return null;
    }

    public String desglosarComponentes(Tarifa tarifa){


        return null;
    }

    public ArrayList<Tarifa> listarTarifas(){


        return null;
    }

}
