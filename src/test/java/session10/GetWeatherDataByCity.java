package session10;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetWeatherDataByCity {
    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    //api key for autherization - send key directly with URL linke above URL

    @Test
    public void GetWeatherDataByCity()
    {
        //create request specification
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.baseUri("https://api.openweathermap.org");
        requestSpec.basePath("/data/2.5/weather");
        requestSpec.queryParam("q", "delhi").queryParam("appid", "ed9997485c5aebf644a45kef046becfceb6e6");
        Response response = requestSpec.get();

    }
}
