/**
 * 
 */
package pojo;

/**
 *  this is a GRNADPARENT CLASS FOR COURSESCLASS
 */
public class GetCourse {
	
	
// in pojo classes this is casual practice to give acess modifier private 
	private String services;
	private String expertise;
//courses is nested json (inside) return type is the class but not string
	//CourseClass is a object of CourseClass class
	private CourseClass courses;
	private String instructor;
	private String linkedIn;
	
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public CourseClass getCourses() {
		return courses;
	}
	public void setCourses(CourseClass courses) {
		this.courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	

	
	
}
