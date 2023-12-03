package co.edu.unicauca.SIRENABackend.configs;

import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.ADMIN;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.COORDINADOR;
import static co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum.DOCENTE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unicauca.SIRENABackend.common.enums.ClassroomTypeEnum;
import co.edu.unicauca.SIRENABackend.common.enums.IncidenceTypeEnum;
import co.edu.unicauca.SIRENABackend.dtos.request.ClassroomTypeReq;
import co.edu.unicauca.SIRENABackend.dtos.request.IncidenceTypeReq;
import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import co.edu.unicauca.SIRENABackend.security.dtos.request.RoleReq;
import co.edu.unicauca.SIRENABackend.security.dtos.request.UserRegisterReq;
import co.edu.unicauca.SIRENABackend.security.services.AuthService;
import co.edu.unicauca.SIRENABackend.security.services.RoleService;
import co.edu.unicauca.SIRENABackend.services.ClassroomTypeService;
import co.edu.unicauca.SIRENABackend.services.IncidenceTypeService;

/**
 * Configuración de la aplicación que utiliza un {@link CommandLineRunner} para realizar acciones
 * de inicialización al arrancar la aplicación.
 */
@Configuration
public class CommandLineRunnerConfig {

    /**
     * Crea un bean {@link CommandLineRunner} que ejecuta acciones de inicialización al iniciar la aplicación.
     *
     * @param authService           Servicio de autenticación.
     * @param roleService           Servicio de roles.
     * @param classroomTypeService  Servicio de tipos de aulas.
     * @param incidenceTypeService  Servicio de tipos de incidencia.
     * @return Un objeto {@link CommandLineRunner}.
     */
    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService, RoleService roleService,
            ClassroomTypeService classroomTypeService, IncidenceTypeService incidenceTypeService) {
        return args -> {
            registroRoles(roleService);
            registroUsuarios(authService);
            registroSalones(classroomTypeService);
            registroTiposIncidencia(incidenceTypeService);
        };
    }
    /**
     * Registra los diferentes tipos de incidencia en el sistema.
     *
     * @param incidenceTypeService Servicio de tipos de incidencia.
     */
    private void registroTiposIncidencia(IncidenceTypeService incidenceTypeService) {
        for (IncidenceTypeEnum IncidenceType : IncidenceTypeEnum.values()) {
            try {
                incidenceTypeService.saveIncidenceTypes(IncidenceTypeReq.builder().name(IncidenceType).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el tipo de incidencia");
            }
        }
    }

    /**
     * Registra los diferentes tipos de aulas en el sistema.
     *
     * @param classroomTypeService Servicio de tipos de aulas.
     */
    private void registroSalones(ClassroomTypeService classroomTypeService) {
        for (ClassroomTypeEnum classroom : ClassroomTypeEnum.values()) {
            try {
                classroomTypeService.saveClassroomType(ClassroomTypeReq.builder().name(classroom).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el salon");
            }
        }
    }

    /**
     * Registra los diferentes roles en el sistema.
     *
     * @param roleService Servicio de roles.
     */
    private void registroRoles(RoleService roleService) {
        for (RoleEnum Role : RoleEnum.values()) {
            try {
                roleService.saveRole(RoleReq.builder().name(Role).build());
            } catch (Exception e) {
                System.out.println("Error al ingresar el rol");
            }
        }
    }

    /**
     * Registra usuarios de ejemplo en el sistema, incluyendo un administrador, un coordinador y un docente.
     *
     * @param authService Servicio de autenticación.
     */
    private void registroUsuarios(AuthService authService) {

        try {
            var adminUser = UserRegisterReq.builder()
                    .usr_name("admin")
                    .usr_firstname("admin")
                    .usr_lastname("admin")
                    .usr_email("admin@unicauca.edu.co")
                    .usr_password("admin")
                    .usr_role(ADMIN)
                    .build();
            System.out.println("Admin Token: " + authService.register(adminUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario admin ya exsite");
        }

        try {
            var coordinadorTestUser = UserRegisterReq.builder()
                    .usr_name("coordinadorTest")
                    .usr_firstname("coordinadorTest")
                    .usr_lastname("coordinadorTest")
                    .usr_email("coordinadorTest@unicauca.edu.co")
                    .usr_password("coordinadorTest")
                    .usr_role(COORDINADOR)
                    .build();
            System.out.println("Coordinador Token: " + authService.register(coordinadorTestUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario coordinadorTest ya existe");
        }

        try {
            var docenteTestUser = UserRegisterReq.builder()
                    .usr_name("docenteTest")
                    .usr_firstname("docenteTest")
                    .usr_lastname("docenteTest")
                    .usr_email("docenteTest@unicauca.edu.co")
                    .usr_password("docenteTest")
                    .usr_role(DOCENTE)
                    .build();
            System.out.println("Docente Token: " + authService.register(docenteTestUser).getAccesToken());
        } catch (Exception e) {
            System.out.println("El usuario docenteTest ya existe");
        }
    }

}
