/**
 * 
 */
package pojo;

import java.util.List;

/**  THIS IS A MINI JSON FOR COURSES BEACAUSE IT HAS NESTED JSON
 *  SO THIS IS THE PARENT CLASS
 */
public class CourseClass {
//WebAutomation webAutomation;Api api;Mobile mobile; BY DOING THIS I HAVE INJECED
// INFORMATION FRON 3 DIFFERENT CLASSES INTO THIS PARENT CLASS	 
	
//by adding Lsit<> before indicates that expect some data inside
	// of WebAutomation,Api,Mobile or it is array
	
	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;
	
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
	//return api is object of getApi() method	
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
		

}
