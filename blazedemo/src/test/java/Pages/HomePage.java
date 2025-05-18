package Pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    private WebDriver driver;

    private By fromPort = By.name("fromPort");
    private By toPort = By.name("toPort");
    private By FlightsBtn = By.cssSelector("input[type='submit']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFromCity(String city) {
        Select select = new Select(driver.findElement(fromPort));
        select.selectByVisibleText(city);
    }

    public void selectToCity(String city) {
        Select select = new Select(driver.findElement(toPort));
        select.selectByVisibleText(city);
    }

    public void clickFlights() {
        driver.findElement(FlightsBtn).click();
    }
}
