package crud.view;


import java.util.InputMismatchException;
import java.util.Scanner;
import crud.controller.DeveloperController;

import crud.controller.SpecialityController;
import crud.model.Speciality;
import crud.model.Status;
import crud.repositrory.impl.GsonDeveloperRepositoryImpl;
import crud.repositrory.impl.GsonSpecialityRepositoryImpl;
public class SpecialityView {
    Scanner scanner;
    private SpecialityController specialtyController = new SpecialityController(new GsonSpecialityRepositoryImpl());
    private DeveloperController developerController = new DeveloperController(new GsonDeveloperRepositoryImpl());
    public void workProgram(){
        while (true){
            getAllSpeciality();
            writeNewSpecialty();
            updateSpecialty();
            deleteSpecialty();
            getAllSpeciality();
            break;
        }
    }
    private void writeNewSpecialty(){
        try {
            scanner = new Scanner(System.in);
            Speciality specialtyToSave = new Speciality();

            System.out.println("Введите название Specialty");
            String nameSpecialty = scanner.next();
            specialtyToSave.setName(nameSpecialty);

            System.out.println(developerController.readAll());
            System.out.println("Введите к какому разработчику отснести");
            Integer developerId = scanner.nextInt();
            specialtyToSave.setId(developerId);
            specialtyController.create(specialtyToSave);
        }catch (InputMismatchException e){
            writeNewSpecialty();
        }
    }

    private void updateSpecialty(){
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введите id Specialty для обновления ");
            Integer ID = scanner.nextInt();
            System.out.println("Введите новое название Specialty");
            String nameSpecialty = scanner.next();
            Speciality specialtyToSave = new Speciality(ID, nameSpecialty, Status.ACTIVE);

            specialtyController.update(specialtyToSave);
        }catch (InputMismatchException e){
            updateSpecialty();
        }
    }

    private void deleteSpecialty(){
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введите id Specialty для удаления");
            Integer id = scanner.nextInt();

            specialtyController.delete(id);
        }catch (InputMismatchException e){
            deleteSpecialty();
        }
    }

    private void getAllSpeciality(){
        System.out.println(specialtyController.readAll());
    }
}
