package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.FilledForm;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface FilledFormRepository extends CrudRepository<FilledForm, Long> {
    Set<FilledForm> findFilledFormByUserDataId(Long userId);
    Set<FilledForm> findFilledFormByEmptyFormId(Long emptyFormId);
}
