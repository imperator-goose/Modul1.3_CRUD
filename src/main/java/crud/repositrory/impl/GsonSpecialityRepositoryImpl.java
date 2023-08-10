package crud.repositrory.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import crud.model.Skill;
import crud.model.Speciality;
import crud.model.Status;
import crud.repositrory.SkillRepository;
import crud.repositrory.SpecialityRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GsonSpecialityRepositoryImpl implements SpecialityRepository {
    private final String SPECIALTY_FILE_PATH = "src/main/resources/specialty.json";
    private final Gson GSON = new Gson();
    private List<Speciality> readSpecialitiesFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(SPECIALTY_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Speciality>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private Integer generateNewId(List<Speciality> specialities) {
        Speciality maxIdSkill = specialities.stream().max(Comparator.comparing(Speciality::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }


    private void writeSpecialitiesToFile(List<Speciality> skills) {
        String json = GSON.toJson(skills);
        try {
            Files.write(Paths.get(SPECIALTY_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Speciality getById(Integer id) {
     return readSpecialitiesFromFile().stream()
             .filter(speciality -> speciality.getId().equals(id))
             .findFirst()
             .orElse(null);
    }

    @Override
    public List<Speciality> getAll() {
        return readSpecialitiesFromFile();
    }


    @Override
    public Speciality save(Speciality specialityToSave) {
        List<Speciality> currentSpeciality = readSpecialitiesFromFile();

        Integer id = generateNewId(currentSpeciality);
        specialityToSave.setId(id);
        specialityToSave.setStatus(Status.ACTIVE);
        currentSpeciality.add(specialityToSave);
        writeSpecialitiesToFile(currentSpeciality);
        return specialityToSave;
    }

    @Override
    public Speciality update(Speciality speciality) {
        List<Speciality> currentSpeciality = readSpecialitiesFromFile();
        currentSpeciality = currentSpeciality.stream()
                .map(s -> Objects.equals(s.getId(),speciality.getId()) ? speciality : s)
                .collect(Collectors.toList());
        writeSpecialitiesToFile(currentSpeciality);
        return speciality;
    }

    @Override
    public void deleteById(Integer id) {
        List<Speciality> currentSpeciality = readSpecialitiesFromFile();
        currentSpeciality = currentSpeciality.stream()
                .peek(s -> {
                    if (Objects.equals(s.getId(), id)) {
                        s.setStatus(Status.DELETED);
                    }
                })
                .collect(Collectors.toList());
        this.writeSpecialitiesToFile(currentSpeciality);
    }


}
