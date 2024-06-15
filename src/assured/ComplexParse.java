package assured;


import files.Payload2;
import io.restassured.path.json.JsonPath;

public class ComplexParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload2.coursesprice());
		
		
	// this .size() can be applied only on array courses are in array
      int count =js.getInt("courses.size()");
      System.out.println(count);
      
    int purchaseAmt =js.getInt("dashboard.purchaseAmount");
    System.out.println(purchaseAmt);

   String title = js.get("courses[1].title");
   System.out.println(title);
   
   //print all course titles and their prices
     
   // this loop will help in printing all the titles
      for(int i=0;i<count;i++)
      {
    	  // to insert a variable in string this is how we rite it "+i+"
    	String courseTitles =js.get("courses["+i+"].title");
        int Cprices =js.getInt("courses["+i+"].price");
        
      // this is also we can sysout print  
        System.out.println(js.get("courses["+i+"].price").toString());
        
        System.out.println(courseTitles);
    	//System.out.println(Cprices);
        
        //for stoping loop
        break;
    	  
    	  
      }
     
   

	}

}
