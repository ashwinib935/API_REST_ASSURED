package session3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Test_GetMethod {

    @Test
public void getUserOnPage2(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println("Response code:" + response.getStatusCode());
        System.out.println("Response body:" + response.getBody().asString());
        System.out.println("Response Time:" + response.getTime());
        System.out.println("Response Header:" + response.getHeader("Content-Type"));
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode,expectedStatusCode);
}
@Test
public void test_2(){
        baseURI = "https://reqres.in/api/users";
        given().
                when().
                queryParam("page","2").
                get(baseURI).
                then().
                 statusCode(200);
}

//easy for assertion
@Test
public void test2(){
        baseURI = "https://reqres.in/api/users";
       Response response = given().
                when().
                queryParam("page","2").
                get();
        System.out.println(response.getBody().asString());
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("Michael"), true);
        Assert.assertEquals(200,response.getStatusCode());
}


@Test
public void test03(){
             baseURI = "https://reqres.in/api";
             basePath = "/users";

        RequestSpecification httprequest = given();

       // Response response = httprequest.request(Method.GET,"/users");
    Response response = httprequest.get();
        String responseBody = response.getBody().asString();
        System.out.println("Response body is :" + responseBody);

        Assert.assertEquals(responseBody.contains("George"), true);


}

        @Test
        public void getAllHeaders(){
                baseURI = "https://reqres.in/api/users";
                Response response = given().
                        when().
                        get();
                Headers headers = response.getHeaders();
                System.out.println("Headers size:"+headers.size());
                for(Header header:headers){
                        System.out.println("Header:"+header);
                }
        }

}
