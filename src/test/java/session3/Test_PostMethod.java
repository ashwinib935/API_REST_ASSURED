package session3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Test_PostMethod {

    @Test
    public void test01(){
        baseURI = "https://reqres.in/api/users";
        JSONObject jsonData = new JSONObject();
        jsonData.put("name","komal");
        jsonData.put("job","QA");

        given().header("content-type","application/json").
                contentType(ContentType.JSON).
                body(jsonData.toJSONString()).
                when().
                post().
                then().statusCode(201).log().all();
    }

    @Test
    public void test02(){
        baseURI = "https://reqres.in/api/users";

        JSONObject jsonData = new JSONObject();
        jsonData.put("name","Shamal");
        jsonData.put("job","QA");

        RequestSpecification httpRequest = given();

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(jsonData.toJSONString());

        Response response = httpRequest.request(Method.POST, "");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("Shamal"), true);

        String contentType = response.header("Content-Type");
        System.out.println("content type is "+contentType);
        Assert.assertEquals(contentType.contains("application/json"), true);

    }

}
