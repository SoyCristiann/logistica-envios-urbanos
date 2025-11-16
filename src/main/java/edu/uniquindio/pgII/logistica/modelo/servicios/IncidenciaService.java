
package edu.uniquindio.pgII.logistica.modelo.servicios;

import edu.uniquindio.pgII.logistica.modelo.entidades.Incidencia;
import edu.uniquindio.pgII.logistica.patrones.builder.envios.Envio;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.EstadoIncidencia;
import edu.uniquindio.pgII.logistica.modelo.util.Enum.TipoIncidencia;
import java.time.LocalDateTime;
import java.util.ArrayList;

    public class IncidenciaService {


        private ArrayList<Incidencia> incidencias;
        private int contador;

        // CONSTRUCTORES
        public IncidenciaService() {
        }



        // METODOS

        public int generarId() {
            return 0;
        }

        public Incidencia registrarIncidencia(Envio envio, TipoIncidencia tipo, String descripcion) {
            return null;
        }

        public void actualizarEstado(int idIncidencia, EstadoIncidencia nuevoEstado) {
        }

        public ArrayList<Incidencia> listarPorEnvio(int idEnvio) {
            return null;
        }

        public ArrayList<Incidencia> listarPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
            return null;
        }

        public Incidencia consultarDetalle(int idIncidencia) {
            return null;
        }

    }


