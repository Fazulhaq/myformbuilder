package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "empty_form")
@Setter
@Getter
@NoArgsConstructor
public class EmptyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empty_form_id")
    private Long id;

    @NotBlank(message = "Form title should not be Null")
    @Column(name = "form_title", nullable = false)
    private String formTitle;

    @NotBlank(message = "JSON text should not be null")
    @Column(name = "json_text", nullable = false)
    private String jsonText;

    @PastOrPresent(message = "Publish date must be in the past or present!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserData userData;

    @JsonIgnore
    @OneToMany(mappedBy = "emptyForm", cascade = CascadeType.ALL)
    private Set<FilledForm> filledForms;

    public EmptyForm(String formTitle, String jsonText, LocalDate publishDate, UserData userData) {
        this.id = this.getId();
        this.formTitle = formTitle;
        this.jsonText = jsonText;
        this.publishDate = publishDate;
        this.userData = userData;
    }
}
