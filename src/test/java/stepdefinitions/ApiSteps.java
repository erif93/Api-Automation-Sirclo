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
	
	@Given("^user send \"([^\"]*)\" request to \"([^\"]*)\" endpoint$")
	public void sendGetRequest(String state, String domain) {
		  objAPI = new ApiPages(request,response);
		  response = objAPI.sendRequest(state, domain);
	}
	
	@Given("^user send \"([^\"]*)\" request with \"([^\"]*)\" login credential$")
	public void sendPostRequest(String state, String domain) {
		 objAPI = new ApiPages(request,response);
		 response = objAPI.sendRequest(state, domain);
	}
	
	@Then("user should get response \"([^\"]*)\"$")
	public void validateTextResponse(String text) {
		objAPI = new ApiPages(request,response);
		objAPI.validateHomepageResponse(text, response);
	}
	
	@Then("user should get empty login response \"([^\"]*)\"$")
	public void validateLoginResponse(String text) {
		objAPI = new ApiPages(request,response);
		objAPI.validateEmptyLoginResponse(text, response);
	}
	
	@Then("user should get wrong login response \"([^\"]*)\"$")
	public void validateWrongLoginResponse(String text) {
		objAPI = new ApiPages(request,response);
		objAPI.validatePlainText(text, response);
	}
}
