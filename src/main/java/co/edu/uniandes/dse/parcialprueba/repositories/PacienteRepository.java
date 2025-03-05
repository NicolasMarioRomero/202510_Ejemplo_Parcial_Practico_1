package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import java.util.List;


public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    List<PacienteEntity> findByNombre(String nombre);
    List<PacienteEntity> findByEdad(Integer edad);
    List<PacienteEntity> findByCorreo(String correo);
    List<PacienteEntity> findByCelular(Integer celular);
} 