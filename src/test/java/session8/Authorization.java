package session8;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Authorization {
    @Test
    public void BasicAuth()
    {
        RequestSpecification requestSpec = RestAssured.given();

        //Specify URL
        requestSpec.baseUri("http://postman-echo.com");
        requestSpec.basePath("/basic-auth");

        //perform get request
        Response response = requestSpec.auth().preemptive().basic("postman", "password").get();

        //print status line
        System.out.println("Responsne status:" + response.statusLine());
        System.out.println("Response body:" + response.getBody().asString());

    }
    @Test
    public void DigestAuth()
    {
        //http://httpbin.org/digest-auth/undefined/prachi/prachi
        RequestSpecification requestSpec = RestAssured.given();

        //Specify URL
        requestSpec.baseUri("http://httpbin.org");
        requestSpec.basePath("/digest-auth/undefined/admin/password/MD5");

        //perform get request
        //Response response = requestSpec.get();
        Response response = requestSpec.auth().digest("admin", "password").get();

        Assert.assertEquals(response.statusCode()/*actual*/, 200,"check for status code");
        //print status line
        System.out.println("Digest Auth Responsne status:" + response.statusLine());
        System.out.println("Digest Auth Response body:" + response.body().asString());

    }
}
