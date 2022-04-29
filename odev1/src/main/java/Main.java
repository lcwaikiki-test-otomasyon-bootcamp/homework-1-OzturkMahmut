import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import  org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {

        //Setting options to avoid automation detection
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); //Removes the detection
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //Removes the banner

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);   //Create the driver with the options

        Actions actions = new Actions(driver);  //Instantiating Actions class to be able to perform hover action

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.lcwaikiki.com/tr-TR/TR"); //Go to the homepage
        WebElement loginButtonHome = driver.findElement(By.cssSelector("span.user-wrapper .dropdown-label"));   //Locate the login button
        actions.moveToElement(loginButtonHome).perform(); //Hover on the login button

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href=\"https://www.lcwaikiki.com/tr-TR/TR/uye-ol\"]"));  //Locate the signup button
        signUpButton.click();

        WebElement emailForm = driver.findElement(By.cssSelector("[id=\"RegisterFormView_RegisterEmail\"]"));   //Locate the email form
        emailForm.sendKeys("wrong_mail_format");    //Fill out the email form with wrong format

        WebElement emailFormError = driver.findElement(By.cssSelector("[id=\"RegisterFormView_RegisterEmail-error\"]"));    //Locate the email form error
        System.out.println("Case 1 Email Error: " + emailFormError.getText());  //Print the error to the console

        WebElement passwordForm = driver.findElement(By.cssSelector("[id=\"RegisterFormView_Password\"]"));     //Locate the password form
        passwordForm.sendKeys("");  //Fill out the password form with wrong format

        WebElement emptySpace = driver.findElement(By.cssSelector("[id=\"RegisterForm\"]"));    //Click outside the form to get the error
        emptySpace.click();

        WebElement passwordFormError = driver.findElement(By.cssSelector("[id=\"RegisterFormView_Password-error\"]"));  //Locate the password form error
        System.out.println("Case 2 Password Error: " + passwordFormError.getText());    //Print the error to the console

        passwordForm.sendKeys("111");   //Fill out the password form with other wrong format
        passwordForm.click();
        emptySpace.click();     //Click outside the form to get the error

        WebElement passwordFormError2 = driver.findElement(By.cssSelector("[id=\"RegisterFormView_Password-error\"]")); //Locate the password form error
        System.out.println("Case 3 Password Error 2: " + passwordFormError2.getText()); //Print the error to the console

    }


}
