package crud.controller;

import crud.model.Developer;
import crud.repositrory.DeveloperRepository;
import crud.repositrory.impl.GsonDeveloperRepositoryImpl;

import java.util.*;

public class DeveloperController {
    private DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

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
}
