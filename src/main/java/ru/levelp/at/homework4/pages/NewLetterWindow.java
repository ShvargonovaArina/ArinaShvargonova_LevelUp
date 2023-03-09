package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

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

    public void fillRecipientMailField(final String recipientMail) {
        sendKeys(recipientMailField, recipientMail);
    }

    public void fillSubjectLetterField(final String subjectLetter) {
        sendKeys(subjectLetterField, subjectLetter);
    }

    public void fillBodyLetterField(final String bodyLetter) {
        sendKeys(bodyLetterField, bodyLetter);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void clickSendButton() {
        click(sendButton);
    }

    public void clickCloseWindowButton() {
        click(closeWindowButton);
    }
}
