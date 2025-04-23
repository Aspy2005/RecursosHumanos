package com.example.demo.repositorio;

import com.example.demo.DTO.MedicoDTO;
import com.example.demo.modelo.Medico;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Integer> {
    boolean existsById(Integer id);
    List<Medico> findByEstado(String estado);



    
    @Query("SELECT DISTINCT new com.example.demo.DTO.MedicoDTO(m.idMedico, m.nombreMedico, m.apellidoMedico) " +
    	       "FROM Medico m " +
    	       "WHERE EXISTS (SELECT a FROM Asistencia a WHERE a.medico = m) " +
    	       "AND EXISTS (SELECT p FROM PQR p WHERE p.medico = m)")
    	List<MedicoDTO> findMedicosConAsistenciaYPQR();
    
    @Query("SELECT DISTINCT a.medico FROM Asistencia a")
    List<Medico> findMedicosConAsistencia();

    @Query("SELECT DISTINCT p.medico FROM PQR p")
    List<Medico> findMedicosConPqr();

    
    Optional<Medico> findByIdMedico(int idMedico);
    
    boolean existsByIdMedico(int idMedico);  // Este es el método que necesitas


}
