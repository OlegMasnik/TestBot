package com.botscrew.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "user")
public class FbUser {

    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("first_name")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("last_name")
    @Column(name = "last_name")
    private String lastName;

    private String gender;
    @JsonIgnore
    @Column(name = "is_active")
    private Boolean isActive;

    public FbUser() {}

    public FbUser(String id, String firstName, String lastName, String gender, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.isActive = isActive;
    }
}
