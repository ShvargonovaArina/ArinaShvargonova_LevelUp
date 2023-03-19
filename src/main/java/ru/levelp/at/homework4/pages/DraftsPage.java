package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

public class DraftsPage extends BasePage {

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first"
            + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/*[@class = 'll-crpt']")
    private WebElement addressMailLastLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal"
            + " llc_first llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sj__normal']")
    private WebElement subjectLastLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first"
            + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sp__normal']")
    private WebElement bodyLastLetter;
    @FindBy(xpath = "//*[@class = 'button2__ico']")
    private WebElement closeFoldedDraftButton;
    @FindBy(xpath = "//*[@data-test-id = 'send']")
    private WebElement sendButton;
    @FindBy(xpath = "//*[@class ='ico ico_16-close ico_size_s']")
    private WebElement confirmationWindowCloseButton;

    public String getAddressMailLastLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(addressMailLastLetter)).getText();
    }

    public String getSubjectLastLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(subjectLastLetter)).getText();
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Черновики");
    }

    public String getBodyLastLetterText() {
        return getText(bodyLastLetter);
    }

    public void openLastDraft() {
        click(bodyLastLetter);
    }

    public void clickSendButton() {
        click(sendButton);
    }

    public void clickCloseFoldedDraftButton() {
        click(closeFoldedDraftButton);
    }

    public void clickConfirmationWindowCloseButton() {
        click(confirmationWindowCloseButton);
    }
}
