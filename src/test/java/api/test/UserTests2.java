package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {

    Faker faker;
    User userPayload;

    public Logger logger;   // for logs

    @BeforeClass
    public void setup(){

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        // logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {

        logger.info("***************** Creating user *****************");

        Response response = UserEndPoints2.createUser(userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***************** User is created *****************");
    }

    @Test(priority = 2)
    public void testGetUserByName() {

        logger.info("***************** Reading user info *****************");

        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***************** User info is displayed *****************");
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {

        logger.info("***************** Updating user *****************");

        // update data using payload

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***************** User is updated *****************");

        // checking data after update

        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());

        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {

        logger.info("***************** Deleting user *****************");

        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***************** User is deleted *****************");
    }
}
