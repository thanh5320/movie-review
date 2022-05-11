package com.hust.movie_review.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)
@Table(name = "countries")
@Entity
public class Country {
    @Id
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "continent", nullable = false)
    private String continent;
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private Set<Actor> actors;
}
