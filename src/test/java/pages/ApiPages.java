package pages;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;
import org.testng.Assert;
import io.restassured.parsing.Parser;
import static org.junit.Assert.assertEquals;


public class ApiPages {
	

	RequestSpecification request;
    Response response;
    String base="http://qa-interview.srcli.xyz";
    String endPoint ="";
	
	public ApiPages(RequestSpecification request, Response response) {
			this.request=request;
			this.response=response;
			
	}
	
	
	public Response sendRequest(String state, String domain) {
		RestAssured.baseURI = base;
		request= RestAssured.given();
		System.out.println("di atas bos");
		if(state.equals("GET")) {
			switch (domain) {
	         case "homepage":
	            response = request.get("/");
	            break;
	          case "data":
	        	response =request.get("/data");
	            break;
	           case "checklogin":
	        	response =request.get("/login");
	            break;
	           case "unknown":
	        	response =request.get("/testacbdasd");
	           default:
	            throw new IllegalArgumentException();
			}
		} else {
			switch (domain) {
	          case "valid":
	            endPoint = "/login";
	            response = request.queryParam("username","root").queryParam("password","root123").post(endPoint);
	            break;
	           case "incorrect":
	           	endPoint = "/login";
	           	response = request.queryParam("username","root").queryParam("password","root122313").post(endPoint);
	            break;
	           case "empty":
	        	 response = request.get("/login");
	             break;
	           case "logout":
	        	 endPoint = "/logout";
	        	 request.coo
	        	 response = request.post(endPoint);
	        	 break;
	           default:
	           	throw new IllegalArgumentException();
	        }
		}
		return response;
	 }
	
	public void validateHomepageResponse(String text, Response response) {
		XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
		System.out.println(response.getStatusCode());
		if(response.getStatusCode()== 307) {
			System.out.println("Already logged in");
		} else {
			assertEquals(response.getStatusCode(),200);
			String expectedText = htmlPath.getString("html.body.h1").trim();
			Assert.assertEquals(expectedText, text);
		}
		
	}
	
	public void validateEmptyLoginResponse(String text, Response response) {
		XmlPath htmlPath = new XmlPath(HTML, response.getBody().asString());
		assertEquals(response.getStatusCode(),200);
		String expectedText = htmlPath.getString("html.body.form.@action").trim();
		Assert.assertEquals(expectedText, text);
	}
	
	public void validatePlainText(String text, Response response) {
		RestAssured.registerParser("text/plain", Parser.TEXT);
		assertEquals(response.getStatusCode(),307);
		String expectedText = response.then().extract().body().asString();
		System.out.println(expectedText);
		Assert.assertEquals(expectedText, text);
	}
 }
