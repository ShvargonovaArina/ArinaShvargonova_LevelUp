package ru.levelp.at.homework4.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework4.base.BaseTest;
import ru.levelp.at.homework4.pages.InboxPage;
import ru.levelp.at.homework4.pages.LoginPage;
import ru.levelp.at.homework4.pages.MainPage;
import ru.levelp.at.homework4.pages.NewLetterWindow;
import ru.levelp.at.homework4.pages.TrashPage;
import ru.levelp.at.homework4.suite.TagName;

public class ThirdPageObjectTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private NewLetterWindow newLetterWindow;
    private InboxPage inboxPage;
    private TrashPage trashPage;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        inboxPage = new InboxPage(driver);
        trashPage = new TrashPage(driver);
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    void deleteInboxLetter() {
        loginPage.open();
        loginPage.clickFirstEnterButton();
        loginPage.switchLoginFrame();
        var login = properties.getProperty("login");
        loginPage.fillLoginField(login);
        loginPage.clickSecondEnterButton();
        var password = properties.getProperty("password");
        loginPage.fillPasswordField(password);
        loginPage.clickThirdEnterButton();
        var accountName = mainPage.getUserDropdownText();
        assertThat(accountName).isEqualTo(personalMail);

        mainPage.clickNewLetterButton();
        newLetterWindow.fillRecipientMailField(personalMail);
        newLetterWindow.fillSubjectLetterField(subjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseWindowButton();

        inboxPage.clickMyselfLetter();
        inboxPage.waitForMyselfPageLoaded();
        var bodyInboxLetter = inboxPage.getBodyInboxLetterText();
        var subjectInboxLetter = inboxPage.getSubjectInboxLetterText();
        var addressInboxLetter = inboxPage.getAddressInboxLetterText();
        assertThat(bodyInboxLetter).contains(bodyLetter);
        assertThat(subjectInboxLetter).contains(subjectLetter);
        assertThat(addressInboxLetter).isEqualTo(testAccount);
        inboxPage.clickInboxLetter();
        inboxPage.clickDeleteButton();

        mainPage.clickTrashSection();
        trashPage.waitForPageLoaded();
        var bodyTrashLetter = trashPage.getBodyTrashLetterText();
        var subjectTrashLetter = trashPage.getSubjectTrashLetterText();
        var addressTrashLetter = trashPage.getAddressTrashLetterText();
        assertThat(bodyTrashLetter).contains(bodyLetter);
        assertThat(subjectTrashLetter).contains(subjectLetter);
        assertThat(addressTrashLetter).isEqualTo(testAccount);

        mainPage.clickUserDropdown();
        mainPage.clickExitButton();
        loginPage.waitForPageLoaded();
    }
}
