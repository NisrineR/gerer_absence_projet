package org.sid.courseservice.model;

import lombok.Data;

@Data
public class Professor {
    private Long id;
    private String nom;
    private  String prenom;
    private String departement;
}
