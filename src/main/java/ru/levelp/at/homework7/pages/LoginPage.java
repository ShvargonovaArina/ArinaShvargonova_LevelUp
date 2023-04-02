package ru.levelp.at.homework7.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Заполняем поле 'Логин'")
    public void fillLoginField(final String login) {
        sendKeys(loginField, login);
    }

    public void clickFirstEnterButton() {
        click(firstEnterButton);
    }

    public void clickSecondEnterButton() {
        click(secondEnterButton);
    }

    @Step("Заполняем поле 'Пароль'")
    public void fillPasswordField(final String password) {
        sendKeys(passwordField, password);
    }

    @Step("Нажимаем на кнопку входа")
    public void clickThirdEnterButton() {
        click(thirdEnterButton);
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Mail.ru: почта, поиск в интернете, новости, игры");
    }
}
