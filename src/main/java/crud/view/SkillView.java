package crud.view;

import crud.controller.DeveloperController;
import crud.controller.SkillController;
import crud.repositrory.impl.GsonDeveloperRepositoryImpl;
import crud.repositrory.impl.GsonSkillRepositoryImpl;
import crud.model.Skill;
import lombok.Data;

import java.util.Scanner;
@Data
public class SkillView {
    Scanner scanner;
    private SkillController skillController = new SkillController(new GsonSkillRepositoryImpl());
    private DeveloperController developerController =new DeveloperController(new GsonDeveloperRepositoryImpl());

    public void workProgram(){
        while (true){
            getAllSkill();
            writeNewSkill();
            updateSkill();
            deleteSkill();
            getAllSkill();
            break;
        }
    }
    private void writeNewSkill(){
        scanner = new Scanner(System.in);
        Skill skillToSave = new Skill();

        System.out.println("Введите название Skill");
        String nameSkill = scanner.next();
        skillToSave.setName(nameSkill);

        System.out.println(developerController.readAll());
        System.out.println("Введите к каком разработчику отнести");
        Integer developerId = scanner.nextInt();
        skillToSave.setId(developerId);

        skillController.create(skillToSave);
    }

    private void updateSkill(){
        scanner = new Scanner(System.in);
        Skill skillToSave = new Skill();
        System.out.println("Введите id Skill для обновления ");
        Integer id = scanner.nextInt();
        skillToSave.setId(id);
        System.out.println("Введите новое название Skill");
        String nameSkill = scanner.next();
        skillToSave.setName(nameSkill);


        skillController.update(skillToSave);
    }

    private void deleteSkill(){
        scanner = new Scanner(System.in);
        System.out.println("Введите id Skill для удаления");
        Integer id = scanner.nextInt();

        skillController.delete(id);
    }

    private void getAllSkill(){
        System.out.println(skillController.readAll());
    }
}
