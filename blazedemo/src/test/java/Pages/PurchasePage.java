package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    private WebDriver driver;

    private By inputName = By.id("inputName");
    private By address = By.id("address");
    private By city = By.id("city");
    private By state = By.id("state");
    private By zipCode = By.id("zipCode");
    private By creditCardNumber = By.id("creditCardNumber");
    private By CardName = By.id("nameOnCard");
    private By FlightBtn = By.cssSelector("input[type='submit']");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void PassengerDetails(String name, String addr, String cityName, String stateName, String zip, String cardNumber, String cardName) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(state).sendKeys(stateName);
        driver.findElement(zipCode).sendKeys(zip);
        driver.findElement(creditCardNumber).sendKeys(cardNumber);
        driver.findElement(CardName).sendKeys(cardName);
    }

    public void clickPurchaseFlight() {
        driver.findElement(FlightBtn).click();
    }
}

