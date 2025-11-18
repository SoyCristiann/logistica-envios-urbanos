package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Tarifa;
import edu.uniquindio.pgII.logistica.modelo.util.Interface.ITarifaService;
import edu.uniquindio.pgII.logistica.modelo.util.constantes.Constantes;
import edu.uniquindio.pgII.logistica.patrones.singleton.AdministradorSingleton;

import java.util.ArrayList;
import java.util.List;

public class TarifaService implements ITarifaService {

    private final List<Tarifa> tarifas = new ArrayList<>();
    private int contador = 1;


    private String generarId() {
        return "TAR-" + (contador++);
    }

    // Calcular tarifa según peso, volumen y servicios adicionales
    @Override
    public Tarifa calcularTarifa(double peso, double alto, double ancho, double largo,
                                 List<ServicioAdicional> servicios) {

        double costoBase = Constantes.precioBase;
        double costoPorPeso = Constantes.precioPorPeso * peso;
        double volumen = alto * ancho * largo;
        double costoPorVolumen = volumen * Constantes.precioPorVolumen;

        double costoServicios = 0;

        if (servicios != null) {
            for (ServicioAdicional s : servicios) {

                double costoPorEste = 0;
                if (s.getStrategy() != null) {
                    costoPorEste = s.getStrategy().calcularCosto(peso, alto, ancho, largo);
                }
                costoServicios += costoPorEste;
            }
        }

        double total = costoBase + costoPorPeso + costoPorVolumen + costoServicios;

        Tarifa t = new Tarifa(
                generarId(),
                costoBase,
                costoPorPeso,
                costoPorVolumen,
                total,
                servicios
        );

        return t;
    }




    // Desglose en texto
    @Override
    public String desglosar(Tarifa t) {

        String serviciosTexto = "";

        if(t.getServiciosIncluidos() != null && !t.getServiciosIncluidos().isEmpty()) {
            for (ServicioAdicional s : t.getServiciosIncluidos()) {
                serviciosTexto += "- " + s.getNombreServicio() + ": " + t.getTotal() + "\n";
            }
        } else {
            serviciosTexto = "No tiene servicios adicionales\n";
        }

        String texto =
                "Tarifa\n" +
                        "Costo base: " + t.getCostoBase() + "\n" +
                        "Costo por peso: " + t.getCostoPorPeso() + "\n" +
                        "Costo por volumen: " + t.getCostoPorVolumen() + "\n" +
                        "Servicios adicionales:\n" +
                        serviciosTexto +
                        "Total: " + t.getTotal();

        return texto;
    }


    // Listar tarifas creadas
    public List<Tarifa> listarTarifas() {
        return new ArrayList<>(tarifas);
    }
}
