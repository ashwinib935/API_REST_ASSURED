package session30;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class DemoJSONSchemaValidation {


	@Test
	public void testMethod()
	{
		String requestPayload = "{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		
		RestAssured
			.given()
			.baseUri("https://restful-booker.herokuapp.com/auth")
			.contentType(ContentType.JSON)
			.body(requestPayload)
			
			.when()
				.post()
			.then()
				.assertThat()
				.statusCode(200)
				.body("token",Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("I:\\Ashwini_Testing\\APITesting_Rest_Assured_Ashwini\\src\\test\\resource\\schema.json"));
				//.body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\ASUS\\Desktop\\schema.json")));
	}
}
