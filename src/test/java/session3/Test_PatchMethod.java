package session3;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Test_PatchMethod {

    @Test
    public void test1() {
        baseURI = "https://reqres.in/api/users/52";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Shamal");
        jsonObject.put("job", "QA");

        given().
                header("content-type", "application/json").
                body(jsonObject.toJSONString()).
                when().
                patch().
                then().
                statusCode(200).
                log().all();

    }
}
