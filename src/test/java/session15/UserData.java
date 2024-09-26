package session15;

import lombok.*;
import org.testng.annotations.Ignore;

import java.util.ArrayList;
import java.util.HashMap;

/*{
"firstName":"Amod",
"lastName":"Mahajan",
"age": 28,
"salary": 10000.56,
"IsMarried":true,
"Hobbies":["Music","Computer","Games"],
"TechSkill":{
"Programming language":"Java",
"WebAutomation":"Selenium",
"API testing" : "Rest Assured"


     }

}*/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserData {

    public String id;
    public String firstName;
    public String lastName;
    public Number age;
    public Double salary;
    public boolean isMarried;
    public ArrayList<String> hobbies;

    public HashMap<String,String>techSkill;
    public String createdAt;




}
