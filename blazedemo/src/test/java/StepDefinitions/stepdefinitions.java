package StepDefinitions;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class stepdefinitions extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testFlightBookingFlowWithValidations() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

   
        HomePage homePage = new HomePage(driver);

        Select fromSelect = new Select(driver.findElement(By.name("fromPort")));
        List<String> fromOptions = fromSelect.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(fromOptions.contains("Boston"), "'From' dropdown should contain Boston");
        Assert.assertTrue(fromOptions.contains("Paris"), "'From' dropdown should contain New York");

        Select toSelect = new Select(driver.findElement(By.name("toPort")));
        List<String> toOptions = toSelect.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(toOptions.contains("London"), "'To' dropdown should contain London");
        Assert.assertTrue(toOptions.contains("Rome"), "'To' dropdown should contain Rome");

        
        homePage.selectFromCity("Boston");
        homePage.selectToCity("New York");
        homePage.clickFlights();

       
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.table")));

        List<WebElement> priceCells = driver.findElements(By.xpath("//table//tr/td[6]")); // price column
        Assert.assertFalse(priceCells.isEmpty(), "Flight prices should be listed");

        for (WebElement priceCell : priceCells) {
            String priceText = priceCell.getText().replace("$", "").trim();
            double price = Double.parseDouble(priceText);
            Assert.assertTrue(price > 0, "Flight price should be greater than zero");
        }

        ChooseFlightPage chooseFlightPage = new ChooseFlightPage(driver);
        chooseFlightPage.choose_First_Flight();

   
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputName")));

        PurchasePage purchasePage = new PurchasePage(driver);

       
        Assert.assertEquals(driver.findElement(By.id("inputName")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("address")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("city")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("state")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("zipCode")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("creditCardNumber")).getAttribute("value"), "");
        Assert.assertEquals(driver.findElement(By.id("nameOnCard")).getAttribute("value"), "");

     
        purchasePage.PassengerDetails(
                "Prajyoti Surywanshi", "Nandi Stop", "Latur", "Maharashtra", "413512", "123456789", "Prajyoti Surywanshi");

       
        Assert.assertEquals(driver.findElement(By.id("inputName")).getAttribute("value"), "Prajyoti Surywanshi");
        Assert.assertEquals(driver.findElement(By.id("address")).getAttribute("value"), "Nandi Stop");

        purchasePage.clickPurchaseFlight();

    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Thank you for your purchase today!']")));

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        Assert.assertTrue(confirmationPage.isConfirmationDisplayed(), "Confirmation message should be displayed.");

        String confId = confirmationPage.getConfirmationId();
        Assert.assertNotNull(confId, "Confirmation ID should not be null");
        Assert.assertFalse(confId.isEmpty(), "Confirmation ID should not be empty");

       test.get().info("Flight booking flow with validations passed successfully.");
    }
}

