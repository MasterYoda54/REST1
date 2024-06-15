package jiraTest;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class AUTOMATION_JIRA {

	public static void main(String[] args) {
		
	  // REVISE AND READ THESE CODE FOR JIRA AND BELOW ARE SOME IMP TOPICS
	 //SessionFilter
	//.multiPart("file",new File("jojo.txt"))
   //.queryParam("fields","comment")	
		
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		
//LOGIN 
		
// filtersession for parsing json read about it not sure
// so when ever i run this code the response will get stored in session OBJECT		
		SessionFilter session = new SessionFilter();
		
// relaxedHTTPSValidation() for real time API'S HERE NOT NEEDED CAUSE WE ARE RUNNING IN OUR LOCAL SERVER
//BUT IN REAL TIME TO INTERACT IWTH THOSE HTTPS WEBSITE WE HAVE TO USE THIS 	
	    given().relaxedHTTPSValidation().header("Content-Type","application/json")
		.body("{ \"username\": \"servesh\", \"password\": \"elippse30\" }")
		.filter(session).when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();	
		
		String expectedMessage ="hi bro wtsup";
		
//ADD COMMENT
		
		String addcommentResponse = given().pathParam("id", "10005")
		// this header basically menas that we are using json to content type
		.header("Content-Type","application/json")
		.body("{\n"
				+ "    \"body\": \""+expectedMessage+"\",\n"
				+ "    \"visibility\": {\n"
				+ "        \"type\": \"role\",\n"
				+ "        \"value\": \"Administrators\"\n"
				+ "    }\n"
				+ "}")
		       .filter(session).when().post("/rest/api/2/issue/{id}/comment")
		       .then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(addcommentResponse);
		String commentID =js.getString("id");
		
		
//ADD ATTACHMENTS
		
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("id", "10005")
//Because the content type is multipart		
		.header("Content-Type","multipart/form-data")	
//For sending file attachment in restassured jojo.txt cause file is in project else have to give file location
		.multiPart("file",new File("jojo.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
//GET ISSUE
		
		String details = given().filter(session).pathParam("id","10005").log().all()
		// this is for particular field  we only get comment filed in json response		
				.queryParam("fields","comment")
		.when().get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		
		JsonPath js2 = new JsonPath(details);
		int commentscount=js2.getInt("fields.comment.comments.size()");
		System.out.println("comment count2=="+commentscount);
		
		for(int i=0;i<commentscount;i++)
		{
			// this to string() basically converts in to string
		 String comment =js2.get("fields.comment.comments["+i+"].id").toString();
		 System.out.println("comment count=="+comment);
		 
		 // if it matches only then it will go inside this loop of if
		 if(comment.equalsIgnoreCase(commentID))
		 {
		   String message =js2.get("fields.comment.comments["+i+"].body").toString();
		   System.out.println(message);
		   Assert.assertEquals( message,expectedMessage );
		 }
			
			
		}
		
		
	}

}
