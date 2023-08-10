package crud.repositrory.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import crud.model.Skill;
import crud.model.Status;
import crud.repositrory.SkillRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class GsonSkillRepositoryImpl implements SkillRepository {
    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";
    private final Gson GSON = new Gson();

    private List<Skill> readSkillsFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(SKILL_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Skill>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String json = GSON.toJson(skills);
        try {
            Files.write(Paths.get(SKILL_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }

    @Override
    public Skill getById(Integer id) {
        return readSkillsFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return readSkillsFromFile();
    }

    @Override
    public Skill save(Skill skillToSave) {
        List<Skill> currentSkills = readSkillsFromFile();

        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        skillToSave.setStatus(Status.ACTIVE);
        currentSkills.add(skillToSave);
        writeSkillsToFile(currentSkills);
        return skillToSave;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> currentSkill = readSkillsFromFile();
        currentSkill = currentSkill.stream()
                .map(s -> Objects.equals(s.getId(),skill.getId()) ? skill : s)
                .collect(Collectors.toList());
        writeSkillsToFile(currentSkill);
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkills = readSkillsFromFile();
        currentSkills = currentSkills.stream()
                .peek(s -> {
                    if (Objects.equals(s.getId(), id)) {
                        s.setStatus(Status.DELETED);
                    }
                })
                .collect(Collectors.toList());
        this.writeSkillsToFile(currentSkills);
    }

}
