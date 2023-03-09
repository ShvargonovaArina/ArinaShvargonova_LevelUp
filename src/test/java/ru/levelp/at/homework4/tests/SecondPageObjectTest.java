package ru.levelp.at.homework4.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework4.pages.LoginPage;
import ru.levelp.at.homework4.pages.MainPage;
import ru.levelp.at.homework4.pages.NewLetterWindow;
import ru.levelp.at.homework4.pages.SentPage;
import ru.levelp.at.homework4.pages.TestPage;
import ru.levelp.at.homework4.properties.Properties;
import ru.levelp.at.homework4.suite.TagName;

public class SecondPageObjectTest extends Properties {

    private LoginPage loginPage;
    private MainPage mainPage;
    private NewLetterWindow newLetterWindow;
    private SentPage sentPage;
    private TestPage testPage;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        sentPage = new SentPage(driver);
        testPage = new TestPage(driver);
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    void sendDraft() {
        loginPage.open();
        loginPage.clickFirstEnterButton();
        loginPage.switchLoginFrame();
        loginPage.fillLoginField(login);
        loginPage.clickSecondEnterButton();
        loginPage.fillPasswordField(password);
        loginPage.clickThirdEnterButton();
        var accountName = mainPage.getUserDropdownText();
        assertThat(accountName).isEqualTo(personalMail);

        mainPage.clickNewLetterButton();
        newLetterWindow.fillRecipientMailField(personalMail);
        newLetterWindow.fillSubjectLetterField(personalSubjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseWindowButton();

        mainPage.clickSentSection();
        sentPage.waitForPageLoaded();
        var bodySentLetter = sentPage.getBodyLastLetterText();
        var subjectSentLetter = sentPage.getSubjectLastLetterText();
        var addressSentLetter = sentPage.getAddressMailLastLetterText();
        assertThat(bodySentLetter).contains(bodyLetter);
        assertThat(subjectSentLetter).contains(personalSubjectLetter);
        assertThat(addressSentLetter).isEqualTo(personalMail);

        mainPage.clickTestSection();
        testPage.waitForPageLoaded();
        var bodyTestLetter = testPage.getBodyTestLetterText();
        var subjectTestLetter = testPage.getSubjectTestLetterText();
        var addressTestLetter = testPage.getAddressMailTestLetterText();
        assertThat(bodyTestLetter).contains(bodyLetter);
        assertThat(subjectTestLetter).contains(personalSubjectLetter);
        assertThat(addressTestLetter).isEqualTo(testAccount);

        mainPage.clickUserDropdown();
        mainPage.clickExitButton();
        loginPage.waitForPageLoaded();
    }
}
