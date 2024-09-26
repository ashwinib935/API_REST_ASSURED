package session12;

import com.qa.data.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeseralizationJsonDemo {
    @Test
    public void createUser(){
        RequestSpecification requestSpecification = given();
        requestSpecification.baseUri("https://reqres.in");
        requestSpecification.basePath("/api/users");

        Users users1 = new Users("Komal","QA");
        System.out.println("user1:"+users1);

        JSONObject jsonData = new JSONObject();
        jsonData.put("name","Komal");
        jsonData.put("job","QA");



        Response response = requestSpecification.header("content-type","application/json").
                contentType(ContentType.JSON).body(jsonData.toJSONString()).post();

        Users users2 = response.getBody().as(Users.class);
        System.out.println("user2:"+users2);
        Assert.assertEquals(users1.getName(),users2.getName());
        Assert.assertEquals(users1.getJob(),users2.getJob());



    }

}

