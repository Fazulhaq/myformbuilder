package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.FilledForm;
import org.springframework.data.repository.CrudRepository;

public interface FilledFormRepository extends CrudRepository<FilledForm, Long> {
}
