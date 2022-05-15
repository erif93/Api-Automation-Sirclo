package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.ApiPages;

public class ApiSteps {
	
	String baseURI="http://qa-interview.srcli.xyz";
	ApiPages objAPI;
	RequestSpecification request;
	Response response;
	
	@Given("^user send \"([^\"]*)\" request to \"([^\"]*)\"  endpoint$")
	public void sendGetRequest(RequestSpecification request,String state, String endPoint, String status) {
		  objAPI = new ApiPages(request,response);
		  response = objAPI.sendRequest(request, endPoint, state, status);
	}
	
	@Given("^user send \"([^\"]*)\" request to \"([^\"]*)\" endpoint with \"([^\"]*)\" credential$")
	public void sendPostRequest(RequestSpecification request,String state, String endPoint, String status) {
		  objAPI = new ApiPages(request,response);
		  response = objAPI.sendRequest(request, endPoint, state, status);
	}
	
	@Then("user should get response \"([^\"]*)\"$")
	public void validateTextResponse(String text) {
		objAPI = new ApiPages(request,response);
		objAPI.validateResponse(text, response);
	}
}
