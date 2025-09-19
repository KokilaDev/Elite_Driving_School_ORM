package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String password;
    private Roles role;
}
