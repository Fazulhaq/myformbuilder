package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.exception.EmptyFormNotFoundException;
import com.mcit.myformbuilder.exception.EntityNotFoundException;
import com.mcit.myformbuilder.repository.EmptyFormRepository;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class EmptyFormService {
    EmptyFormRepository emptyFormRepository;
    UserDataRepository userDataRepository;

    public EmptyForm getEmptyForm(Long emptyFormId){
        Optional<EmptyForm> emptyForm = emptyFormRepository.findById(emptyFormId);
        return unwrappedEmptyForm(emptyForm, emptyFormId);
    }

    public List<EmptyForm> getAllEmptyForm(){
        return (List<EmptyForm>) emptyFormRepository.findAll();
    }

    public EmptyForm saveEmptyForm(EmptyForm emptyForm, Long userId){
        UserData userData = UserDataService.unwrapUserData(userDataRepository.findById(userId), userId);
        emptyForm.setUserData(userData);
        return emptyFormRepository.save(emptyForm);
    }

    public EmptyForm updateEmptyForm(EmptyForm newEmptyForm, Long emptyFormId){
        Optional<EmptyForm> emptyForm = emptyFormRepository.findById(emptyFormId);
        EmptyForm unwrappedEmptyForm = unwrappedEmptyForm(emptyForm, emptyFormId);
        unwrappedEmptyForm.setFormTitle(newEmptyForm.getFormTitle());
        unwrappedEmptyForm.setJsonText(newEmptyForm.getJsonText());
        unwrappedEmptyForm.setPublishDate(newEmptyForm.getPublishDate());
        return emptyFormRepository.save(unwrappedEmptyForm);
    }

    public void deleteEmptyForm(Long emptyformid){
        Optional<EmptyForm> emptyForm = emptyFormRepository.findById(emptyformid);
        EmptyForm unwrappedEmptyForm = unwrappedEmptyForm(emptyForm, emptyformid);
        emptyFormRepository.delete(unwrappedEmptyForm);
    }

    public Set<EmptyForm> getUserEmptyForms(Long userid){
        Optional<UserData> userData = userDataRepository.findById(userid);
        UserDataService.unwrapUserData(userData, userid);
        Set<EmptyForm> emptyForm = emptyFormRepository.findEmptyFormByUserDataId(userid);
        if (emptyForm.isEmpty()) throw new EmptyFormNotFoundException(userid);
        return emptyForm;
    }

    static EmptyForm unwrappedEmptyForm(Optional<EmptyForm> entity, Long emptyFormId){
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(emptyFormId, EmptyForm.class);
    }
}
