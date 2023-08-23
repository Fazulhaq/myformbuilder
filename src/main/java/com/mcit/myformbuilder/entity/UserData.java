package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user_data")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NonNull
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
}
