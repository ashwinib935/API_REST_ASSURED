package session3;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Test_PutMethod {

    @Test
    public void test01(){
        baseURI = "https://reqres.in/api/users/52";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","komal");
        jsonObject.put("job","QA");

        given().header("content-type","application/json").
                contentType(ContentType.JSON).
                body(jsonObject.toJSONString()).
                when().
                put().then().statusCode(200).log().all();


    }
}
