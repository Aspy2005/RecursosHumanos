package com.example.demo.servicios;

import com.example.demo.DTO.ReporteMedicoDTO;
import com.example.demo.modelo.Asistencia;
import com.example.demo.modelo.Evaluacion;
import com.example.demo.modelo.VacacionesPermisos;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@Service
public class PdfGeneratorService {

    public byte[] generarPdfReporte(String tipo, ReporteMedicoDTO datos) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            document.add(new Paragraph("Reporte de " + tipo.toUpperCase(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph(" ")); // Espacio

            switch (tipo.toLowerCase()) {
            case "asistencia":
                PdfPTable asistenciaTable = new PdfPTable(3);
                asistenciaTable.addCell("Fecha");
                asistenciaTable.addCell("Hora Entrada");
                asistenciaTable.addCell("Hora Salida");

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

                for (Asistencia a : datos.getAsistencias()) {
                    asistenciaTable.addCell(dateFormatter.format(a.getFecha()));
                    asistenciaTable.addCell(timeFormatter.format(a.getHoraEntrada()));
                    asistenciaTable.addCell(timeFormatter.format(a.getHoraSalida()));
                }

                document.add(asistenciaTable);
                break;


                case "desempeno":
                    PdfPTable evaluacionTable = new PdfPTable(3);
                    evaluacionTable.addCell("Fecha");
                    evaluacionTable.addCell("Calificación Desempeño");
                    evaluacionTable.addCell("Observaciones");

                    for (Evaluacion e : datos.getEvaluaciones()) {
                        evaluacionTable.addCell(e.getFecha().toString());
                        evaluacionTable.addCell(String.valueOf(e.getPuntajeDesempeno()));
                        evaluacionTable.addCell(e.getObservaciones());
                    }

                    document.add(evaluacionTable);
                    break;

                case "permisos":
                    PdfPTable permisoTable = new PdfPTable(4);
                    permisoTable.addCell("Fecha Inicio");
                    permisoTable.addCell("Fecha Fin");
                    permisoTable.addCell("Motivo");
                    permisoTable.addCell("Estado");

                    for (VacacionesPermisos p : datos.getPermisos()) {
                        permisoTable.addCell(p.getFechaInicio().toString());
                        permisoTable.addCell(p.getFechaFin().toString());
                        permisoTable.addCell(p.getMotivo());
                        permisoTable.addCell(p.getEstado());
                    }

                    document.add(permisoTable);
                    break;

                default:
                    document.add(new Paragraph("Tipo de reporte no reconocido."));
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generando el PDF", e);
        }
    }
}
