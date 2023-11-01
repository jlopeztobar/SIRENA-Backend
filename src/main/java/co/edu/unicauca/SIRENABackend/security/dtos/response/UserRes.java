package co.edu.unicauca.SIRENABackend.security.dtos.response;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    protected Integer usr_id;

    protected String usr_name;

    protected String usr_firstname;

    protected String usr_lastname;

    protected String usr_email;

    protected RoleEnum usr_role;
}
