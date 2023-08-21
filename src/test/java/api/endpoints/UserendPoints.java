package api.endpoints;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class UserendPoints {
	
	
public static Response create_user(User payload) {
	Response res=given()
	.accept("application/json")
	.contentType("application/json")
	.body(payload)
	.when()
	.post(Routes.post_url);
	return res;
	
	
}
public static Response read_user(String userName) {
	Response res=given()
			.contentType("application/json")
			.pathParam("username", userName)
			.when()
			.get(Routes.get_url);
	return res;
	
	
}
public static Response update_user(User payload,String userName) {
	Response res=given()
			.contentType("application/json")
			.accept("application/json")
			.pathParam("username", userName)
			.body(payload)
			.when()
			.put(Routes.update_url);
	return res;
}	
	public static Response delete_user(String userName) {
		Response res=given()
				.contentType("application/json")
				.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url);
		return res;
	
}



}
