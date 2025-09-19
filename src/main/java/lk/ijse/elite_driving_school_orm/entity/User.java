package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String username;
    private String password;
    private Roles role;
}
