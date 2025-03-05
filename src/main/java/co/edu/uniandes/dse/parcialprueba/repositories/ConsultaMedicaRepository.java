package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import java.util.List;
import java.util.Date;


public interface ConsultaMedicaRepository extends JpaRepository<ConsultaMedicaEntity, Long> {
    List<ConsultaMedicaEntity> findByFecha(Date fecha);
    List<ConsultaMedicaEntity> findByCausa(String causa);
}