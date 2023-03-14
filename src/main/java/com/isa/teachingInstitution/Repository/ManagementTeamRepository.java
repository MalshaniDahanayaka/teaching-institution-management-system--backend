package com.isa.teachingInstitution.Repository;

import com.isa.teachingInstitution.Model.ManagementTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementTeamRepository extends JpaRepository<ManagementTeam, String> {
}
