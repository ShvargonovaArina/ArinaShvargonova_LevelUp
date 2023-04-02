package ru.levelp.at.homework7.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    @FindBy(xpath = "//img[@class = 'ph-avatar-img svelte-dfhuqc']")
    private WebElement menuDropdown;
    @FindBy(xpath = "//div[@class = 'ph-desc svelte-1q09i9a']")
    private WebElement userAccount;
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

    public String getAccountName() {
        return wait.until(ExpectedConditions.elementToBeClickable(userAccount)).getText();
    }

    @Step("Открываем окно создания нового письма")
    public void clickNewLetterButton() {
        click(newLetterButton);
    }

    @Step("Раскрываем меню")
    public void clickMenuDropdown() {
        click(menuDropdown);
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
