package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "empty_form")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class EmptyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empty_form_id")
    private Long emptyFormId;

    @NonNull
    @Column(name = "form_title", nullable = false)
    private String formTitle;

    @NonNull
    @Column(name = "json_text", nullable = false)
    private String JSONText;

    @NonNull
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserData userData;

    @JsonIgnore
    @OneToMany(mappedBy = "emptyForm", cascade = CascadeType.ALL)
    private Set<FilledForm> filledForms;

}
