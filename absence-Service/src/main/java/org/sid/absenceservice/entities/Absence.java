package org.sid.absenceservice.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.absenceservice.model.Course;
import org.sid.absenceservice.model.Student;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Absence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Long studentId ;
    private  Long courseId ;
    private Date date;
    @Transient
    private Student student;
    @Transient
    private Course course;

}
