package org.northcoders.security_and_authentication.model;

import jakarta.persistence.*;

@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    public String id;

    @Column
    public String name;

    @Column
    public String githubUsername;

}
