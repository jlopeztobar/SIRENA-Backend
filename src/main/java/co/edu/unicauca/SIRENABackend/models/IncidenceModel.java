package co.edu.unicauca.SIRENABackend.models;

import co.edu.unicauca.SIRENABackend.security.models.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidences")
public class IncidenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_int_id", unique = true)
    private Integer id;

    @Column(name = "ins_name", nullable = false, length = 40)
    private String name;

    // Es la mismas que reserva == rsv_codigo_insidencias
    @Column(name = "ins_key", nullable = false, length = 40)
    private String key;

    // Es la misma que user ID == rsv_usr_int_id
    @ManyToOne
    @JoinColumn(name = "ins_teacher_name", nullable = false)
    private UserModel teacherName;

    @ManyToOne
    @JoinColumn(name = "ins_insidencias", nullable = false)
    private BookingModel insidencias;

    @ManyToOne
    @JoinColumn(name = "ins_type", nullable = false)
    private IncidenceTypeModel insidenciaType;
}
