package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelp.at.homework4.inheritance.BasePage;

public class LoginPage extends BasePage {
    private static final String MAIL_URL = "https://mail.ru/";
    @FindBy(xpath = "//*[@class = 'ag-popup__frame__layout__iframe']")
    private WebElement loginFrame;
    @FindBy(xpath = "//*[@data-testid = 'enter-mail-primary']")
    private WebElement firstEnterButton;
    @FindBy(xpath = "//*[@class = 'submit-button-wrap']")
    private WebElement secondEnterButton;
    @FindBy(xpath = "//button[@data-test-id = 'submit-button']")
    private WebElement thirdEnterButton;
    @FindBy(xpath = "//*[@name = 'username']")
    private WebElement loginField;
    @FindBy(xpath = "//*[@name = 'password']")
    private WebElement passwordField;

    public LoginPage(final WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to(MAIL_URL);
    }

    public void switchLoginFrame() {
        driver.switchTo().frame(loginFrame);
    }

    public void clickFirstEnterButton() {
        click(firstEnterButton);
    }

    public void clickSecondEnterButton() {
        click(secondEnterButton);
    }

    public void clickThirdEnterButton() {
        click(thirdEnterButton);
    }

    public void fillLoginField(final String login) {
        sendKeys(loginField, login);
    }

    public void fillPasswordField(final String password) {
        sendKeys(passwordField, password);
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Mail.ru: почта, поиск в интернете, новости, игры");
    }
}
