package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user_data")
@Setter
@Getter
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "First Name should not be null")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name should not be null")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "Email should not be null")
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Password should not be null")
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "User type should not be null")
    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotBlank(message = "Organization should not be null")
    @Column(name = "organization", nullable = false)
    private String organization;

    @JsonIgnore
    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
    private Set<EmptyForm> emptyForms;

    @JsonIgnore
    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
    private Set<FilledForm> filledForms;

    @JsonIgnore
    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
    private Set<FeedbackHistory> feedbackHistories;

    public UserData(String firstName, String lastName, String email, String password, UserType userType, String organization) {
        this.id = this.getId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.organization = organization;
    }
}
