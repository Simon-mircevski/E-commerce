package veb_prog.veb_prog.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import javax.xml.bind.SchemaOutputResolver;

public class LoginPage extends AbstractPage{

    private WebElement username;

    private WebElement password;

    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(WebDriver driver, String username, String password){
        get(driver,"/login");
        System.out.println(driver.getCurrentUrl());
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
    }

}
