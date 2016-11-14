package com.botscrew.model;

import com.botscrew.enums.UserState;
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
    private UserState state;
}
