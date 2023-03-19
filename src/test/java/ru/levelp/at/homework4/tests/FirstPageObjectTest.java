package ru.levelp.at.homework4.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework4.base.BaseTest;
import ru.levelp.at.homework4.pages.DraftsPage;
import ru.levelp.at.homework4.pages.LoginPage;
import ru.levelp.at.homework4.pages.MainPage;
import ru.levelp.at.homework4.pages.NewLetterWindow;
import ru.levelp.at.homework4.pages.SentPage;
import ru.levelp.at.homework4.suite.TagName;

public class FirstPageObjectTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private NewLetterWindow newLetterWindow;
    private DraftsPage draftsPage;
    private SentPage sentPage;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        draftsPage = new DraftsPage(driver);
        sentPage = new SentPage(driver);
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    void sendDraft() {
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
        newLetterWindow.fillRecipientMailField(recipientMail);
        newLetterWindow.fillSubjectLetterField(subjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSaveButton();
        newLetterWindow.clickCloseWindowButton();

        mainPage.clickDraftsSection();
        draftsPage.waitForPageLoaded();
        var bodyDraft = draftsPage.getBodyLastLetterText();
        var subjectDraft = draftsPage.getSubjectLastLetterText();
        var addressDraft = draftsPage.getAddressMailLastLetterText();
        assertThat(bodyDraft).contains(bodyLetter);
        assertThat(subjectDraft).isEqualTo(subjectLetter);
        assertThat(addressDraft).contains(recipientMail);

        draftsPage.openLastDraft();
        draftsPage.clickCloseFoldedDraftButton();
        draftsPage.clickSendButton();
        draftsPage.clickConfirmationWindowCloseButton();
        mainPage.clickSentSection();
        sentPage.waitForPageLoaded();
        var bodySentLetter = sentPage.getBodyLastLetterText();
        var subjectSentLetter = sentPage.getSubjectLastLetterText();
        var addressSentLetter = sentPage.getAddressMailLastLetterText();
        assertThat(bodySentLetter).contains(bodyLetter);
        assertThat(subjectSentLetter).isEqualTo(subjectLetter);
        assertThat(addressSentLetter).isEqualTo(recipientMail);
        mainPage.clickUserDropdown();
        mainPage.clickExitButton();
        loginPage.waitForPageLoaded();
    }
}
