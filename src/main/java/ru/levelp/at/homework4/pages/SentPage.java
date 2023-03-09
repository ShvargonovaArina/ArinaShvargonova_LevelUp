package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

public class SentPage extends BasePage {

    public SentPage(WebDriver driver) {

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

    public String getAddressMailLastLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(addressMailLastLetter)).getText();
    }

    public String getSubjectLastLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(subjectLastLetter)).getText();
    }

    public String getBodyLastLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(bodyLastLetter)).getText();
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Отправленные");
    }
}
