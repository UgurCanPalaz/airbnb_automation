package tests;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirbnbSearchTest extends BaseTest {

    @Test
    public void testSearchWithFilters() throws InterruptedException {
        report.log("Test başladı: Airbnb arama filtre testi.");

        driver.get("https://www.airbnb.com");
        report.log("Airbnb ana sayfası açıldı.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebElement popupButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Anladım']")));
            popupButton.click();
            report.log("Anladım popup'ı kapatıldı.");
        } catch (Exception ignored) {
            report.log("Popup kapatma gerekmedi.");
        }

        WebElement locationInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("bigsearch-query-location-input")));
        locationInput.click();
        locationInput.sendKeys("Paris");
        locationInput.sendKeys(Keys.ENTER);
        report.log("Konum seçildi: Paris.");

        WebElement dateTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='expanded-searchbar-dates-calendar-tab']")));
        dateTab.click();
        report.log("Tarih seçme tabı açıldı.");

        LocalDate today = LocalDate.now();
        Random random = new Random();
        int checkinOffset = 7 + random.nextInt(8);
        int checkoutOffset = checkinOffset + 1 + random.nextInt(7);

        LocalDate checkinDate = today.plusDays(checkinOffset);
        LocalDate checkoutDate = today.plusDays(checkoutOffset);

        DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String checkinISO = checkinDate.format(isoFormatter);
        String checkoutISO = checkoutDate.format(isoFormatter);

        while (driver.findElements(By.cssSelector("button[data-state--date-string='" + checkinISO + "']")).isEmpty()) {
            WebElement nextMonthBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Next']")));
            nextMonthBtn.click();
            Thread.sleep(500);
        }
        WebElement checkinDay = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-state--date-string='" + checkinISO + "']")));
        checkinDay.click();
        report.log("Giriş tarihi seçildi: " + checkinISO);

        while (driver.findElements(By.cssSelector("button[data-state--date-string='" + checkoutISO + "']")).isEmpty()) {
            WebElement nextMonthBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Next']")));
            nextMonthBtn.click();
            Thread.sleep(500);
        }
        WebElement checkoutDay = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-state--date-string='" + checkoutISO + "']")));
        checkoutDay.click();
        report.log("Çıkış tarihi seçildi: " + checkoutISO);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("[data-testid='calendarModal']")));

        WebElement guestButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Kişiler')]")));
        guestButton.click();
        report.log("Kişi sayısı seçme kısmı açıldı.");

        WebElement adultPlus = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='stepper-adults-increase-button']")));
        adultPlus.click();
        Thread.sleep(500);
        adultPlus.click();
        report.log("Yetişkin sayısı artırıldı +2.");

        WebElement childPlus = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='stepper-children-increase-button']")));
        childPlus.click();
        report.log("Çocuk sayısı artırıldı +1.");

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Arama']")));
        searchBtn.click();
        report.log("Arama butonuna tıklandı.");

        WebElement filtersButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Filtreler']")));
        filtersButton.click();
        report.log("Filtreler açıldı.");

        WebElement minPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("price_filter_min")));
        minPriceInput.click();
        minPriceInput.sendKeys(Keys.CONTROL + "a");
        minPriceInput.sendKeys(Keys.DELETE);
        minPriceInput.sendKeys("1000");
        minPriceInput.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        report.log("Minimum fiyat girildi: 1000.");

        WebElement maxPriceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("price_filter_max")));
        maxPriceInput.click();
        maxPriceInput.sendKeys(Keys.CONTROL + "a");
        maxPriceInput.sendKeys(Keys.DELETE);
        maxPriceInput.sendKeys("20000");
        maxPriceInput.sendKeys(Keys.ENTER);
        report.log("Maksimum fiyat girildi: 20000.");

        WebElement freeCancellation = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("filter-item-flexible_cancellation")));
        freeCancellation.click();
        report.log("Ücretsiz iptal filtresi seçildi.");

        WebElement increaseBedrooms = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='stepper-filter-item-min_bedrooms-stepper-increase-button']")));
        increaseBedrooms.click();
        Thread.sleep(500);
        report.log("Minimum yatak odası sayısı 1 olarak ayarlandı.");

        WebElement showResultsBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'yeri göster')]")));
        showResultsBtn.click();
        report.log("Sonuçlar gösterildi.");

        wait.until(ExpectedConditions.urlContains("price_max=20000"));
        wait.until(ExpectedConditions.urlContains("flexible_cancellation=true"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("price_max=20000"));
        assertTrue(currentUrl.contains("flexible_cancellation=true"));
        report.log("URL filtre kriterlerine uygun.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='/rooms/']")));
        List<WebElement> listings = driver.findElements(By.cssSelector("a[href*='/rooms/']"));
        assertTrue(listings.size() > 0, "İlan bulunamadı!");
        int maxIndex = Math.min(5, listings.size());
        int randomIndex = new Random().nextInt(maxIndex);
        WebElement randomListing = listings.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", randomListing);
        wait.until(ExpectedConditions.elementToBeClickable(randomListing));
        report.log("İlanlardan rastgele biri seçildi.");

        String originalWindow = driver.getWindowHandle();

        try {
            randomListing.click();
            report.log("İlan tıklandı.");
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", randomListing);
            report.log("İlan tıklaması JS ile yapıldı.");
        }

        try {
            Thread.sleep(2000);

            Object isVisible = js.executeScript(
                "const btn = document.querySelector('button[aria-label=\"Kapat\"]');" +
                "if (btn) {" +
                "  const style = window.getComputedStyle(btn);" +
                "  return (style && style.display !== 'none' && style.visibility !== 'hidden' && btn.offsetHeight > 0 && btn.offsetWidth > 0);" +
                "} else {" +
                "  return false;" +
                "}"
            );

            if (Boolean.TRUE.equals(isVisible)) {
                js.executeScript("document.querySelector('button[aria-label=\"Kapat\"]').click();");
                report.log("Pop-up kapatma butonuna JS ile tıklandı.");
            } else {
                report.log("Pop-up kapatma butonu görünür değil.");
            }

        } catch (Exception e) {
            report.log("Pop-up kapatma sırasında hata: " + e.getMessage());
        }

        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("/rooms/"));
        report.log("Detay sayfasına geçildi.");

        WebElement detailPriceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@data-testid, 'price')]//span[contains(text(),'₺')]")));
        String detailPriceText = detailPriceElem.getText().replaceAll("[^0-9]", "");
        int detailPrice = Integer.parseInt(detailPriceText);
        assertTrue(detailPrice >= 1000 && detailPrice <= 20000, "Detay sayfası fiyat aralıkta değil: " + detailPrice);
        report.log("Detay sayfası fiyat doğrulandı: " + detailPrice);

        for (int i = 0; i < 5; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
            Thread.sleep(1000);
        }

        List<WebElement> detailFreeCancelElems = driver.findElements(By.xpath("//*[contains(text(),'Ücretsiz iptal')]"));
        assertTrue(!detailFreeCancelElems.isEmpty(), "Detay sayfasında Ücretsiz iptal yok!");
        report.log("Detay sayfasında Ücretsiz iptal doğrulandı.");

        report.log("Detay sayfasında filtrelere uygun ilan bulundu.");
        report.log("İlan fiyatı: " + detailPriceElem.getText());
        report.log("Arama URL'si: " + driver.getCurrentUrl());

        Thread.sleep(30000);

        report.log("Test tamamlandı.");
    }
}
