package Restassured.restapitestings;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresDeleteReqTesting {

    @Test
    void reqres_Delete_Request_Validation() {

        // Set the base URI for Reqres API
        baseURI = "https://reqres.in/api";

        // Send DELETE request to delete user with ID = 2 and perform validations
        Response response =
            given()
                .header("x-api-key", "reqres-free-v1")        // Provide API key as required by Reqres
                .contentType(ContentType.JSON)                // Specify request content type
                .accept(ContentType.JSON)                     // Expect JSON response (even though it's 204 No Content)
            .when()
                .delete("/users/2")                           // Perform DELETE on user with ID 2
            .then()
                .statusCode(204)                              // Validate HTTP status code is 204 No Content
                .statusLine("HTTP/1.1 204 No Content")        // Validate exact status line      
                .time(lessThan(2000L))                        // Validate response time is under 2 seconds
                .log().all()                                  // Log the entire response for debugging
                .extract()
                .response();                                  // Extract response for further validations

        // Additional manual assertion to ensure response body is completely empty
        assert response.asString().isEmpty() : "Response body should be empty for DELETE 204";
        assert response.contentType().isEmpty() : "Response body should be empty for 204 No Content";
        // Print status code and response time in the console for reference
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Time (ms): " + response.time());
    }
}
