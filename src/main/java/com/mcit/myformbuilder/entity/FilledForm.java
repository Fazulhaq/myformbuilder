package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "filled_form")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilledForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filled_form_id")
    private Long filledFormId;

    @NonNull
    @Column(name = "form_title")
    private String formTitle;

    @NonNull
    @Column(name = "json_text", nullable = false)
    private String JSONText;

    @NonNull
    @Column(name = "filled_date", nullable = false)
    private LocalDate filledDate;

    @NonNull
    @Column(name = "form_status")
    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserData userData;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "empty_form_id", referencedColumnName = "empty_form_id")
    private EmptyForm emptyForm;

    @JsonIgnore
    @OneToMany(mappedBy = "filledForm", cascade = CascadeType.ALL)
    private Set<FeedbackHistory> feedbackHistories;

}
