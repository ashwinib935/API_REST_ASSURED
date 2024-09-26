package session23;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import session21.Employee;
import session22.Address;
import session22.EmployeePojoClass;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DemoTestComplexNestedJSONObject {
/*"companyName" :"XYZ Ltd",
"Street": "Arifac Avenue",
	"City": "RK Puram, Delhi",
	"State": "New Delhi",
	"pin code":110066,
"BankAccounts":["HDFC","SBI","AXIS"]*/


    @Test
    public void createUser() throws JsonProcessingException {
        List<String>banks = new ArrayList<>();

        banks.add("HDFC");
        banks.add("SBI");
        banks.add("AXIS");


        Address address = new Address("Park Avenue","vijaywada","Andhra Pradesh",530012);

        EmployeePojoClass emp1 = new EmployeePojoClass("Komal","Bhambere","female",32,10000.56,address);
        EmployeePojoClass emp2 = new EmployeePojoClass("Shamal","Bhambere","female",34,20000.56,address);
        EmployeePojoClass emp3 = new EmployeePojoClass("Sagar","Bhambere","male",36,30000.56,address);

        List<EmployeePojoClass> emplist = new ArrayList<>();
        emplist.add(emp1);
        emplist.add(emp2);
        emplist.add(emp3);

        NestedJSONPojoClass nestedJSONPojoClass = new NestedJSONPojoClass("XYZ Ltd","Arifac Avenue","RK Puram, Delhi","New Delhi","110066",banks,emplist);

        //Convert Class object to JSON Object as String
        ObjectMapper objectMapper = new ObjectMapper();
       // String jsonPlayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nestedJSONPojoClass);

        ObjectNode jsonPlayload = objectMapper.createObjectNode();
        jsonPlayload.set("employees",objectMapper.convertValue(nestedJSONPojoClass, JsonNode.class));

        Response response = given().
                            baseUri("http://httpbin.org/post").
                            contentType(ContentType.JSON).
                body(jsonPlayload).
                post();

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200,"Check for status code");
    }
}
