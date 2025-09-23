package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;
import lk.ijse.elite_driving_school_orm.dto.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userID;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
