package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserendPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {
	Faker faker;
	User UserPayload ;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		faker=new Faker();
		 UserPayload = new User();
		UserPayload.setId(faker.idNumber().hashCode());
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setEmail(faker.internet().safeEmailAddress());
		UserPayload.setPassword(faker.internet().password(5,10));
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
//		
//		logs
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void test_post_user() {
		logger.info("Creating user--------------------");
		Response res=UserendPoints.create_user(UserPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("Successfully Create user info! ");
	}
	@Test(priority=2)
	
	public void test_get_user() {
		logger.info("Reading user--------------------");
		Response res=UserendPoints.read_user(this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("Successfully Read user info! ");
	}
	
@Test(priority=3)
	
	public void test_update_user() {
	logger.info("Updating user--------------------");
		
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setEmail(faker.internet().safeEmailAddress());
		Response res=UserendPoints.update_user(UserPayload,this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		res=UserendPoints.read_user(this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("Successfully Updates user info! ");
	}
	@Test(priority=4)
	
	public void test_delete_user() {
		logger.info("Deleting user--------------------");
		Response res=UserendPoints.read_user(this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("Successfully Deletes user info! ");
	}

}
