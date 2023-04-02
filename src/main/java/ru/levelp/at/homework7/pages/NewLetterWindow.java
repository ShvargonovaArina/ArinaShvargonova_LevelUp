package ru.levelp.at.homework7.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewLetterWindow extends BasePage {
    @FindBy(xpath = "//input[@class = 'container--H9L5q size_s--3_M-_']")
    private WebElement recipientMailField;
    @FindBy(xpath = "//input[@name = 'Subject']")
    private WebElement subjectLetterField;
    @FindBy(xpath = "//*[@role = 'textbox']")
    private WebElement bodyLetterField;
    @FindBy(xpath = "//*[@data-test-id = 'save']")
    private WebElement saveButton;
    @FindBy(xpath = "//*[@data-test-id = 'send']")
    private WebElement sendButton;
    @FindBy(xpath = "//*[@title = 'Закрыть']")
    private WebElement closeWindowButton;

    public NewLetterWindow(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем получателя письма")
    public void fillRecipientMailField(final String recipientMail) {
        sendKeys(recipientMailField, recipientMail);
    }

    @Step("Заполняем тему письма")
    public void fillSubjectLetterField(final String subjectLetter) {
        sendKeys(subjectLetterField, subjectLetter);
    }

    @Step("Заполняем тело письма")
    public void fillBodyLetterField(final String bodyLetter) {
        sendKeys(bodyLetterField, bodyLetter);
    }

    @Step("Нажимаем кнопку сохранения")
    public void clickSaveButton() {
        click(saveButton);
    }

    @Step("Нажимаем кнопку отправки")
    public void clickSendButton() {
        click(sendButton);
    }

    @Step("Нажимаем кнопку закрытия окна")
    public void clickCloseWindowButton() {
        click(closeWindowButton);
    }
}
