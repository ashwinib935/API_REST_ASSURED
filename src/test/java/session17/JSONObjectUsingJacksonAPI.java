package session17;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JSONObjectUsingJacksonAPI {
    @Test
    public void createUser(){
        		/*{
   "firstName":"Prerna",
   "lastName":"Gupta",
   "age": 28,
   "salary": 10000.56,
   "IsMarried":true,
   "TechSkill":{
		"Programming language":"Java",
		"WebAutomation":"Selenium",
		"API testing" : "Rest Assured"
             }

 }*/
        //Create ObjectMapper class instance

        ObjectMapper objectMapper = new ObjectMapper();

//Create object node i.e json node
        ObjectNode jsonObjectUser = objectMapper.createObjectNode();

        jsonObjectUser.put("firstName","Komal");
        jsonObjectUser.put("lastName","Bhambere");
        jsonObjectUser.put("age",32);
        jsonObjectUser.put("salary",10000.56);
        jsonObjectUser.put("isMarried",true);

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Cooking");
        hobbies.add("Dance");
        hobbies.add("Music");

        jsonObjectUser.set("hobbies",objectMapper.convertValue(hobbies, JsonNode.class));

        ObjectNode techSkils = objectMapper.createObjectNode();

        techSkils.put("Programming language","Java");
        techSkils.put("WebAutomation","Selenium");
        techSkils.put("API testing","Rest Assured");

        jsonObjectUser.set("techSkils",techSkils);

        //print userDetails JSON Object

        try {
            String userDetailsAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObjectUser);
            System.out.println("Created Json Node is:"+userDetailsAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //Reretrive field value
        String firstName = jsonObjectUser.get("firstName").asText();
        System.out.println("Value of firstName field is:"+firstName);

        Boolean isMarried = jsonObjectUser.get("isMarried").asBoolean();
        System.out.println("Value of isMarried field is:"+isMarried);


        Integer age = jsonObjectUser.get("age").intValue();
        System.out.println("Value of age field is:"+age);

//retried field value of webAutomation
        String webAutomationValue = jsonObjectUser.get("techSkils").get("WebAutomation").asText();
        System.out.println("Value of WebAutomation is :" + webAutomationValue);

        System.out.println("---------------Print all fields name---------------\n");

        Iterator<String> fieldNameIterator = jsonObjectUser.fieldNames();
        while (fieldNameIterator.hasNext()){
            System.out.println(fieldNameIterator.next());
        }

        System.out.println("---------------Print all fields values---------------\n");

        Iterator<JsonNode>fieldValueIterator = jsonObjectUser.elements();
        while (fieldValueIterator.hasNext()){
            System.out.println(fieldValueIterator.next());
        }

        System.out.println("---------------Print all field name and value both (key, value pair)---------------\n");

        Iterator<Map.Entry<String,JsonNode>>keyValuePair = jsonObjectUser.fields();
         while (keyValuePair.hasNext()){
             Map.Entry<String,JsonNode> keyValue  = keyValuePair.next();
             System.out.println("Key of user:"+keyValue.getKey()+"--->"+"Value of user:"+keyValue.getValue());
         }


        //Remove field "first name" from json object or objectnode

        String removedValued = jsonObjectUser.remove("firstName").asText();
        System.out.println("Removed firstname value is: " + removedValued);



        //update json object or object node
        jsonObjectUser.put("lastName", "Bhambere1");

        techSkils.put("Programming language", "C#");
        jsonObjectUser.set("techSkils", techSkils);

        //print userDetails JSON Object

        try {
            String userDetailsAsAString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObjectUser);
            System.out.println("JSON object after remove some fields:"+userDetailsAsAString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //create Request Specification

        Response response = given().
                baseUri("https://reqres.in/api/users").
                contentType(ContentType.JSON).
                body(jsonObjectUser).post();

        System.out.println("-------------Print Http response body-----------------------------");
        response.prettyPrint();

//Validate the status code
        Assert.assertEquals(response.statusCode(), 201,"Check for status code.");



    }

}
