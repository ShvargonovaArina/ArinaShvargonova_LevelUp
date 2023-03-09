package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

public class TrashPage extends BasePage {

    public TrashPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first"
            + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/*[@class = 'll-crpt']")
    private WebElement addressMailLastTrashLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal"
            + " llc_first llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sj__normal']")
    private WebElement subjectLastTrashLetter;
    @FindBy(xpath = "//*[@class = 'llc llc_normal llc_first llc_new llc_new-selection"
            + " js-letter-list-item js-tooltip-direction_letter-bottom']"
            + "/div/div/div/span/div/*[@class = 'll-sp__normal']")
    private WebElement bodyLastTrashLetter;

    public String getAddressTrashLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(addressMailLastTrashLetter)).getText();
    }

    public String getSubjectTrashLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(subjectLastTrashLetter)).getText();
    }

    public String getBodyTrashLetterText() {
        return wait.until(ExpectedConditions.elementToBeClickable(bodyLastTrashLetter)).getText();
    }

    public void waitForPageLoaded() {
        super.waitForPageLoaded("Корзина");
    }
}

