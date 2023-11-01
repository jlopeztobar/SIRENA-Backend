package co.edu.unicauca.SIRENABackend.security.dtos.request;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {

    protected String usr_name;

    protected String usr_firstname;

    protected String usr_lastname;

    protected String usr_email;

    protected String usr_password;

    protected RoleEnum usr_role;
}