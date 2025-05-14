package Restassured.restapitestings;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresPutReqTesting {

    @Test
    void reqres_PUT_Request_validations() {
        
        // Base URI of the API
        baseURI = "https://reqres.in/";

        // Create JSON request body with fields to update
        JSONObject obj = new JSONObject();
        obj.put("name", "ravi");
        obj.put("job", "manual tester");

        // Sending PUT request with request body and headers
        Response response = given().
                contentType(ContentType.JSON).        // Set Content-Type header to application/json
                body(obj.toJSONString()).             // Attach JSON body
                header("x-api-key", "reqres-free-v1").// Custom header (Note: this API doesn't require it, added for demonstration)

        when().
                put("api/users/2").                   // PUT request to update user with ID 2

        then().
                assertThat().
                statusCode(200).                      // Validate HTTP status code
                statusLine("HTTP/1.1 200 OK").        // Validate exact status line
                contentType(ContentType.JSON).        // Validate response Content-Type is JSON
                body("name", equalTo("ravi")).        // Validate "name" in response
                body("job", equalTo("manual tester")).// Validate "job" in response
                body("updatedAt", notNullValue()).    // Ensure "updatedAt" field is returned
                header("Connection", equalToIgnoringCase("keep-alive")).   // Validate connection header
                header("Content-Type", containsString("application/json")).// Validate content type header
                time(lessThan(2000L)).                // Ensure response is received in under 2 seconds
                log().all().                          // Log entire response for debugging
                extract().response();                 // Extract full response for further custom validation

        // Extract the 'updatedAt' field from the response
        String updatedAt = response.jsonPath().getString("updatedAt");
        System.out.println("Updated At: " + updatedAt);

        // Check if the year in 'updatedAt' is 2025 (may fail if system date is different)
        assert updatedAt.contains("2025") : "Updated year is not 2025";

        // Validate that the timestamp follows ISO 8601 format (e.g., 2025-05-14T14:23:45.123Z)
        assert updatedAt.matches("\\d{4}-\\d{2}-\\d{2}T.*") : "Invalid timestamp format";

        // Additional null/empty field checks to ensure data was returned correctly
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        assert name != null && !name.isEmpty() : "Name is empty";
        assert job != null && !job.isEmpty() : "Job is empty";
    }
}

