package assured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload2;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void Sumofcourses()
	
	{
		//this is for sum of all
		int sum = 0;
		JsonPath js = new JsonPath(Payload2.coursesprice());
	      int count =js.getInt("courses.size()");
	      
	      for(int i=0;i<count;i++)
	      {
	          int price =js.getInt("courses["+i+"].price");
	          int copies =js.getInt("courses["+i+"].copies");
	          int amount =price*copies;
	          System.out.println(amount);
	          
	   //this sum will work in loop after every iteration previous amt will added to sum
	           sum =sum+amount;
	            
	      }
	      System.out.println(sum);
	     int purchaseAmt = js.getInt("dashboard.purchaseAmount");
	     Assert.assertEquals(sum, purchaseAmt);
	      
	      

	}

}
