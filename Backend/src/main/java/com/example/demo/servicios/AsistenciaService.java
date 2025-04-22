package com.example.demo.servicios;

import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Medico;
import com.example.demo.repositorio.AsistenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        nueva.setHoraEntrada(new Date());
        nueva.setHorasTrabajadas(0);

        return asistenciaRepository.save(nueva);
    }

    public String registrarSalida(int idMedico) {
        Medico medico = new Medico();
        medico.setIdMedico(idMedico);

        Date inicio = obtenerInicioDelDia();
        Date fin = obtenerFinDelDia();

        Asistencia asistencia = asistenciaRepository.findByMedicoAndFechaBetween(medico, inicio, fin)
            .orElseThrow(() -> new IllegalStateException("No se encontró un registro de entrada."));

        if (asistencia.getHoraSalida() != null) {
            throw new IllegalStateException("Ya se ha registrado la salida.");
        }

        Date horaSalida = new Date();
        asistencia.setHoraSalida(horaSalida);

        long diferenciaMs = horaSalida.getTime() - asistencia.getHoraEntrada().getTime();
        float horasTrabajadas = diferenciaMs / (1000f * 60 * 60);
        asistencia.setHorasTrabajadas(horasTrabajadas);

        asistenciaRepository.save(asistencia);

        long horas = (long) horasTrabajadas;
        long minutos = (long) ((horasTrabajadas - horas) * 60);

        return String.format("Hora de salida registrada correctamente. Tiempo trabajado: %d horas y %d minutos.", horas, minutos);
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
