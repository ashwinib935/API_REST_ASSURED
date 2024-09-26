package session22;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoNestedJSONPayload {

    @Test
    public void createUser() throws JsonProcessingException {
		/*
		 *
{
  "firstName": "Suresh",
  "lastName": "Mehra",
  "gender": "Male",
  "age": 35,
  "salary:10000.56,
  "Address":{
	"Street": "Park Avenue",
	"City": "Vijaywada",
	"State": "Andhra Pradesh",
	"pin code":530012
        }
}
*/
        Address address = new Address("Park Avenue","vijaywada","Andhra Pradesh",530012);

        EmployeePojoClass emp1 =new EmployeePojoClass("Suresh","Mehra","Male",35,10000.56,address);


        ObjectMapper objectMapper = new ObjectMapper();

//        String jsonpayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
//        System.out.println("jsonpayload"+jsonpayload);

        //OR

        ObjectNode jsonpayload = objectMapper.createObjectNode();
        jsonpayload.set("employee",objectMapper.convertValue(emp1, JsonNode.class));

        Response response = given().
                baseUri("http://httpbin.org/post").
                contentType(ContentType.JSON).
                body(jsonpayload).
                post();
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200,"Check for status code");

    }

}
