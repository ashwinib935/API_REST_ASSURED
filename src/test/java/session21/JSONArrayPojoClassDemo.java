package session21;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONArrayPojoClassDemo {

    @Test
    public void createEmployeesJSONArray(){

      Employee emp1 = new Employee("Komal","Bhambere","female",32,10000.56);
        Employee emp2 = new Employee("Shamal","Bhambere","female",34,20000.56);
        Employee emp3 = new Employee("Komal","Bhambere","female",32,10000.56);

        //Create List Of Employee
        ArrayList<Employee>empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonEmployeeObject = objectMapper.createObjectNode();

        jsonEmployeeObject.set("employees",objectMapper.convertValue(empList, JsonNode.class));

        //create Request Specification
        RequestSpecification reqSpec = RestAssured.given();

        //specify URL
        reqSpec.baseUri("http://httpbin.org/post");

        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(jsonEmployeeObject);

        //perform POST request
        Response response = reqSpec.post();
        System.out.println("----------ResponseBody----------------");

        response.prettyPrint();


        //verify the status code
        Assert.assertEquals(response.statusCode(), 200,"check for status code.");

        //convert JsonArray to Employee class objects (Deserialization)
     List<Employee> allEmployees = response.getBody().jsonPath().getList("json.employees",Employee.class);
        System.out.println("Employee list size:"+allEmployees.size());
        System.out.println("----------Emoployee objects in ResponseBody----------------");
        for (Employee emp:allEmployees){
            System.out.println(emp.getFirstname()+ " " + emp.getLastname());
        }

    }

    @Test
    public void createEmployeesJSONArray1() throws JsonProcessingException {

        Employee emp1 = new Employee("Komal", "Bhambere", "female", 32, 10000.56);
        Employee emp2 = new Employee("Shamal", "Bhambere", "female", 34, 20000.56);
        Employee emp3 = new Employee("Komal", "Bhambere", "female", 32, 10000.56);

        //Create List Of Employee
        ArrayList<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);

        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode jsonEmployeeObject = objectMapper.createObjectNode();
//
//        jsonEmployeeObject.set("employees",objectMapper.convertValue(empList, JsonNode.class));
        String jsonArrayPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(empList);

        //create Request Specification
        RequestSpecification reqSpec = RestAssured.given();

        //specify URL
        reqSpec.baseUri("http://httpbin.org/post");

        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(jsonArrayPayload);

        //perform POST request
        Response response = reqSpec.post();
        System.out.println("----------ResponseBody----------------");

        response.prettyPrint();


        //verify the status code
        Assert.assertEquals(response.statusCode(), 200, "check for status code.");

        //convert JsonArray to Employee class objects (Deserialization)
        // List<Employee> allEmployees = response.getBody().jsonPath().getList("json.employees",Employee.class);

        ResponseBody responseBody = response.getBody();

        JsonPath jsonPathView = responseBody.jsonPath();

        List<Employee> allEmployees = jsonPathView.getList("json", Employee.class);


        System.out.println("Employee list size:" + allEmployees.size());
        System.out.println("----------Emoployee objects in ResponseBody----------------");
        for (Employee emp : allEmployees) {
            System.out.println(emp.getFirstname() + " " + emp.getLastname());
        }

    }

}
