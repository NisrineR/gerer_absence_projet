package org.sid.absenceservice.model;

import lombok.Data;

@Data
public class Course {
    private Long id;
    private  String nom;
    private String professorId;
}
