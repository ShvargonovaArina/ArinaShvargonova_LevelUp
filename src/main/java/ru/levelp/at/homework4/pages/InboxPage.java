package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

public class InboxPage extends BasePage {

    public InboxPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first"
            + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/*[@class = 'll-crpt']")
    private WebElement addressMailLastInboxLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first llc_new llc_new-selection"
            + " js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sj__normal']")
    private WebElement subjectLastInboxLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first"
            + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sp__normal']")
    private WebElement bodyLastInboxLetter;
    @FindBy(xpath = "//*[@href = '/tomyself/?']")
    private WebElement myselfLetter;
    @FindBy(xpath = "//*[@data-title-shortcut = 'Del']")
    private WebElement deleteButton;


    public String getAddressInboxLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(addressMailLastInboxLetter)).getText();
    }

    public String getSubjectInboxLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(subjectLastInboxLetter)).getText();
    }

    public String getBodyInboxLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(bodyLastInboxLetter)).getText();
    }

    public void clickMyselfLetter() {
        click(myselfLetter);
    }

    public void clickInboxLetter() {
        click(bodyLastInboxLetter);
    }

    public void clickDeleteButton() {
        click(deleteButton);
    }

    public void waitForMyselfPageLoaded() {
        super.waitForPageLoaded("Письма себе");
    }
}




