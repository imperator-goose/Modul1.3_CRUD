package crud.controller;

import crud.model.Developer;
import crud.model.Speciality;
import crud.repositrory.DeveloperRepository;
import crud.repositrory.SkillRepository;
import crud.repositrory.SpecialityRepository;

import java.util.List;

public class SpecialityController {
    private SpecialityRepository specialityRepository;

    public SpecialityController(SpecialityRepository specialityRepository){
        this.specialityRepository = specialityRepository;
    }

    public Speciality create(Speciality speciality) {
        specialityRepository.save(speciality);
        return speciality;
    }

    public Speciality read(Integer id) {
        return specialityRepository.getById(id);
    }

    public List<Speciality> readAll() {
        return specialityRepository.getAll();
    }

    public void update(Speciality speciality){
        specialityRepository.update(speciality);
    }

    public void delete(Integer id) {
        specialityRepository.deleteById(id);
    }

}
