package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({ConsultaMedicaService.class, PacienteService.class})

public class PacienteConsultaMedicaTest {
    @Autowired
    private PacienteConsultaMedicaService pacienteConsultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<ConsultaMedicaEntity> consultas = new ArrayList<>();
    private List<PacienteEntity> pacientes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("DELETE FROM PacienteEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM ConsultaMedicaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConsultaMedicaEntity consultaMedicaEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
            entityManager.persist(consultaMedicaEntity);
            consultas.add(consultaMedicaEntity);
        }

        for (int i = 0; i < 3; i++) {
            PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
            entityManager.persist(pacienteEntity);
            pacientes.add(pacienteEntity);
        }
    }

    @Test
    void testAddConsulta() throws IllegalOperationException, EntityNotFoundException {
        PacienteEntity paciente = pacientes.get(0);
        ConsultaMedicaEntity consultaMedicaEntity = consultas.get(0);
        pacienteConsultaMedicaService.addConsulta(paciente.getId(), consultaMedicaEntity.getId());
        assertTrue(paciente.getConsultaMedica().contains(consultaMedicaEntity));
    }
}
