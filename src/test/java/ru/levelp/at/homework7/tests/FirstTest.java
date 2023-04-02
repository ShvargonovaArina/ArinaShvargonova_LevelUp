package ru.levelp.at.homework7.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework7.steps.Steps;
import ru.levelp.at.homework7.suite.TagName;

@Feature("Черновики")
@DisplayName("Первый тест")
public class FirstTest extends BaseTest {

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
    @Story("Сохранение черновика")
    @Story("Отправка черновика")
    @DisplayName("Отправление письма из Черновика")

    void sendDraft() {
        var login = properties.getProperty("login");
        var password = properties.getProperty("password");
        var recipientMail = properties.getProperty("recipientMail");
        var personalMail = properties.getProperty("personalMail");
        var subjectLetter = properties.getProperty("subjectLetter");
        var bodyLetter = properties.getProperty("bodyLetter");

        steps.openSite();
        steps.logIn(login, password);
        steps.checkAccountName(personalMail);
        steps.createDraft(recipientMail, subjectLetter, bodyLetter);
        steps.openDraftSection();
        steps.checkDraft(recipientMail, subjectLetter, bodyLetter);
        steps.openDraft();
        steps.sendDraft();
        steps.openSentSection();
        steps.checkSentDraft(recipientMail, subjectLetter, bodyLetter);
        steps.logout();
    }
}
