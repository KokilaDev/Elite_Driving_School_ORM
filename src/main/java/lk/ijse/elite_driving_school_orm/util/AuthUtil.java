package lk.ijse.elite_driving_school_orm.util;

import lk.ijse.elite_driving_school_orm.dto.Roles;
import lk.ijse.elite_driving_school_orm.dto.UserDTO;

public class AuthUtil {
    private static UserDTO userDTO;

    public static UserDTO getUserDTO() {
        return userDTO;
    }

    public static void setUserDTO(UserDTO userDTO) {
        AuthUtil.userDTO = userDTO;
    }

    public static boolean isAdmin() {
        if (userDTO != null) {
            return userDTO.getRole().equals(Roles.ADMIN);
        }
        return false;
    }
}
