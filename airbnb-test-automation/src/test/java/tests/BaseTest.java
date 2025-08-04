package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.SimpleHtmlReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;
    protected static SimpleHtmlReport report;

    @BeforeEach
    public void setUp() {
        if (report == null) {
            report = new SimpleHtmlReport(); // Rapor nesnesini test başında oluşturuyoruz
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        report.log("Tarayıcı başlatıldı.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            report.log("Tarayıcı kapatıldı.");
        }
        report.generateReport();  // Her test sonunda raporu güncelle
    }
}
