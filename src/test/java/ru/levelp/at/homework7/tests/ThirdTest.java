package ru.levelp.at.homework7.tests;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework7.steps.Steps;
import ru.levelp.at.homework7.suite.TagName;

@DisplayName("Третий тест")
public class ThirdTest extends BaseTest {

    private Steps steps;

    @Override
    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        steps = new Steps(driver);
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    @DisplayName("Удаления письма из Входящих")
    void deleteInboxLetter() {
        var login = properties.getProperty("login");
        var password = properties.getProperty("password");
        var personalMail = properties.getProperty("personalMail");
        var testAccount = properties.getProperty("testAccount");
        var subjectLetter = properties.getProperty("subjectLetter");
        var bodyLetter = properties.getProperty("bodyLetter");

        steps.openSite();
        steps.logIn(login, password);
        steps.checkAccountName(personalMail);
        steps.sendLetterToMyself(personalMail, subjectLetter, bodyLetter);
        steps.openInboxSection();
        steps.checkLetterSentToMyselfInInboxSection(testAccount, subjectLetter, bodyLetter);
        steps.openLetterSentToMyself();
        steps.deleteLetterSentToMyself();
        steps.openTrashSection();
        steps.checkLetterInTrashSection(testAccount, subjectLetter, bodyLetter);
        steps.logout();
    }
}
