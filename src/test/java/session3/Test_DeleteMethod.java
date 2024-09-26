package session3;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Test_DeleteMethod {

    @Test
    public void test(){
        baseURI = "https://reqres.in/api/users/52";
        given().
                when().
                delete().
                then().
                statusCode(204).
                log().all();

    }
}
