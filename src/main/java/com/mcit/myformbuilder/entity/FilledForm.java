package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "filled_form")
@Setter
@Getter
@NoArgsConstructor
public class FilledForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filled_form_id")
    private Long id;

    @NotBlank(message = "Form title should not be null")
    @Column(name = "form_title")
    private String formTitle;

    @NotBlank(message = "JSON text should not be null")
    @Column(name = "json_text", nullable = false)
    private String jsonText;

    @PastOrPresent(message = "Filling date of form must be in the past or present!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "filled_date", nullable = false)
    private LocalDate filledDate;

    @NotNull(message = "Status of form should not be null")
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

    public FilledForm(String formTitle, String jsonText, LocalDate filledDate, FormStatus formStatus, UserData userData, EmptyForm emptyForm) {
        this.id = this.getId();
        this.formTitle = formTitle;
        this.jsonText = jsonText;
        this.filledDate = filledDate;
        this.formStatus = formStatus;
        this.userData = userData;
        this.emptyForm = emptyForm;
    }
}
