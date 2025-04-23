package com.example.demo.controlador;

import com.example.demo.DTO.ReporteMedicoDTO;
import com.example.demo.servicios.PdfGeneratorService;
import com.example.demo.servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
public class ReporteControlador {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    // Endpoint para obtener datos JSON del reporte
    @GetMapping("/{cedula}/{tipo}")
    public ResponseEntity<ReporteMedicoDTO> obtenerReporte(
            @PathVariable String cedula,
            @PathVariable String tipo) {

        try {
            ReporteMedicoDTO reporte = reporteService.generarReporte(cedula, tipo);

            boolean vacio = (tipo.equalsIgnoreCase("asistencia") && reporte.getAsistencias().isEmpty()) ||
                            (tipo.equalsIgnoreCase("desempeno") && reporte.getEvaluaciones().isEmpty()) ||
                            (tipo.equalsIgnoreCase("permisos") && reporte.getPermisos().isEmpty());

            if (vacio) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para obtener PDF del reporte
    @GetMapping("/pdf/{cedula}/{tipo}")
    public ResponseEntity<byte[]> obtenerReportePdf(
            @PathVariable String cedula,
            @PathVariable String tipo) {

        ReporteMedicoDTO reporte = reporteService.generarReporte(cedula, tipo);
        byte[] pdfBytes = pdfGeneratorService.generarPdfReporte(tipo, reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte-" + tipo + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }
    
    @GetMapping("/validar/{cedula}")
    public ResponseEntity<Boolean> validarMedico(@PathVariable String cedula) {
        boolean existe = reporteService.existeMedicoPorCedula(cedula);
        return ResponseEntity.ok(existe);
    }


}
