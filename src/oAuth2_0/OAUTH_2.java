package oAuth2_0;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAUTH_2 {

	public static void main(String[] args) {
		
		
		
		
		String acessTokenResponse = given().queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		
				//given all these querryParams justlike in post man refer to 2.0 postman oauth
			.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type","authorization_code")
			.queryParams("code","ya29.a0AXooCguFg2laKc6PFaAYBwEqvTAp3qDbicsR2ujvDQxgKaGpBeCEeyw1AzzerymURIC3V-0")
		
			.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		// enables you to easily navigate and extract data from the JSON structure using the JsonPath library
		JsonPath js = new JsonPath(acessTokenResponse);
		String extractedAcessToken = js.getString(acessTokenResponse);
			
		
		
		String response = given().queryParam("access_token",extractedAcessToken)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	
	}

}
