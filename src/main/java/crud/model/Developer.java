package crud.model;

import lombok.Data;

import java.util.List;

@Data
public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private Specialty specialty;
    private Status status;

}
