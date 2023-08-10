package crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;
    private Speciality specialty;
    private Status status;

}
