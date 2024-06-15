package assured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload2; 

public class DynamicJson {

	
	@Test(dataProvider = "bData")
	public void addbook(String isbn,String aisle) 
	
	  {
		
		RestAssured.baseURI="http://216.10.245.166";
		String respo = given().header("Content-Type","application/json")
// so(Payload2.addbook("xyz","321"))argument of this go to the payload addbook and we can give value to 
// to isbn and aile we can vahnge the value here instaed of goin to json and change it every time
				
	//leave the above example aside this is parametirzed API test with multiple sets of data			
		.body(Payload2.addbook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(respo);
		
		JsonPath js = new JsonPath(respo);
		String id=js.get("ID");
		System.out.println(id);
		
		//DeleteBook
		
	}
	@DataProvider(name="bData")
	public Object[][] getData()
	
	{
		// array = collection of element
		// multidimensional array is collection of array more than 1 [] bracket
		return new Object[][] {{"MLA","123"},{"MP","000"},{"PM","200"}};
	}
	
	
}
