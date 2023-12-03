package co.edu.unicauca.SIRENABackend.controllers;

import co.edu.unicauca.SIRENABackend.dtos.request.BookingReq;
import co.edu.unicauca.SIRENABackend.dtos.request.DataReq;
import co.edu.unicauca.SIRENABackend.dtos.request.FacultyRequest;
import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceReq;
import co.edu.unicauca.SIRENABackend.models.BuildingModel;
import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import co.edu.unicauca.SIRENABackend.models.FacultyModel;
import co.edu.unicauca.SIRENABackend.models.ProgramModel;
import co.edu.unicauca.SIRENABackend.security.dtos.response.UserRegisterRes;
import co.edu.unicauca.SIRENABackend.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controlador REST que maneja las operaciones relacionadas con la carga de datos
 * (bookings).
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
@Tag(name = "Data controller", description = "Endpoints para quemar datos")
public class DataController {
    @Autowired
    private final BookingService bookingService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private IncidenceService incidenceService;
    @Autowired
    private ProgramService programService;

    @Operation(summary = "Registra los datos cargados a partir de un json", description = "Crea datos por defecto en el sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Datos cargados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se creo la reservas")
    })
    @PostMapping()
    public ResponseEntity<String> loadData(@RequestBody DataReq dataRequest) {
        for(BuildingModel building:dataRequest.getBuildingsList())
        {
            if(buildingService.saveBuilding(building)==null)
                return new ResponseEntity<String>("Error en el edificio "+building.getName(), HttpStatus.BAD_REQUEST);
            System.out.println("Edifcio "+building.getName()+" creado");
        }
        for(FacultyRequest faculty:dataRequest.getFacultiesList())
        {
            // Busca el edificio por su id
            Optional<BuildingModel> buildingOptional = buildingService.getBuildingById(faculty.getBuildingId());

            if (buildingOptional.isPresent()) {
                BuildingModel building = buildingOptional.get();

                // Crea la nueva facultad
                FacultyModel facultyModel = new FacultyModel();
                facultyModel.setName(faculty.getName());
                facultyModel.setBuilding(building);

                // Guarda la facultad
                FacultyModel savedFaculty = facultyService.saveFaculties(facultyModel);
                if(savedFaculty==null)
                {
                    return new ResponseEntity<>("Error en la facultad "+faculty.getName(),HttpStatus.BAD_REQUEST);
                }

                System.out.println("Facultad "+facultyModel.getName()+" creada");
            } else {
                return new ResponseEntity<>("Error en la facultad "+faculty.getName()+" edifico no encontrado",HttpStatus.NOT_FOUND);
            }
        }
        for(ProgramModel program:dataRequest.getProgramsList())
        {
            if(programService.saveProgram(program)==null)
                return new ResponseEntity<String>("Error en el programa "+program.getName(), HttpStatus.BAD_REQUEST);
            System.out.println("Programa "+program.getName()+" creado");
        }
        for(ClassroomModel classroom:dataRequest.getClassroomsList())
        {
            if(classroomService.save(classroom)==null)
                return new ResponseEntity<String>("Error en el salon "+classroom.getName(), HttpStatus.BAD_REQUEST);
            System.out.println("Salon "+classroom.getName()+" creado");
        }
        for(IncidenceReq incidence:dataRequest.getIncidencesList())
        {
            if(incidenceService.saveIncidence(incidence)==null)
                return new ResponseEntity<String>("Error en la incidencia "+incidence.getName(), HttpStatus.BAD_REQUEST);
            System.out.println("Incidencia "+incidence.getName()+" creada");
        }
        for(BookingReq booking:dataRequest.getBookingsList())
        {
            booking.setFechaSolicitud(LocalDateTime.now());
            System.out.println(bookingService.crearBooking(booking).getBody());
        }
        return new ResponseEntity<String>("Datos cargados correctamente",HttpStatus.CREATED);
    }
}
