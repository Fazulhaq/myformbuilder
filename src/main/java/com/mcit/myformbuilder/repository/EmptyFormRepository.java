package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.EmptyForm;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface EmptyFormRepository extends CrudRepository<EmptyForm, Long> {
    Set<EmptyForm> findEmptyFormByUserDataId(Long userid);
}
