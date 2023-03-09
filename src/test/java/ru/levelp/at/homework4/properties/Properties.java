package ru.levelp.at.homework4.properties;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Properties {
    protected static final String login = "my.first.test.account";
    protected static final String password = "my.first.password";
    protected static final String recipientMail = "my.first.test.account@yandex.ru";
    protected static final String personalMail = "my.first.test.account@mail.ru";
    protected static final String testAccount = "Test Account";
    protected static final String subjectLetter = "Subject";
    protected static final String personalSubjectLetter = "Тест";
    protected static final String bodyLetter = "Body";
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
