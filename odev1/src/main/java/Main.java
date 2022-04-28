import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import  org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        //setting options to avoid automation detection
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); //removes the detection
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //removes the banner

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);   //create the driver with the options

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.lcwaikiki.com/tr-TR/TR"); //go to the homepage
        WebElement loginButtonHome = driver.findElement(By.cssSelector("span.user-wrapper .dropdown-label")); //find the login button
        loginButtonHome.click(); //click the login button

        WebElement emailContainer = driver.findElement(By.cssSelector(".login-form .text-input[name=\"email\"]")); //find the email form
        emailContainer.sendKeys("wrong@gmail.com"); // fill out the form with nonexistent user mail

        WebElement passwordContainer = driver.findElement(By.cssSelector(".login-form .text-input[name=\"password\"]")); //find the password form
        passwordContainer.sendKeys("wrong_password");    // fill out the form

        WebElement loginButton = driver.findElement(By.cssSelector(".login-form__button")); //find the login button
        loginButton.submit();   //click the login button

        WebElement error = driver.findElement(By.cssSelector(".login-form__header-errors--p")); //find the error
        System.out.println("Error: " + error.getText());    //print the error to the console

    }


}
