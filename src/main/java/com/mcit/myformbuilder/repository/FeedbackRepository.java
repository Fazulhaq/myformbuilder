package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.FeedbackHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FeedbackRepository extends CrudRepository<FeedbackHistory, Long> {
    Set<FeedbackHistory> findByFilledFormId(Long formId);
    Set<FeedbackHistory> findByFilledFormIdAndUserDataId(Long formId, Long userId);
}
