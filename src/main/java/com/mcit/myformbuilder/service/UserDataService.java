package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.exception.UserNotFoundException;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDataService {
    UserDataRepository userDataRepository;
    public UserData getUserData(Long userId) {
        Optional<UserData> userData = userDataRepository.findById(userId);
        return unwrapUserData(userData, userId);
    }

    public List<UserData> getAllUserData() {
        return (List<UserData>) userDataRepository.findAll();
    }

    public UserData saveUserData(UserData userData){
        return userDataRepository.save(userData);
    }

    public UserData updateUserData(UserData userData, Long userId){
        Optional<UserData> userData1 = userDataRepository.findById(userId);
        unwrapUserData(userData1, userId);
        return userDataRepository.save(userData);
    }

    public void deleteUserData(Long userId){
        Optional<UserData> userData = userDataRepository.findById(userId);
        unwrapUserData(userData, userId);
        userDataRepository.deleteById(userId);
    }

    static UserData unwrapUserData(Optional<UserData> entity, Long userId){
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(userId);
    }


}
