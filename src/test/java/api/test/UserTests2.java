package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;

	public Logger logger;

	@BeforeClass
	public void setup() {

		System.out.println("print ==> setup data");

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setUsername(faker.name().username());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("****** TC1_PostUser- Creating user *******");
		//System.out.println("print ==> test post user");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("****** TC1_PostUser- User is created *******");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("****** TC2_GetUserByName- Reading user info*******");
		//System.out.println("print ==> test get user");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("****** TC2_GetUserByName- User info is displayed *******");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		logger.info("****** TC3_UpdateUserByName- Updating user *******");
		//System.out.println("print ==> test update user");
		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("****** TC3_UpdateUserByName- User is updated *******");
		
		// checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();

		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("****** TC4_DeleteUserByName- Deleting user *******");
		//System.out.println("print ==> test delete user");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("****** TC4_DeleteUserByName- User deleted *******");
		
		// checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();

	}

}
