package edu.uniquindio.pgII.logistica.modelo.util.Interface;

import edu.uniquindio.pgII.logistica.modelo.entidades.ServicioAdicional;
import edu.uniquindio.pgII.logistica.modelo.entidades.Tarifa;

import java.util.List;

public interface ITarifaService {

    public abstract Tarifa calcularTarifa(double peso, double alto, double ancho, double largo, List<ServicioAdicional> servicios);

    public abstract String desglosar(Tarifa t);
}
