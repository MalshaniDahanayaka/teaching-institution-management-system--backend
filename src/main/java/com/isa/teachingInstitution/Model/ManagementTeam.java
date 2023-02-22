package com.isa.teachingInstitution.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="managementTeam")
public class ManagementTeam {
    @Id
    private String username;
    private String password;

}
