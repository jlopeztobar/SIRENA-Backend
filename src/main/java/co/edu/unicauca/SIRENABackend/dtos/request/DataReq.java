package co.edu.unicauca.SIRENABackend.dtos.request;

import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) que representa la solicitud para crear
 * datos por defecto
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataReq {
    @JsonProperty("data_buildings")
    private List<BuildingModel> buildingsList;
    @JsonProperty("data_faculties")
    private List<FacultyRequest> facultiesList;
    @JsonProperty("data_programs")
    private List<ProgramModel> programsList;
    @JsonProperty("data_classrooms")
    private List<ClassroomModel> classroomsList;
    @JsonProperty("data_incidences")
    private List<IncidenceReq> incidencesList;
    @JsonProperty("data_bookings")
    private List<BookingReq> bookingsList;
}
