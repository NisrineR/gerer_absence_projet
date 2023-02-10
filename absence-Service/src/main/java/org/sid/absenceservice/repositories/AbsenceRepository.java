package org.sid.absenceservice.repositories;

import org.sid.absenceservice.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
@RepositoryRestResource
public interface AbsenceRepository extends JpaRepository<Absence,Long> {

    @RestResource(path = "/byStudentID")
    List<Absence> findByStudentId(@Param("studentId") Long studentID);
}
