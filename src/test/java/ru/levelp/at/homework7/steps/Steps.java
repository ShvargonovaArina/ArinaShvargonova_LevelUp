package ru.levelp.at.homework7.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.homework7.pages.DraftsPage;
import ru.levelp.at.homework7.pages.InboxPage;
import ru.levelp.at.homework7.pages.LoginPage;
import ru.levelp.at.homework7.pages.MainPage;
import ru.levelp.at.homework7.pages.NewLetterWindow;
import ru.levelp.at.homework7.pages.SentPage;
import ru.levelp.at.homework7.pages.TestPage;
import ru.levelp.at.homework7.pages.TrashPage;

public class Steps {
    private final LoginPage loginPage;
    private final MainPage mainPage;
    private final NewLetterWindow newLetterWindow;
    private final InboxPage inboxPage;
    private final DraftsPage draftsPage;
    private final SentPage sentPage;
    private final TestPage testPage;
    private final TrashPage trashPage;

    public Steps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.mainPage = new MainPage(driver);
        this.newLetterWindow = new NewLetterWindow(driver);
        this.inboxPage = new InboxPage(driver);
        this.sentPage = new SentPage(driver);
        this.draftsPage = new DraftsPage(driver);
        this.testPage = new TestPage(driver);
        this.trashPage = new TrashPage(driver);
    }

    public void openSite() {
        loginPage.open();
    }

    @Step("Авторитизируемся")
    public void logIn(String login, String password) {
        loginPage.clickFirstEnterButton();
        loginPage.switchLoginFrame();
        loginPage.fillLoginField(login);
        loginPage.clickSecondEnterButton();
        loginPage.fillPasswordField(password);
        loginPage.clickThirdEnterButton();
    }

    @Step("Проверяем имя аккаунта")
    public void checkAccountName(String personalMail) {
        mainPage.clickMenuDropdown();
        var accountName = mainPage.getAccountName();
        assertThat(accountName).isEqualTo(personalMail);
    }

    @Step("Создаем черновик")
    public void createDraft(String recipientMail, String subjectLetter, String bodyLetter) {
        mainPage.clickNewLetterButton();
        newLetterWindow.fillRecipientMailField(recipientMail);
        newLetterWindow.fillSubjectLetterField(subjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSaveButton();
        newLetterWindow.clickCloseWindowButton();
    }

    @Step("Открываем черновик")
    public void openDraft() {
        draftsPage.openLastDraft();
        draftsPage.clickCloseFoldedDraftButton();
    }

    @Step("Открываем раздел 'Черновики'")
    public void openDraftSection() {
        mainPage.clickDraftsSection();
        draftsPage.waitForPageLoaded();
    }

    @Step("Проверяем, что черновик создался")
    public void checkDraft(String recipientMail, String subjectLetter, String bodyLetter) {
        var bodyDraft = draftsPage.getBodyLastLetterText();
        var subjectDraft = draftsPage.getSubjectLastLetterText();
        var addressDraft = draftsPage.getAddressMailLastLetterText();
        assertThat(bodyDraft).contains(bodyLetter);
        assertThat(subjectDraft).isEqualTo(subjectLetter);
        assertThat(addressDraft).contains(recipientMail);
    }

    @Step("Отправляем черновик")
    public void sendDraft() {
        draftsPage.clickSendButton();
        draftsPage.clickConfirmationWindowCloseButton();
    }

    @Step("Открываем раздел 'Отправленные'")
    public void openSentSection() {
        mainPage.clickSentSection();
        sentPage.waitForPageLoaded();
    }

    @Step("Проверяем, что черновик отправился")
    public void checkSentDraft(String recipientMail, String subjectLetter, String bodyLetter) {
        var bodySentLetter = sentPage.getBodyLastLetterText();
        var subjectSentLetter = sentPage.getSubjectLastLetterText();
        var addressSentLetter = sentPage.getAddressMailLastLetterText();
        assertThat(bodySentLetter).contains(bodyLetter);
        assertThat(subjectSentLetter).isEqualTo(subjectLetter);
        assertThat(addressSentLetter).isEqualTo(recipientMail);
    }

    @Step("Отправляем письмо")
    public void sendNewLetter(String personalMail, String personalSubjectLetter, String bodyLetter) {
        mainPage.clickNewLetterButton();
        newLetterWindow.fillRecipientMailField(personalMail);
        newLetterWindow.fillSubjectLetterField(personalSubjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseWindowButton();
    }

    @Step("Проверям, что письмо отправилось")
    public void checkSentNewLetter(String personalMail, String personalSubjectLetter, String bodyLetter) {
        var bodySentLetter = sentPage.getBodyLastLetterText();
        var subjectSentLetter = sentPage.getSubjectLastLetterText();
        var addressSentLetter = sentPage.getAddressMailLastLetterText();
        assertThat(bodySentLetter).contains(bodyLetter);
        assertThat(subjectSentLetter).contains(personalSubjectLetter);
        assertThat(addressSentLetter).isEqualTo(personalMail);
    }

    @Step("Открываем раздел 'Тест'")
    public void openTestSection() {
        mainPage.clickTestSection();
        testPage.waitForPageLoaded();
    }

    @Step("Проверяем, что отправленное письмо попало в раздел 'Тест'")
    public void checkSentLetterInTestSection(String testAccount, String personalSubjectLetter, String bodyLetter) {
        var bodyTestLetter = testPage.getBodyTestLetterText();
        var subjectTestLetter = testPage.getSubjectTestLetterText();
        var addressTestLetter = testPage.getAddressMailTestLetterText();
        assertThat(bodyTestLetter).contains(bodyLetter);
        assertThat(subjectTestLetter).contains(personalSubjectLetter);
        assertThat(addressTestLetter).isEqualTo(testAccount);
    }

    @Step("Отправляем письмо самому себе")
    public void sendLetterToMyself(String personalMail, String subjectLetter, String bodyLetter) {
        mainPage.clickNewLetterButton();
        newLetterWindow.fillRecipientMailField(personalMail);
        newLetterWindow.fillSubjectLetterField(subjectLetter);
        newLetterWindow.fillBodyLetterField(bodyLetter);
        newLetterWindow.clickSendButton();
        newLetterWindow.clickCloseWindowButton();
    }

    @Step("Открываем раздел 'Входящие'")
    public void openInboxSection() {
        inboxPage.clickMyselfLetter();
        inboxPage.waitForMyselfPageLoaded();
    }

    @Step("Проверяем, что отправленное самому себе письмо попало в раздел 'Входящие'")
    public void checkLetterSentToMyselfInInboxSection(
            String testAccount, String subjectLetter, String bodyLetter
    ) {
        var bodyInboxLetter = inboxPage.getBodyInboxLetterText();
        var subjectInboxLetter = inboxPage.getSubjectInboxLetterText();
        var addressInboxLetter = inboxPage.getAddressInboxLetterText();
        assertThat(bodyInboxLetter).contains(bodyLetter);
        assertThat(subjectInboxLetter).contains(subjectLetter);
        assertThat(addressInboxLetter).isEqualTo(testAccount);
    }

    @Step("Открываем отправленное самому себе писмо")
    public void openLetterSentToMyself() {
        inboxPage.clickInboxLetter();
    }

    @Step("Удаляем отправленное самому себе письмо")
    public void deleteLetterSentToMyself() {
        inboxPage.clickDeleteButton();
    }

    @Step("Открываем раздел 'Корзина'")
    public void openTrashSection() {
        mainPage.clickTrashSection();
        trashPage.waitForPageLoaded();
    }

    @Step("Проверяем, что удаленное письмо попало в раздел 'Корзина'")
    public void checkLetterInTrashSection(String testAccount, String subjectLetter, String bodyLetter) {
        var bodyTrashLetter = trashPage.getBodyTrashLetterText();
        var subjectTrashLetter = trashPage.getSubjectTrashLetterText();
        var addressTrashLetter = trashPage.getAddressTrashLetterText();
        assertThat(bodyTrashLetter).contains(bodyLetter);
        assertThat(subjectTrashLetter).contains(subjectLetter);
        assertThat(addressTrashLetter).isEqualTo(testAccount);
    }

    @Step("Выходим из аккаунта")
    public void logout() {
        mainPage.clickMenuDropdown();
        mainPage.clickExitButton();
        loginPage.waitForPageLoaded();
    }
}
