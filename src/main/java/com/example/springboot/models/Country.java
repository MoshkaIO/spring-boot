package com.example.springboot.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; //должно сработать

import java.util.ArrayList;
import java.util.List;
//import javax.persistence.*;

@Entity
@Table(name = "countries")
@Access(AccessType.FIELD)
public class Country {

    public Country() { }
    public Country(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @Column(name = "name",nullable = false, unique = true)
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "country", targetEntity = Artist.class)
    public List artists = new ArrayList();
}
