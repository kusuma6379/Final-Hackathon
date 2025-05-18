package Pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private WebDriver driver;

    private By confirmationMessage = By.xpath("//h1[text()='Thank you for your purchase today!']");
    private By confirmationId = By.xpath("//table//td[text()='Id']/following-sibling::td");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isConfirmationDisplayed() {
        return driver.findElements(confirmationMessage).size() > 0;
    }

    public String getConfirmationId() {
        return driver.findElement(confirmationId).getText();
    }
}
