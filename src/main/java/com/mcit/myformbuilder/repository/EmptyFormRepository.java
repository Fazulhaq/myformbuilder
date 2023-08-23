package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.EmptyForm;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;


public interface EmptyFormRepository extends CrudRepository<EmptyForm, Long> {
    Optional<EmptyForm> findEmptyFormByIdAndUserDataId(Long emptyformid, Long userid);
    Set<EmptyForm> findEmptyFormByUserDataId(Long userid);
}
