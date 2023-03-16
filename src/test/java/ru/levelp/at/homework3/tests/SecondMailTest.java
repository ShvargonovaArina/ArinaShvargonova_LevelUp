package ru.levelp.at.homework3.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.homework3.Main;
import ru.levelp.at.homework3.suites.TagName;

public class SecondMailTest extends Main {

    @Test
    @Tag(TagName.SUITE_TAG_NAME)
    void filterInbox() {
        final var title = driver.getTitle();
        driver.findElement(By.xpath("//*[@data-testid = 'enter-mail-primary']")).click();
        WebElement frameElement = driver.findElement(
                By.xpath("//*[@class = 'ag-popup__frame__layout__iframe']")
        );
        driver.switchTo().frame(frameElement);
        driver.findElement(By.xpath("//*[@name = 'username']"))
                .sendKeys("my.first.test.account"); //Ввод логина
        driver.findElement(By.xpath("//*[@class = 'submit-button-wrap']")).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name = 'password']"))
        ).sendKeys("my.first.password"); //Ввод пароля
        driver.findElement(By.xpath("//button[@data-test-id = 'submit-button']")).click();
        wait.until(ExpectedConditions.titleContains("Входящие"));
        WebElement accountName = driver.findElement(
                By.xpath("//span[@class = 'ph-project__user-name svelte-1hiqrvn']")
        );
        assertThat(accountName.getText()).isEqualTo("my.first.test.account@mail.ru"); //Чек входа на почту

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href = '/compose/']")
        )).click(); //Открытие нового письма
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-test-id = 'save']")));
        driver.findElement(By.xpath("//input[@class = 'container--H9L5q size_s--3_M-_']"))
                .sendKeys("my.first.test.account@mail.ru"); //Заполнение адреса получателя
        driver.findElement(By.xpath("//input[@name = 'Subject']"))
                .sendKeys("Тест"); //Заполнение темы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role = 'textbox']")))
                .sendKeys("Test Mail Body"); //Заполнение тела
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@data-test-id = 'send']")
        )).click(); //Отправка письма
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@class ='ico ico_16-close ico_size_s']")
        )).click(); //Закрытие окна

        driver.findElement((By.xpath("//*[@href = '/sent/?']"))).click(); //Открытие папки "Отправленные"
        wait.until(ExpectedConditions.titleIs("Отправленные - Почта Mail.ru"));
        WebElement subjectMail = driver.findElement(
                By.xpath("//*[@class = 'llc llc_normal llc_first"
                        + " llc_new llc_new-selection js-letter-list-item"
                        + " js-tooltip-direction_letter-bottom']/div/div/div/span/div/*[@class = 'll-sj__normal']")
        );
        assertThat(subjectMail.getText()).contains("Тест"); //Проверка темы письма

        driver.findElement((By.xpath("//*[@href = '/1/?']"))).click(); //Открытие папки "Тест"
        wait.until(ExpectedConditions.titleContains("Тест - Почта Mail.ru"));
        WebElement testSubjectMail = driver.findElement(
                By.xpath("//*[@class = 'llc llc_normal llc_first"
                        + " llc_new llc_new-selection js-letter-list-item js-tooltip-direction_letter-bottom']"
                        + "/div/div/div/span/div/*[@class = 'll-sj__normal']")
        );
        assertThat(testSubjectMail.getText()).contains("Тест"); //Проверка темы письма

        driver.findElement(By.xpath("//*[@data-testid = 'whiteline-account']")).click(); //Раскрытие окна
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@data-testid = 'whiteline-account-exit']")
        )).click(); //Нажатие на "Выход"
        assertThat(title).isEqualTo("Mail.ru: почта, поиск в интернете, новости, игры"); //Проверка логаута
    }
}
