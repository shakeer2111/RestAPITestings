package Restassured.restapitestings;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresPostReqTesting {

    @Test
    void reqres_POST_Request_Validations() {

        // Set the base URI for the Reqres API
        baseURI = "https://reqres.in/";

        // Create a JSON object to represent the request body
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "shakeer");
        obj1.put("job", "Automation tester");

        // Send the POST request and perform response validations
        Response response =
            given()
                .header("x-api-key", "reqres-free-v1")                  // Provide API key as required by Reqres
                .contentType(ContentType.JSON)                         // Set request content type to JSON
                .accept(ContentType.JSON)                              // Expect JSON response from server
                .body(obj1.toJSONString())                             // Set the JSON body as request payload
            .when()
                .post("api/users/2")                                     // Send POST request to /api/users endpoint
            .then()
                .assertThat()
                .body("name", equalTo("shakeer"))                      // Validate 'name' field in response
                .body("job", equalTo("Automation tester"))            // Validate 'job' field in response
                .body("id", notNullValue())                            // Ensure 'id' field is not null
                .body("createdAt", notNullValue())                     // Ensure 'createdAt' field is not null
                .body("id", instanceOf(String.class))                  // Ensure 'id' is of type String
                .body("createdAt", matchesRegex("\\d{4}-\\d{2}-\\d{2}.*")) // Validate 'createdAt' matches expected date format
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Validate response content type
                .time(lessThan(2000L))                                 // Ensure response time is under 2 seconds
                .statusCode(201)                                       // Expect status code 201 (Created)
                .log().all()                                           // Log full response for debugging
                .extract().response();                                 // Extract response for further validation

        // Additional assertion on createdAt field using Java
        String createdAt = response.jsonPath().getString("createdAt");
        assert createdAt.contains("2025") : "createdAt should contain the current year";

        // Print raw response and response time
        System.out.println("Response: " + response.asString());       // Print response body
        System.out.println("Response Time (ms): " + response.time()); // Print response time
    }
}
