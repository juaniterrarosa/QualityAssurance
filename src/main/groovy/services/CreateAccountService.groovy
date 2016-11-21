package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;

@Singleton
public class CreateAccountService {

    static final String NEO_URL = "http://neo-account.preprod1.grindr.com/";
    static final String HOME_SELECTION = "Create Account";
    static final String COMMIT_CA_FORM = "commit";

    static final String ID_ERROR = "flash_error";
    static final String ID_CAPTCHA_ERROR = "recaptcha_response_field";
    static final String ID_EMAIL = "user_email";
    static final String ID_PASS = "user_password";

    static final String CAPTCHA_ERROR = "You typed the CAPTCHA words incorrectly. Please try again.";
    static final String PASS_ERROR = "Password is too short (minimum is 6 characters)";
    static final String EMPTY_PASS_ERROR = "Password can't be blank";
    static final String EMAIL_ERROR = "Email is invalid";

    static final String OK_EMAIL = "juan.terrarosa@grindr.com";
    static final String OK_PASS = "123456";

    static final String WRONG_EMAIL = "ab";
    static final String WRONG_PASS = "123";
    static final String WRONG_CAPTCHA = "ab";

    public static WebDriver driver;

    public void startBrowser(){
        driver = new FirefoxDriver();
    }

    public void getHome () {
        startBrowser();
        driver.get(NEO_URL);
        driver.manage().window().maximize();
    }

    public void selectCreateAccount() {
        WebElement logInButton = driver.findElement(By.linkText(HOME_SELECTION));
        logInButton.click();
    }

    public void commitForm(){
        WebElement logInButton = driver.findElement(By.name(COMMIT_CA_FORM));
        logInButton.click();
    }

    public void checkError(String errorType){
        WebElement message = driver.findElement(By.id(ID_ERROR));
        String text = "";
        switch (errorType) {
            case "emptyEmail": text = EMAIL_ERROR; break;
            case "invalidEmail": text = EMAIL_ERROR; break;
            case "emptyPassword": text = EMPTY_PASS_ERROR; break;
            case "invalidPassword": text = PASS_ERROR; break;
            case "Captcha": text = CAPTCHA_ERROR; break;
        }
        assertEquals(text, message.getText());
    }

    public void setMail(String emailField){
        String text = "";
        switch (emailField) {
            case "emptyEmail": text = ""; break;
            case "invalidEmail": text = WRONG_EMAIL; break;
            case "correctEmail": text = OK_EMAIL; break;
        }
        driver.findElement(By.id(ID_EMAIL)).sendKeys(text);
    }

    public void setPass(String passField){
        String text = "";
        switch (passField) {
            case "emptyPassword": text = ""; break;
            case "invalidPassword": text = WRONG_PASS; break;
            case "correctPassword": text = OK_PASS; break;
        }
        driver.findElement(By.id(ID_PASS)).sendKeys(text);
    }

    public void setInvalidCaptcha(){
        driver.findElement(By.id(ID_CAPTCHA_ERROR)).sendKeys(WRONG_CAPTCHA);
    }

    public void closeDriver(){
        driver.close();
    }
}