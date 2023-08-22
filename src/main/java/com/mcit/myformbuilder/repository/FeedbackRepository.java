package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.FeedbackHistory;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<FeedbackHistory, Long> {
}
