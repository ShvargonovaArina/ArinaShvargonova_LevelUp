package ru.levelp.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class FirstMailTest {
    public static final String MAIL_URL = "https://mail.ru/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to(MAIL_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    void sendDraft() {
        final var title = driver.getTitle();
        driver.findElement(By.xpath("//*[@data-testid = 'enter-mail-primary']")).click();
        WebElement frameElement = driver.findElement(
                By.xpath("//*[@class = 'ag-popup__frame__layout__iframe']")
        );
        driver.switchTo().frame(frameElement);
        driver.findElement(By.xpath("//*[@name = 'username']"))
                .sendKeys("my.first.test.account"); //Ввод логина
        driver.findElement(By.xpath("//*[@class = 'submit-button-wrap']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name = 'password']")))
                .sendKeys("my.first.password"); //Ввод пароля
        driver.findElement(By.xpath("//button[@data-test-id = 'submit-button']")).click();
        wait.until(ExpectedConditions.titleContains("Входящие"));
        WebElement accountName = driver.findElement(
                By.xpath("//span[@class = 'ph-project__user-name svelte-1hiqrvn']")
        );
        assertThat(accountName.getText()).isEqualTo("my.first.test.account@mail.ru"); //Чек входа на почту

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/compose/']")))
                .click(); //Открытие нового письма
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test-id = 'save']")));
        driver.findElement(By.xpath("//input[@class = 'container--H9L5q size_s--3_M-_']"))
                .sendKeys("my.first.test.account@yandex.ru"); //Заполнение адреса получателя
        driver.findElement(By.xpath("//input[@name = 'Subject']"))
                .sendKeys("Test Mail"); //Заполнение темы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role = 'textbox']")))
                .sendKeys("Test Mail Body"); //Заполнение тела
        driver.findElement(By.xpath("//*[@data-test-id = 'save']")).click(); //Сохранение черновика
        driver.findElement(By.xpath("//*[@class = 'icon--3PdIV']")).click(); //Закрытие окна подтверждения

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href = '/drafts/?']")))
                .click(); //Открытие папки "Черновики"
        wait.until(ExpectedConditions.titleContains("Черновики"));
        WebElement bodyMail = driver.findElement(By.xpath("//*[@class = 'llc llc_normal llc_first"
                + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
                + "/div/div/div/span/div/*[@class = 'll-sp__normal']"));
        WebElement subjectMail = driver.findElement(By.xpath("//*[@class = 'llc llc_normal"
                + " llc_first llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
                + "/div/div/div/span/div/*[@class = 'll-sj__normal']"));
        WebElement addressMail = driver.findElement(By.xpath("//*[@class = 'llc llc_normal llc_first"
                + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
                + "/div/div/div/*[@class = 'll-crpt']"));
        assertThat(bodyMail.getText()).contains("Test Mail Body"); //Проверка тела письма
        assertThat(subjectMail.getText()).contains("Test Mail"); //Проверка темы письма
        assertThat(addressMail.getText()).contains("my.first.test.account@yandex.ru"); //Проверка адреса получателя

        bodyMail.click(); //Открытие тестового черновика
        driver.findElement(By.xpath("//*[@class = 'button2__ico']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test-id = 'send']")))
                .click(); //Отправка письма
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@class ='ico ico_16-close ico_size_s']")
        )).click(); //Закрытие окна

        driver.findElement((By.xpath("//*[@href = '/sent/?']"))).click(); //Открытие папки "Отправленные"
        wait.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
        WebElement bodySentMail = driver.findElement(
                By.xpath("//*[@class = 'llc llc_normal llc_first llc_new llc_new-selection "
                        + "js-letter-list-item js-tooltip-direction_letter-bottom']"
                        + "/div/div/div/span/div/*[@class = 'll-sp__normal']")
        );
        assertThat(bodySentMail.getText()).contains("Test Mail Body"); //Проверка отправленного письма по телу
        driver.findElement(By.xpath("//*[@data-testid = 'whiteline-account']")).click(); //Раскрытие окна
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@data-testid = 'whiteline-account-exit']")
        )).click(); //Нажатие на "Выход"
        assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры"); //Проверка логаута
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
