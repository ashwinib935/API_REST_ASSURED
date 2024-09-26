package session15;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class JsonObjecUsingJavaMapDemo {

    @Test
    public void createAuthToken(){
      //https://restful-booker.herokuapp.com/auth
        /*{
    "username" : "admin",
    "password" : "password123"
}*/

        HashMap<String,String> authToken = new HashMap<String,String>();
        authToken.put("username","admin");
        authToken.put("password","password123");


        Response response = given().
                baseUri("https://restful-booker.herokuapp.com/auth").
                contentType(ContentType.JSON).
                body(authToken).post();
        response.prettyPrint();

        //verify status code
        Assert.assertEquals(response.statusCode(), 200,"check for status code.");
    }

    @Test
    public void createUser(){

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


        HashMap<String,Object> userData = new HashMap<>();
        userData.put("firstName","Komal");
        userData.put("lastName","Bhambere");
        userData.put("age",32);
        userData.put("salary",10000.56);
        userData.put("isMarried",true);

        ArrayList<String>hobbies = new ArrayList<>();
        hobbies.add("Music");
        hobbies.add("Computer");
        hobbies.add("Games");

        userData.put("hobbies",hobbies);


        HashMap<String,String> techSkill = new HashMap<>();
        techSkill.put("Programming language","Java");
        techSkill.put("WebAutomation","Selenium");
        techSkill.put("API testing","Rest Assured");

        userData.put("techSkill",techSkill);

        UserData userData1 = new UserData("", "Komal","Bhambere",32,10000.56,true,hobbies,techSkill,"");
        System.out.println("userData1:"+userData1);

        Response response = given().
                baseUri("https://reqres.in/api/users").
                contentType(ContentType.JSON).
                body(userData).
                post();

        response.prettyPrint();

//verify status code
        Assert.assertEquals(response.statusCode(), 201,"check for status code.");

        UserData userData2 = response.getBody().as(UserData.class);
        System.out.println("userData2:"+userData2);

        Assert.assertEquals(userData1.getFirstName(),userData2.getFirstName());

    }


}
