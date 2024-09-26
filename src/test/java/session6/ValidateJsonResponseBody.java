package session6;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ValidateJsonResponseBody {

    @Test
    public void UserListResponseBody(){
        RequestSpecification requestSpec = given();

        requestSpec.baseUri("https://reqres.in");
        requestSpec.basePath("api/users");



        Response response =  requestSpec.get();

        //read response body
        ResponseBody responseBody = response.getBody();

        //print response body
        //for body
        // response.getBody().asString();
        System.out.println(responseBody.asString());

        //check for presence of George in response body
        Assert.assertEquals(responseBody.asString().contains("George"),true,"Check for presence of George");

        //get json path view of response body
        //for json path
        //response.getBody().jsonPath();
        JsonPath jsonPathView  = responseBody.jsonPath();

        //x.data[4].first_name
        String firstName = jsonPathView.get("data[0].first_name");

        System.out.println("email address:"+ jsonPathView.get("data[1].avatar"));

        Assert.assertEquals(firstName,"George","Check for presense of firstname as George");




    }


    @Test
    public void UserListResponseBody1(){
//       baseURI ="https://reqres.in";
//       basePath = "api/users";


        Response response =  given().baseUri("https://reqres.in").basePath("api/users").queryParam("page","2").when().get();

        //read response body
        ResponseBody responseBody = response.getBody();

        //print response body
        //for body
        // response.getBody().asString();
        System.out.println(responseBody.asString());

        //check for presence of George in response body
        Assert.assertEquals(responseBody.asString().contains("George"),true,"Check for presence of George");

        //get json path view of response body
        //for json path
        //response.getBody().jsonPath();
        JsonPath jsonPathView  = responseBody.jsonPath();

        //x.data[4].first_name
        String firstName = jsonPathView.get("data[0].first_name");

        System.out.println("email address:"+ jsonPathView.get("data[1].avatar"));

        Assert.assertEquals(firstName,"Michael","Check for presense of firstname as Michael");




    }
}
