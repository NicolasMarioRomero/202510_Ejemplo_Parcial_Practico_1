package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity crearPaciente(PacienteEntity paciente) throws EntityNotFoundException, IllegalOperationException {
        log.info("creando nuevo paciente...");
        if (paciente == null || paciente.getNombre() == null || paciente.getEdad() == null || paciente.getCorreo() == null || paciente.getCelular() == null) {
            throw new IllegalArgumentException("Los campos del paciente no pueden ser nulos");
        }
        log.info("paciente creado con exito!");
        return pacienteRepository.save(paciente);
    }
}
