package ru.levelp.at.homework7.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.homework7.listener.AllureAttachmentReport;
import ru.levelp.at.homework7.listener.TestContext;

@ExtendWith({AllureAttachmentReport.class})
public abstract class BaseTest {
    public Properties properties;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        TestContext.getInstance().addObject("driver", driver);
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("users.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        TestContext.clear();
    }
}
