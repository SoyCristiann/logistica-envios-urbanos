package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Tarifa;

import java.util.ArrayList;
import java.util.List;

public class TarifaService {

    private List<Tarifa> tarifas;
    private int contador;

    public TarifaService() {
        this.tarifas = new ArrayList<>();
        this.contador = 1;
    }

    // Genera un número de id sencillo para cada tarifa
    public String generarId() {
        String id = "TAR-" + contador;
        contador++;
        return id;
    }

    // Crea una tarifa con valores básicos
    public Tarifa calcularTarifa(double peso, double volumen, double distancia, double prioridad) {
        double costoBase = 5000;
        double costoPorPeso = peso * 200;
        double costoPorVolumen = volumen * 100;
        double recargoPrioridad = prioridad * 500;
        double recargoDistancia = distancia * 50;

        double total = costoBase + costoPorPeso + costoPorVolumen + recargoPrioridad + recargoDistancia;

        Tarifa tarifa = new Tarifa(
                generarId(),
                costoBase,
                costoPorPeso,
                costoPorVolumen,
                recargoPrioridad,
                recargoDistancia,
                total
        );

        tarifas.add(tarifa);
        return tarifa;
    }


    public String desglosarComponentes(Tarifa tarifa) {
        return "Detalles de la tarifa:\n" +
                "Costo base: " + tarifa.getCostoBase() + "\n" +
                "Costo por peso: " + tarifa.getCostoPorPeso() + "\n" +
                "Costo por volumen: " + tarifa.getCostoPorVolumen() + "\n" +
                "Recargo por prioridad: " + tarifa.getRecargoPrioridad() + "\n" +
                "Recargo por distancia: " + tarifa.getRecargoDistancia() + "\n" +
                "Total: " + tarifa.getTotal();
    }

    public ArrayList<Tarifa> listarTarifas() {
        return new ArrayList<>(tarifas);
    }
}
