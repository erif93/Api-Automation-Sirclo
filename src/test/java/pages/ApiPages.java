package pages;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import static org.junit.Assert.assertEquals;

public class ApiPages {
	

	private RequestSpecification request;
	private String jsonString = "";
	private Response response;
	
	public ApiPages(RequestSpecification request, Response response) {
			this.request=request;
			this.response=response;
			
	}
	
	public RequestSpecification setBaseURI(String base) {
		
		RestAssured.baseURI = base;
		request= RestAssured.given();
		return request;
	}
	
	public Response sendRequest(RequestSpecification request, String endPoint, String state, String status) {
		
		if(state.toLowerCase()=="get") {
			request.get(endPoint);
		} else {
			 switch (status) {
	           case "valid":
	               endPoint = endPoint+"?username=root&password=root123";
	               request.post(endPoint);
	               break;
	            case "incorrect":
	            	endPoint = endPoint+"?username=root&password=root12345";
	            	 request.post(endPoint);
	                break;
	            case "empty":
	            	request.get(endPoint);
	                break;
	            default:
	            	throw new IllegalArgumentException();
	        }
		}
		return response;
	 }
	
	public void validateResponse(String text, Response response) {
		XmlPath htmlPath = new XmlPath(HTML, response.asString());
		assertEquals(htmlPath.getString("html.h1"),text);
	}
 }
