package crud.controller;

import crud.model.Developer;
import crud.model.Skill;
import crud.repositrory.DeveloperRepository;
import crud.repositrory.SkillRepository;
import crud.repositrory.impl.GsonSkillRepositoryImpl;

import java.util.List;
import java.util.Map;

public class SkillController {
    private SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    public Skill create(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    public Skill read(Integer id) {
        return skillRepository.getById(id);
    }

    public List<Skill> readAll() {
        return skillRepository.getAll();
    }

    public void update(Skill skill){
        skillRepository.update(skill);
    }

    public void delete(Integer id) {
        skillRepository.deleteById(id);
    }


}
