package assured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JsonFromFIle {

	
	public static void main(String[] args) throws IOException {
		
		
		// validate if Add Place API is workimg as expected 
				//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
				
				//given() - all input details 
				//when() - Submit the API -resource name,http method
				//Then() - validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		//AddPlace
		// body using file method
		//new String this willl convert digit json back to string
		//content of json is coming from external file , no payload file is needed
		 
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body( new String (Files.readAllBytes(Paths.get("/Users/servesh/Documents/JsonPath.docx")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)")
	// this for extracting response into string fromant 
		.extract().response().asString();
		
		System.out.println("respo::"+response);
		
	// this is for parsing json it will take input as string and convert it into json	
//		JsonPath js = new JsonPath(response);
//	String placeid=js.getString("place_id");
//	System.out.println("place id ::"+placeid);
}
}
