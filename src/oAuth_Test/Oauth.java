/**
 * 
 */
package oAuth_Test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

/**
 * 
 */
public class Oauth {

	/**
	 * 
	 */
	public static void main(String[] args) {

	//sending formparameters and getting  to access_token
		
   // box [] means STring of arrays
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		
	 String response =given()
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
	 System.out.print("RES>>"+response);
	 
	 JsonPath jspath = new JsonPath(response);
	String acessToken=jspath.getString("access_token");
	System.out.println("token is this"+acessToken);
	 
	 
	  
	  
	  
	//using  access_token to get course details

// gc is object of GetCourse class
       GetCourse gc=given()
      .queryParam("access_token",acessToken)
	  .when().log().all()
//as(GetCourse.class) doing this instaed of asString to convert json response into java object
	  .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
       System.out.println(gc);
       
       System.out.println(gc.getLinkedIn());
       System.out.println(gc.getInstructor());
              
//gc object goes to get courses.java from there goes access's Api.java and goes to index(1)
//and get's CourseTitle using getCourseTitle() method of Api.java
       System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
  
// placing info of courses and api in variable to be used in loop      
       List<Api> apicourses = gc.getCourses().getApi();
       for(int i=0;i<apicourses.size();i++)
       {
    	 if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
    	 {
    		 System.out.println(apicourses.get(i).getPrice());
    	 }
    	
    	
    	 //GET COURSE NAMES*************
    	 
 // array List is used when array in dynamic meaning we dont how many arrays will be there 
  //  	 INTERVIEW COULD BE ASKED NORMAL ARRAY IS USED WHEN U KNOW THE EXACT NUMBERS OF ARRAYS 
    	 
    	 ArrayList<String> aList = new ArrayList<String>();
    	 
    	 List<WebAutomation> wa = gc.getCourses().getWebAutomation();    
    	 for(int j=0;j<wa.size();j++)
    	 {
    //i have added all the titles into the varialbe aList how all ? well by using loop
    		aList.add(wa.get(j).getCourseTitle());
    	 }
    	 
    	 
    //Here converted above declared arrays into Array list for comparison
    	 List<String> expectedTitle = Arrays.asList(courseTitles);
    	 
    	 Assert.assertTrue(aList.equals(expectedTitle));
    	 
       }
    	   
      

	}

}
