package com.mcit.myformbuilder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
@Table(name = "feedback_history")
@Setter
@Getter
@NoArgsConstructor
public class FeedbackHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @NotBlank(message = "Feedback should not be null")
    @Column(name = "feedback_text", nullable = false)
    private String feedbackText;

    @PastOrPresent(message = "Feedback date must be in the past or present!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public FeedbackHistory(String feedbackText, LocalDate feedbackDate, UserData userData, FilledForm filledForm) {
        this.id = this.getId();
        this.feedbackText = feedbackText;
        this.feedbackDate = feedbackDate;
        this.userData = userData;
        this.filledForm = filledForm;
    }
}
