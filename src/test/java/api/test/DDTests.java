package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserendPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	
	public void test_post_user(String userId,String userName,String fname,String lname,String email,String password,String ph) {
		
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(ph);
		
		Response res=UserendPoints.create_user(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		
	}
//	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	
	public void test_get_user(String useName) {
		Response res=UserendPoints.read_user(useName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
