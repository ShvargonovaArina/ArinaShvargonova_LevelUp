package ru.levelp.at.homework7.tests;

import java.io.IOException;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework7.steps.Steps;
import ru.levelp.at.homework7.suite.TagName;

@Feature("Тестовая папка")
@DisplayName("Второй тест")
public class SecondTest extends BaseTest {

    private Steps steps;

    @Override
    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        steps = new Steps(driver);
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    @Story("Создание письма")
    @Story("Отправка письма")
    @DisplayName("Фильтрация получаемых писем по теме")
    void filteringEmailsSentToYourself() {
        var login = properties.getProperty("login");
        var password = properties.getProperty("password");
        var personalMail = properties.getProperty("personalMail");
        var testAccount = properties.getProperty("testAccount");
        final String personalSubjectLetter = "Тест";
        var bodyLetter = properties.getProperty("bodyLetter");

        steps.openSite();
        steps.logIn(login, password);
        steps.checkAccountName(personalMail);
        steps.sendNewLetter(personalMail, personalSubjectLetter, bodyLetter);
        steps.openSentSection();
        steps.checkSentNewLetter(personalMail, personalSubjectLetter, bodyLetter);
        steps.openTestSection();
        steps.checkSentLetterInTestSection(testAccount, personalSubjectLetter, bodyLetter);
        steps.logout();
    }
}
