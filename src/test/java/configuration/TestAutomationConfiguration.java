package configuration;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.example.invokes.API;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAutomationConfiguration {
    @BeforeSuite
    public void initToken() {
        var refreshToken = System.getenv("REFRESHTOKEN");

        Gson gson = new Gson();
        String requestBody = gson.toJson(RequestTokenDto.builder().refreshToken(refreshToken).build());

        var response = RestAssured.given()
                .baseUri("https://luftborn-platform-auth-dev.azurewebsites.net/Account/RefreshToken")
                .header("Content-type", "application/json")
                .body(requestBody)
                .post();

        System.out.println("before token");
        System.out.println("refresh-token" + refreshToken);
        response.prettyPrint();

        var token = response.body().jsonPath().get("id_token").toString();

        System.out.println("after token");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(System.getenv("BASEURL"))
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    @Test
    public void getClientById() {
        var response = API.invokeGet(
                Map.of(),
                Map.of("Clients", "74667d39-873b-450b-9091-db3759b5c292"),
                Map.of(),
                "Clients/{Clients}"
        );

        var id = response.body().jsonPath().get("id");
        Assert.assertEquals(id, "74667d39-873b-450b-9091-db3759b5c292");
    }

    @Test
    public void fail(){
        Assert.assertTrue(false);
    }
}
