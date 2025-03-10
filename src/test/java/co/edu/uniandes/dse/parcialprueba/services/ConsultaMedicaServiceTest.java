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
@Import(ConsultaMedicaService.class)
public class ConsultaMedicaServiceTest {

    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<ConsultaMedicaEntity> consultas = new ArrayList<>();
    private PacienteEntity paciente;

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

        PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
        entityManager.persist(pacienteEntity);
        paciente = pacienteEntity;
    }

    @Test
    void testCreateConsulta() throws IllegalOperationException, EntityNotFoundException{
        ConsultaMedicaEntity consultaMedicaEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
        ConsultaMedicaEntity result = consultaMedicaService.createConsultaMedica(consultaMedicaEntity);
        assertNotNull(result);
        assertEquals(consultaMedicaEntity.getFecha(), result.getFecha());
        assertEquals(consultaMedicaEntity.getCausa(), result.getCausa());
    }

}
