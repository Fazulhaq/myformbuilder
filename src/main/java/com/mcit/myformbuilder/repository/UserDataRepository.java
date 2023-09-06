package com.mcit.myformbuilder.repository;

import com.mcit.myformbuilder.entity.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);
}
