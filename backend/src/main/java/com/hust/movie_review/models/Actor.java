package com.hust.movie_review.models;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "actors")
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;
    @Column(name = "year_birthday")
    private int yearBirthday;
    @Column(name = "gender")
    private String gender;

    @ManyToMany(mappedBy = "actors")
    Set<Movie> movies;
}
