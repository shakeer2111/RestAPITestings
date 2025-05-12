package Restassured.restapitestings;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresGetRequestTesting {

	@Test
	void get_Request_Validations() {
		baseURI="https://reqres.in/";	                                                   //base URL of testing site
		
		
		 Response response=	
				 given().
			             contentType(ContentType.JSON).                                         //request content type
			             accept(ContentType.JSON).                                              // expected response content type
			
			      when().
			            get("api/users?page=2").                                                 //get request
			
			     then().
			            assertThat().
			            body("page",equalTo(2)).                                                  //validations of response body
			            body("data.find{it.id==7}.email",equalTo("michael.lawson@reqres.in")).    //validating the value of particular key
			           body("data[0].id",  instanceOf(Integer.class)).                            //validating the field types
			           body("data[0].email",instanceOf(String.class)).
			           body("data[0].first_name",instanceOf(String.class)).
			           body("data[0].last_name",instanceOf(String.class)).
			           body("data[0].avatar", instanceOf(String.class)).
			           body("data[1].id", allOf(equalTo(8), instanceOf(Integer.class))).      //validating both value and type
			            statusCode(200).                                                        //status code validation
			            statusLine("HTTP/1.1 200 OK").                                          //status line validation
			            header("Server", "cloudflare").                                         //headers validation
			            header("Cache-Control","max-age=14400").
			            header("Etag","W/\"440-ZjsmMKR8P3usr2uJZbOCGCxC3Uw\"").                //Note: You must escape the double quotes inside the string with backslashes (\").
			            time(lessThan(2000l)).                                                   // response time validation
			    
			            body(matchesJsonSchemaInClasspath("Schemas/schemaforvalidation.json")).                //schema validation by inserting json schema file in classpath i.e under src/test/java and importing related json schema validator
			            log().all().
			            extract().response();
		 
		 System.out.println("Response time : "+response.time());
		 
			
		

		}

	

}

