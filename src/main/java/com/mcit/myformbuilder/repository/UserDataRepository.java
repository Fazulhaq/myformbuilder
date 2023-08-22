package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.UserData;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
}
