package pojo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;


public class SerializeTest {
	
	public static void main(String[]args) {
		
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
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response2 = given().queryParam("key", "qaclick123").body(ap)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("haha>><<=="+response2);
		
	}

}
