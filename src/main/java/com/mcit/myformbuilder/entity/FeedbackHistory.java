package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "feedback_history")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @NonNull
    @Column(name = "feedback_text", nullable = false)
    private String feedbackText;

    @NonNull
    @Column(name = "feedback_date", nullable = false)
    private LocalDate feedbackDate;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserData userData;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "filled_form_id", referencedColumnName = "filled_form_id")
    private FilledForm filledForm;
}
