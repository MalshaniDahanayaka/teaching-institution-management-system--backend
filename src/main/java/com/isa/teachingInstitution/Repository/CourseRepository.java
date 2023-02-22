package com.isa.teachingInstitution.Repository;

import com.isa.teachingInstitution.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
