package assured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 

import static org.hamcrest.Matchers.*;



import files.PayLoad;




public class Assured1 {

	public static void main(String[] args) {
		
	
		// validate if Add Place API is workimg as expected 
				//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
				
				//given() - all input details 
				//when() - Submit the API -resource name,http method
				//Then() - validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		//AddPlace
		
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(PayLoad.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)")
	// this for extracting response into string fromant 
		.extract().response().asString();
		
		System.out.println(response);
		
	// this is for parsing json it will take input as string and convert it into json	
		JsonPath js = new JsonPath(response);
	String placeid=js.getString("place_id");
	System.out.println("place id ::"+placeid);
	
	
	//updating address
	//update place
	
      String newAdd = "Summer walk, India";
	
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\n"
			
			// rather place id i have put placeid variable in ""+  +" format 
			+ "\"place_id\":\""+placeid+"\",\n"
			+ "\"address\":\""+newAdd+"\",\n"
			+ "\"key\":\"qaclick123\"\n"
			+ "}")
	
	//here using put http method on given below source and on above base url
	    .when().put("maps/api/place/update/json")
	    
	    // here hamcrest compares equalTo used to compare
	    .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated") );
	
	//GET PLACE
	  String getplaceResponse = given().log().all().queryParam("key","qaclick123")
	     .queryParam(placeid, "place_id")
	     .when().get("maps/api/place/get/json")
	     .then().assertThat().log().all().statusCode(200).extract().response().asString();
	  
	  
//	  SOMETHING IS WRONG RECTIFY IT LATER
	  JsonPath jPath = new JsonPath(getplaceResponse);
	String actualAddress = jPath.getString("address");
	System.out.println("address"+ actualAddress);
	
	
	  
	  
	  
	
	
	}
	


}


 