package com.aditya.major.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;

}


