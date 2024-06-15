package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.TargetAuthenticationStrategy;


public class RequestBuilder {
	
	public static void main(String[]args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlaceParentClass ap = new AddPlaceParentClass();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		
//location object has knowledge of value of lat and lng
		Location location =new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
//giving values to setLocation by passing location object as argument in setLocation		
		ap.setLocation(location);
		
		ap.setName("servesh");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
//here we have created myList object cause we cant set values in types
//cause value is in array list.
		ap.setTypes(myList);
		
	
	
		         //BASICALLY DECREASING THE SIZE OF THE CODE 
		
	//*********************  RequestSpecification ********************
		
	// importing RequestSpecBuilder class then setting base url	
  RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
   // addQueryParam same as.queryParam in given build() this will build the entire requestspec
.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
  //           **************** ResponseSpecification ******************
  ResponseSpecification respo = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
		  .build();
  //whenever we build reqspecbuilder we give given().spec(req) argumnet will  be passed req
		 RequestSpecification req2 = given().spec(req).body(ap);
				//.queryParam("key", "qaclick123")
		 
		 //String res = when().post("/maps/api/place/add/json")
		 Response response = req2.when().post("/maps/api/place/add/json")
		.then().spec(respo).assertThat().statusCode(200).extract().response();
		   
		   String responseString= response.asString();
	
		System.out.println("haha>><<=="+response);
		
		
		
	}

}
