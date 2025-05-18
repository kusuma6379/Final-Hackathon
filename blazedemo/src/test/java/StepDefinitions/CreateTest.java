package StepDefinitions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTest extends BaseTest {

    @Test
    public void verifyTitle() {
    	
        String title = driver.getTitle();
        test.get().info("Page title is: " + title);
        Assert.assertTrue(title.contains("BlazeDemo"));
        test.get().pass("Verified title contains 'BlazeDemo'");
   }
}