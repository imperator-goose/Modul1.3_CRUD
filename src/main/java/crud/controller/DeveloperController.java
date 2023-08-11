package crud.controller;

import crud.model.Developer;
import crud.model.Skill;
import crud.model.Speciality;
import crud.model.Status;
import crud.repositrory.DeveloperRepository;
import crud.repositrory.impl.GsonDeveloperRepositoryImpl;
import crud.repositrory.impl.GsonSkillRepositoryImpl;
import crud.repositrory.impl.GsonSpecialityRepositoryImpl;

import java.util.*;

import static crud.model.Status.ACTIVE;

public class DeveloperController {
    private DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    public Developer create(Developer developer) {
        developerRepository.save(developer);
        return developer;
    }

    public Developer read(Integer id) {
        return developerRepository.getById(id);
    }

    public List<Developer> readAll() {
        return developerRepository.getAll();
    }

    public void update(Developer developer){
        developerRepository.update(developer);
    }

    public void delete(Integer id) {
        developerRepository.deleteById(id);
    }

    public static void main(String[] args) {

        List<Skill> skills = new SkillController(new GsonSkillRepositoryImpl()).readAll();

        Speciality speciality = new SpecialityController(new GsonSpecialityRepositoryImpl()).read(1);
        Developer developer = new Developer();
        DeveloperController developerController = new DeveloperController(new GsonDeveloperRepositoryImpl());


        developer.setFirstName("Негритёнок");
        developer.setLastName("Негр");
        speciality.setName("Вспахиватель");

        developer.setSkills(skills);
        developer.setSpecialty(speciality);

        developerController.create(developer);


    }
}
