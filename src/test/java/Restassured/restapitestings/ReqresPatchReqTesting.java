package Restassured.restapitestings;

	import org.testng.annotations.Test;
	import static io.restassured.RestAssured.given;
	import static io.restassured.RestAssured.baseURI;
	import static org.hamcrest.Matchers.*;

	import org.json.simple.JSONObject;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	public class ReqresPatchReqTesting {
	

	    @Test
	    void reqres_PATCH_Request_validations() {

	        // Set the base URI for Reqres API
	        baseURI = "https://reqres.in/";

	        // Create JSON body with partial fields to update
	        JSONObject obj = new JSONObject();
	        obj.put("job", "automation tester"); // Only updating 'job' field for PATCH

	        // Send PATCH request with body and headers
	        Response response = given().
	                contentType(ContentType.JSON).        // Specify JSON request
	                body(obj.toJSONString()).             // Convert JSON object to string
	                header("x-api-key", "reqres-free-v1").// Custom header (not required by reqres.in, for demonstration)

	        when().
	                patch("api/users/2").                 // PATCH request to update user with ID 2

	        then().
	                assertThat().
	                statusCode(200).                      // Check that status code is 200 OK
	                statusLine("HTTP/1.1 200 OK").        // Confirm the exact status line
	                contentType(ContentType.JSON).        // Ensure response is JSON
	                body("job", equalTo("automation tester")). // Validate updated job field
	                body("updatedAt", notNullValue()).    // Ensure the updatedAt field is present
	                header("Connection", equalToIgnoringCase("keep-alive")). // Header validation
	                header("Content-Type", containsString("application/json")). // Content-Type check
	                time(lessThan(2000L)).                // Response should be quick (< 2 seconds)
	                log().all().                          // Log response details for debugging
	                extract().response();                 // Extract response for further checks

	        // Extract 'updatedAt' from response
	        String updatedAt = response.jsonPath().getString("updatedAt");
	        System.out.println("Updated At: " + updatedAt);

	        // Verify that the timestamp contains the current year (2025)
	        assert updatedAt.contains("2025") : "Updated year is not 2025";

	        // Validate ISO 8601 datetime format in 'updatedAt'
	        assert updatedAt.matches("\\d{4}-\\d{2}-\\d{2}T.*") : "Invalid timestamp format";

	        // Optional: Validate that response contains the updated job value and it's not empty
	        String job = response.jsonPath().getString("job");
	        assert job != null && !job.isEmpty() : "Job is empty";
	    }
	    
	}

