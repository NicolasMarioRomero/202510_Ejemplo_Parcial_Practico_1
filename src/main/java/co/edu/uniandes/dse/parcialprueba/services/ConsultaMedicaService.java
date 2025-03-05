package co.edu.uniandes.dse.parcialprueba.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaMedicaService {

    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity createConsultaMedica(ConsultaMedicaEntity consultaMedica) throws EntityNotFoundException, IllegalOperationException{
        Date fecha = new Date(5);
        if (consultaMedica.getFecha().after(fecha) == false){
            throw new IllegalArgumentException("La fecha es invalida");
        }
        if (consultaMedica.getFecha() == null || consultaMedica.getCausa() == null || consultaMedica == null) {
            throw new IllegalArgumentException("Los campos de la consulta medica no pueden ser nulos");
        }
        return consultaMedicaRepository.save(consultaMedica);
    }
}
