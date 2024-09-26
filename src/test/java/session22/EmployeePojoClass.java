package session22;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePojoClass {
    private String firstname;
    private String lastname;
    private String gender;
    private int age;
    private double salary;
    private  Address   address;
}
