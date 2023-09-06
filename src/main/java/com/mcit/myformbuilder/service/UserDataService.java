package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.exception.EntityNotFoundException;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDataService {
    UserDataRepository userDataRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserData getUserData(Long userid) {
        Optional<UserData> userData = userDataRepository.findById(userid);
        return unwrapUserData(userData, userid);
    }

    public List<UserData> getAllUserData() {
        return (List<UserData>) userDataRepository.findAll();
    }

    public UserData findUserByEmail(String email){
        Optional<UserData> userData = userDataRepository.findByEmail(email);
        return unwrapUserData(userData, 404L);
    }

    public UserData saveUserData(UserData userData){
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        return userDataRepository.save(userData);
    }

    public UserData updateUserData(UserData userData, Long userid){
        Optional<UserData> userData1 = userDataRepository.findById(userid);
        UserData userData2 = unwrapUserData(userData1, userid);
        userData2.setFirstName(userData.getFirstName());
        userData2.setLastName(userData.getLastName());
        userData2.setEmail(userData.getEmail());
        userData2.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
        userData2.setUserType(userData.getUserType());
        userData2.setOrganization(userData.getOrganization());
        return userDataRepository.save(userData2);
    }

    public void deleteUserData(Long userid){
        Optional<UserData> userData = userDataRepository.findById(userid);
        unwrapUserData(userData, userid);
        userDataRepository.deleteById(userid);
    }

    static UserData unwrapUserData(Optional<UserData> entity, Long userid){
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(userid, UserData.class);
    }
}
