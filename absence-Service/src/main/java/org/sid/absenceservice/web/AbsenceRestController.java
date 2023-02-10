package org.sid.absenceservice.web;

import org.sid.absenceservice.entities.Absence;
import org.sid.absenceservice.repositories.AbsenceRepository;
import org.sid.absenceservice.services.CourseRestClient;
import org.sid.absenceservice.services.StudentRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbsenceRestController {
    AbsenceRepository absenceRepository;
    CourseRestClient courseRestClient;
    StudentRestClient studentRestClient;

    public AbsenceRestController(AbsenceRepository absenceRepository, CourseRestClient courseRestClient, StudentRestClient studentRestClient) {
        this.absenceRepository = absenceRepository;
        this.courseRestClient = courseRestClient;
        this.studentRestClient = studentRestClient;
    }


    @GetMapping("/absences/{id}")
    public Absence absenceById(@PathVariable Long id){
        Absence abs=absenceRepository.findById(id).get();
        abs.setStudent(studentRestClient.findStudentById(abs.getStudentId()));
        abs.setCourse(courseRestClient.findCourseByID(abs.getCourseId()));
        return abs;
    }

}