package crud.repositrory.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import crud.model.Developer;
import crud.model.Skill;
import crud.model.Speciality;
import crud.model.Status;
import crud.repositrory.DeveloperRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final String DEVELOPER_FILE_PATH = "src/main/resources/developer.json";
    private final Gson GSON = new Gson();
    private List<Developer> readDevelopersFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(DEVELOPER_FILE_PATH)));
            Type type = new TypeToken<ArrayList<Developer>>(){}.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private Integer generateNewId(List<Developer> developers) {
        Developer maxIdSkill = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }


    private void writeDeveloperToFile(List<Developer> developers) {
        String json = GSON.toJson(developers);
        try {
            Files.write(Paths.get(DEVELOPER_FILE_PATH), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Developer getById(Integer id) {
        return readDevelopersFromFile().stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return readDevelopersFromFile();
    }


    @Override
    public Developer save(Developer developerToSave) {
        List<Developer> currentDeveloper = readDevelopersFromFile();

        Integer id = generateNewId(currentDeveloper);
        developerToSave.setId(id);
        developerToSave.setStatus(Status.ACTIVE);
        currentDeveloper.add(developerToSave);
        writeDeveloperToFile(currentDeveloper);
        return developerToSave;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> currentDeveloper = readDevelopersFromFile();
        currentDeveloper = currentDeveloper.stream()
                .map(s -> Objects.equals(s.getId(),developer.getId()) ? developer : s)
                .collect(Collectors.toList());
        writeDeveloperToFile(currentDeveloper);
        return developer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Developer> currentDeveloper = readDevelopersFromFile();
        currentDeveloper = currentDeveloper.stream()
                .peek(s -> {
                    if (Objects.equals(s.getId(), id)) {
                        s.setStatus(Status.DELETED);
                    }
                })
                .collect(Collectors.toList());
        this.writeDeveloperToFile(currentDeveloper);
    }


}
