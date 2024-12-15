package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    private UserRolesEnum name;

    public Role(UserRolesEnum name) {
        this.name = name;
    }

    protected Role() {}

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public UserRolesEnum getName() {
        return name;
    }

    public void setName(UserRolesEnum name) {
        this.name = name;
    }
}
