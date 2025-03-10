package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EspecialidadEntity extends BaseEntity{
    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "especialidades")
    @PodamExclude
    private Set<MedicEntity> medicos;
}
