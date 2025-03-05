package co.edu.uniandes.dse.parcialprueba.services;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteConsultaMedicaService {

    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void addConsulta(Long pacienteId, Long consultaMedicaId) throws EntityNotFoundException, IllegalOperationException{
        PacienteEntity paciente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new IllegalArgumentException("El paciente no existe"));
        ConsultaMedicaEntity consulta = consultaMedicaRepository.findById(consultaMedicaId)
            .orElseThrow(() -> new IllegalArgumentException("La consulta medica no existe"));

        List<ConsultaMedicaEntity> consultas = paciente.getConsultaMedica();

        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getFecha() == consulta.getFecha()) {
                throw new IllegalArgumentException("No es posible agregar la consulta porque ya hay una consulta en la fecha dada");
            }
        }
        paciente.getConsultaMedica().add(consulta);
        pacienteRepository.save(paciente);
    }

    @Transactional
    public List<ConsultaMedicaEntity> getConsultasProgramadas(Long pacienteId) throws EntityNotFoundException, IllegalOperationException {
        PacienteEntity paciente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new IllegalArgumentException("El paciente no existe"));
        
        List<ConsultaMedicaEntity> consultasProgramadas = new ArrayList<>();
        List<ConsultaMedicaEntity> consultasPaciente = paciente.getConsultaMedica();
        Date fecha = new Date(5);

        for (int i = 0; i < consultasPaciente.size(); i++) {
            if (consultasPaciente.get(i).getFecha().after(fecha)){
                consultasProgramadas.add(consultasPaciente.get(i));
            }
        }
        return consultasProgramadas;
    }
}
