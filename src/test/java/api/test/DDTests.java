package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "Data")
    public void testPostUser(String userID, String userName, String firstName, String lastName, String email, String password, String phone) {

        User user = new User();

        user.setId(Integer.parseInt(userID));
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        Response response = UserEndPoints.createUser(user);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProviderClass = DataProviders.class, dataProvider = "UserNames")
    public void testDeleteUserByName(String userName){

        Response response = UserEndPoints.deleteUser(userName);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
