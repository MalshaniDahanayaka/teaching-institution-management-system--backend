package com.isa.teachingInstitution.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="managementTeam")
@PrimaryKeyJoinColumn(name="username")
public class ManagementTeam extends User{

    @Column(name="admin_id")
    private String adminID;

    public ManagementTeam(String firstName, String lastName, String username, String email, String password,
                          String role, String adminID) {
        super(firstName, lastName, username, email, password, role);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
