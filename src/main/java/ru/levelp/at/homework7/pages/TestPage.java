package ru.levelp.at.homework7.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestPage extends BasePage {

    public TestPage(WebDriver driver) {

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
    @FindBy(xpath = "//*[@data-title-shortcut = 'Del']")
    private WebElement deleteButton;

    public String getAddressMailTestLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(addressMailLastLetter)).getText();
    }

    public String getSubjectTestLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(subjectLastLetter)).getText();
    }

    public String getBodyTestLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(bodyLastLetter)).getText();
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Тест");
    }
}
