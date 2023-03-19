package ru.levelp.at.homework4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework4.inheritance.BasePage;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[@class = 'ph-project__user-name svelte-1hiqrvn']")
    private WebElement userDropdown;
    @FindBy(xpath = "//*[@data-testid = 'whiteline-account-exit']")
    private WebElement exitButton;

    // Sections
    @FindBy(xpath = "//a[@href = '/compose/']")
    private WebElement newLetterButton;
    @FindBy(xpath = "//div[@id = 'sideBarContent']/div/nav/a[@href = '/sent/?']")
    private WebElement sentSection;
    @FindBy(xpath = "//div[@id = 'sideBarContent']/div/nav/a[@href = '/drafts/?']")
    private WebElement draftsSection;
    @FindBy(xpath = "//*[@href = '/1/?']")
    private WebElement testSection;
    @FindBy(xpath = "//*[@href = '/trash/?']")
    private WebElement trashSection;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getUserDropdownText() {
        return wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).getText();
    }

    public void clickNewLetterButton() {
        click(newLetterButton);
    }

    public void clickUserDropdown() {
        click(userDropdown);
    }

    public void clickExitButton() {
        click(exitButton);
    }

    public void clickDraftsSection() {
        click(draftsSection);
    }

    public void clickSentSection() {
        click(sentSection);
    }

    public void clickTestSection() {
        click(testSection);
    }

    public void clickTrashSection() {
        click(trashSection);
    }
}
