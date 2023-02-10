package org.sid.absenceservice.services;

import org.sid.absenceservice.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="COURSE-SERVICE")
public interface CourseRestClient {

    @GetMapping(path = "/courses/{id}")
    Course findCourseByID(@PathVariable Long id);

    @GetMapping("/courses")
    PagedModel<Course> getAllCourses();
}
