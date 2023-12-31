package com.mcit.myformbuilder.service;

import com.mcit.myformbuilder.entity.EmptyForm;
import com.mcit.myformbuilder.entity.FilledForm;
import com.mcit.myformbuilder.entity.UserData;
import com.mcit.myformbuilder.exception.EmptyFilledFormNotFoundException;
import com.mcit.myformbuilder.exception.EntityNotFoundException;
import com.mcit.myformbuilder.exception.UserFilledFormNotFoundException;
import com.mcit.myformbuilder.repository.EmptyFormRepository;
import com.mcit.myformbuilder.repository.FilledFormRepository;
import com.mcit.myformbuilder.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class FilledFormService {
    FilledFormRepository filledFormRepository;
    UserDataRepository userDataRepository;
    EmptyFormRepository emptyFormRepository;

    public FilledForm getFilledForm(Long filledFormId){
        Optional<FilledForm> filledForm = filledFormRepository.findById(filledFormId);
        return testedFilledForm(filledForm, filledFormId);
    }

    public List<FilledForm> getFilledForms(){
        return (List<FilledForm>) filledFormRepository.findAll();
    }

    public FilledForm saveFilledForm(FilledForm filledForm, Long userId, Long emptyFormId) {
        UserData userData = UserDataService.unwrapUserData(userDataRepository.findById(userId), userId);
        EmptyForm emptyForm = EmptyFormService.unwrappedEmptyForm(emptyFormRepository.findById(emptyFormId), emptyFormId);
        filledForm.setUserData(userData);
        filledForm.setEmptyForm(emptyForm);
        return filledFormRepository.save(filledForm);
    }

    public FilledForm updateFilledForm(FilledForm newFilledForm, Long filledFormId){
        Optional<FilledForm> filledForm = filledFormRepository.findById(filledFormId);
        FilledForm testedFilledForm = testedFilledForm(filledForm, filledFormId);
        testedFilledForm.setFormTitle(newFilledForm.getFormTitle());
        testedFilledForm.setJsonText(newFilledForm.getJsonText());
        testedFilledForm.setFilledDate(newFilledForm.getFilledDate());
        testedFilledForm.setFormStatus(newFilledForm.getFormStatus());
        return filledFormRepository.save(testedFilledForm);
    }

    public void deleteFilledForm(Long filledFormId) {
        Optional<FilledForm> filledForm = filledFormRepository.findById(filledFormId);
        FilledForm testedFilledForm = testedFilledForm(filledForm, filledFormId);
        filledFormRepository.delete(testedFilledForm);
    }

    public Set<FilledForm> getUserFilledForms(Long userId){
        Optional<UserData> userData = userDataRepository.findById(userId);
        UserDataService.unwrapUserData(userData, userId);
        Set<FilledForm> filledForms = filledFormRepository.findFilledFormByUserDataId(userId);
        if (filledForms.isEmpty()) throw new UserFilledFormNotFoundException(userId);
        return filledForms;
    }

    public Set<FilledForm> getEmptyFormUsedFilledForms(Long emptyFormId){
        Optional<EmptyForm> emptyForm = emptyFormRepository.findById(emptyFormId);
        EmptyFormService.unwrappedEmptyForm(emptyForm, emptyFormId);
        Set<FilledForm> filledForms = filledFormRepository.findFilledFormByEmptyFormId(emptyFormId);
        if (filledForms.isEmpty()) throw new EmptyFilledFormNotFoundException(emptyFormId);
        return filledForms;
    }

     static FilledForm testedFilledForm(Optional<FilledForm> filledForm, Long formId){
        if (filledForm.isPresent()) return filledForm.get();
        else throw new EntityNotFoundException(formId, FilledForm.class);
    }
}
