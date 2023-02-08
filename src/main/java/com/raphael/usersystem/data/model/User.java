package com.raphael.usersystem.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Document(collection = "User")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Validated

public class User {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private String gender;
    private String nationality;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dateid")
    private Date dateOfBirth;
    private Integer age; // how to get age from date of birth


    public User(String firstName, String lastName, String username, String email, String password,
                String phoneNumber, Address address, String gender, Date dateOfBirth, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }
}
