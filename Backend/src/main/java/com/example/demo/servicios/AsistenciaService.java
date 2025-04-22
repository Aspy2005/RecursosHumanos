package com.example.demo.servicios;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.AsistenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepositorio asistenciaRepository;

    public Asistencia registrarEntrada(Medico medico) {
        Date inicio = obtenerInicioDelDia();
        Date fin = obtenerFinDelDia();

        Optional<Asistencia> asistenciaExistente = asistenciaRepository.findByMedicoAndFechaBetween(medico, inicio, fin);
        if (asistenciaExistente.isPresent()) {
            throw new IllegalStateException("La hora de entrada ya fue registrada para este día.");
        }

        Asistencia nueva = new Asistencia();
        nueva.setMedico(medico);
        nueva.setFecha(new Date());
        nueva.setHoraEntrada(obtenerHoraActual());
        nueva.setHorasTrabajadas(0);

        return asistenciaRepository.save(nueva);
    }

    public Asistencia registrarSalida(Medico medico) {
        Date inicio = obtenerInicioDelDia();
        Date fin = obtenerFinDelDia();

        Asistencia asistencia = asistenciaRepository.findByMedicoAndFechaBetween(medico, inicio, fin)
            .orElseThrow(() -> new IllegalStateException("No se puede registrar la salida sin haber registrado la entrada."));

        if (asistencia.getHoraSalida() != null) {
            throw new IllegalStateException("La hora de salida ya fue registrada para este día.");
        }

        asistencia.setHoraSalida(obtenerHoraActual());
        asistencia.setHorasTrabajadas(calcularHoras(asistencia));
        return asistenciaRepository.save(asistencia);
    }

    public void borrarRegistro(Medico medico) {
        Date inicio = obtenerInicioDelDia();
        Date fin = obtenerFinDelDia();

        asistenciaRepository.findByMedicoAndFechaBetween(medico, inicio, fin)
            .ifPresentOrElse(
                asistenciaRepository::delete,
                () -> { throw new IllegalStateException("No hay registro de asistencia para borrar."); }
            );
    }

    public Optional<Asistencia> obtenerAsistenciaPorMedicoYFecha(Medico medico, Date fecha) {
        Date inicio = obtenerInicioDelDia();
        Date fin = obtenerFinDelDia();
        return asistenciaRepository.findByMedicoAndFechaBetween(medico, inicio, fin);
    }

    private float calcularHoras(Asistencia asistencia) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date entrada = sdf.parse(asistencia.getHoraEntrada());
            Date salida = sdf.parse(obtenerHoraActual());
            long ms = salida.getTime() - entrada.getTime();
            return ms / (1000f * 60 * 60);
        } catch (Exception e) {
            return 0;
        }
    }

    private String obtenerHoraActual() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    private Date obtenerInicioDelDia() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    private Date obtenerFinDelDia() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }
}
