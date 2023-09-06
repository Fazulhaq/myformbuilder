package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.FeedbackHistory;
import com.mcit.myformbuilder.entity.FilledForm;
import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.exception.EntityNotFoundException;
import com.mcit.myformbuilder.exception.FeedbackNotFoundException;
import com.mcit.myformbuilder.exception.FormFeedbackNotFoundException;
import com.mcit.myformbuilder.repository.FeedbackRepository;
import com.mcit.myformbuilder.repository.FilledFormRepository;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class FeedbackHistoryService {

    FeedbackRepository feedbackRepository;
    UserDataRepository userDataRepository;
    FilledFormRepository filledFormRepository;

    public FeedbackHistory getFeedback(Long feedbackId) {
        Optional<FeedbackHistory> feedback = feedbackRepository.findById(feedbackId);
        return foundFeedback(feedback, feedbackId);
    }

    public List<FeedbackHistory> getFeedbacks(){
        return (List<FeedbackHistory>) feedbackRepository.findAll();
    }

    public FeedbackHistory saveFeedback(FeedbackHistory feedback, Long userId, Long formId) {
        UserData userData = UserDataService.unwrapUserData(userDataRepository.findById(userId), userId);
        FilledForm filledForm = FilledFormService.testedFilledForm(filledFormRepository.findById(formId), formId);
        feedback.setUserData(userData);
        feedback.setFilledForm(filledForm);
        return feedbackRepository.save(feedback);
    }

    public FeedbackHistory updateFeedback(FeedbackHistory newFeedback, Long feedbackId) {
        Optional<FeedbackHistory> feedback = feedbackRepository.findById(feedbackId);
        FeedbackHistory foundFeedback = foundFeedback(feedback, feedbackId);
        foundFeedback.setFeedbackText(newFeedback.getFeedbackText());
        foundFeedback.setFeedbackDate(newFeedback.getFeedbackDate());
        return feedbackRepository.save(foundFeedback);
    }

    public void deleteFeedback(Long feedbackId) {
        Optional<FeedbackHistory> feedback = feedbackRepository.findById(feedbackId);
        FeedbackHistory foundFeedback = foundFeedback(feedback, feedbackId);
        feedbackRepository.delete(foundFeedback);
    }

    public Set<FeedbackHistory> getFormFeedbacks(Long formId) {
        Optional<FilledForm> filledForm = filledFormRepository.findById(formId);
        FilledFormService.testedFilledForm(filledForm, formId);
        Set<FeedbackHistory> feedbacks = feedbackRepository.findByFilledFormId(formId);
        if (feedbacks.isEmpty()) throw new FormFeedbackNotFoundException(formId);
        return feedbacks;
    }

    public Set<FeedbackHistory> getFeedbacksByFormAndUser(Long formId, Long userId) {
        Optional<FilledForm> filledForm = filledFormRepository.findById(formId);
        Optional<UserData> userData = userDataRepository.findById(userId);
        FilledFormService.testedFilledForm(filledForm, formId);
        UserDataService.unwrapUserData(userData, userId);
        Set<FeedbackHistory> feedbacks = feedbackRepository.findByFilledFormIdAndUserDataId(formId, userId);
        if (feedbacks.isEmpty()) throw new FeedbackNotFoundException(formId, userId);
        return feedbacks;
    }

    static FeedbackHistory foundFeedback(Optional<FeedbackHistory> entity, Long feedbackId){
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(feedbackId, FeedbackHistory.class);
    }
}
