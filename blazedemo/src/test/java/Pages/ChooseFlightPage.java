package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseFlightPage {

    private WebDriver driver;

    
    private By FirstFlightBtn = By.cssSelector("input[type='submit']");

    public ChooseFlightPage(WebDriver driver) {
        this.driver = driver;
    }

    public void choose_First_Flight() {
        driver.findElement(FirstFlightBtn).click();
    }
}

