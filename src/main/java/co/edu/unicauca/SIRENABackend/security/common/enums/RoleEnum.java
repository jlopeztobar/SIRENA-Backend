package co.edu.unicauca.SIRENABackend.security.common.enums;

import lombok.RequiredArgsConstructor;

/**
 * Enumeración que representa roles relacionados con la seguridad.
 * Cada rol tiene una correspondencia con una cadena específica.
 */
@RequiredArgsConstructor
public enum RoleEnum {

    ADMIN,
    COORDINADOR,
    DOCENTE;
}
