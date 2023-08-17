package crud.view;

import crud.controller.DeveloperController;
import crud.controller.SkillController;
import crud.controller.SpecialityController;
import crud.model.Developer;
import crud.model.Skill;
import crud.model.Speciality;

import crud.repositrory.impl.GsonDeveloperRepositoryImpl;
import crud.repositrory.impl.GsonSkillRepositoryImpl;
import crud.repositrory.impl.GsonSpecialityRepositoryImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;
public class DeveloperView {
    Scanner scanner;
    private DeveloperController developerController = new DeveloperController(new GsonDeveloperRepositoryImpl());
    private SkillController skillController = new SkillController(new GsonSkillRepositoryImpl());
    private SpecialityController specialtyController = new SpecialityController(new GsonSpecialityRepositoryImpl());
    private Developer developer = new Developer();
    public void workProgram(){
        while (true){
            getAllDeveloper();
            writeNewDeveloper();
            updateDeveloper();
            deleteDeveloper();
            getAllDeveloper();
            break;
        }
    }
    private void writeNewDeveloper(){
        try {
            scanner = new Scanner(System.in);

            Developer developerToSave = new Developer();


            System.out.println("Введите firstName для Developer");
            String firstNameDeveloper = scanner.nextLine();
            developerToSave.setFirstName(firstNameDeveloper);

            System.out.println("Введите lastName для Developer");
            String lastNameDeveloper = scanner.nextLine();
            developerToSave.setLastName(lastNameDeveloper);


            List<Skill> skills = skillRead();
            developerToSave.setSkills(skills);

            Speciality specialty = specialtyRead();
            developerToSave.setSpecialty(specialty);

            developerController.create(developerToSave);
        }catch (InputMismatchException e){
            writeNewDeveloper();
        }
    }

    private void updateDeveloper(){
        try {

            scanner = new Scanner(System.in);
            Developer developerToSave = new Developer();


            System.out.println("Введите id Developer для обновления ");
            Integer id = scanner.nextInt();

            developerToSave.setId(id);

            System.out.println("Введите новое firstName Developer");
            String firstNameDeveloper = scanner.next();
            developerToSave.setFirstName(firstNameDeveloper);

            System.out.println("Введите новое lastName Developer");
            String lastNameDeveloper = scanner.next();
            developerToSave.setLastName(lastNameDeveloper);


            developerController.update(developerToSave);

        }catch (InputMismatchException e){
            updateDeveloper();
        }
    }

    private void deleteDeveloper(){
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введите id Developer для удаления");
            Integer id = scanner.nextInt();

            developerController.delete(id);
        }catch (InputMismatchException e){
            deleteDeveloper();
        }
    }

    private void getAllDeveloper(){
        System.out.println(developerController.readAll());
    }

    private List<Skill> skillRead(){
        List<Skill> currentDeveloperSkills = new ArrayList<>();
        developer.getSkills();
        return currentDeveloperSkills;
    }

    private Speciality specialtyRead(){


        return developer.getSpecialty();
    }


}