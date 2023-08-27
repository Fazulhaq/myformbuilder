package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.Constants;
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
    public UserData getUserData(Long userid) {
        Optional<UserData> userData = userDataRepository.findById(userid);
        return unwrapUserData(userData, userid);
    }

    public List<UserData> getAllUserData() {
        return (List<UserData>) userDataRepository.findAll();
    }

    public UserData saveUserData(UserData userData){
        return userDataRepository.save(userData);
    }

    public UserData updateUserData(UserData userData, Long userid){
        Optional<UserData> userData1 = userDataRepository.findById(userid);
        unwrapUserData(userData1, userid);
        return userDataRepository.save(userData);
    }

    public void deleteUserData(Long userid){
        Optional<UserData> userData = userDataRepository.findById(userid);
        unwrapUserData(userData, userid);
        userDataRepository.deleteById(userid);
    }

    static UserData unwrapUserData(Optional<UserData> entity, Long userid){
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(userid);
    }


    public Long ifFounded(Long id){
        for(Long l = 1L; l<getAllUserData().size()+1; l++){
            if(getUserData(l).getId().equals(id))
                return l;
        }
        return Constants.Not_Found;
    }
}
