package session23;

import lombok.*;
import session22.EmployeePojoClass;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NestedJSONPojoClass {
    private String companyName;
    private String state;
    private String Street;
    private String City;

    private String pincode;
    private List<String > bankAccount;

    private List<EmployeePojoClass> employeeList;
}
