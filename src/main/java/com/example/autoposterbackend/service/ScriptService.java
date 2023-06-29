package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.ProductWithImageDto;
import com.example.autoposterbackend.dto.request.ScriptRequest;
import com.example.autoposterbackend.entity.*;
import com.example.autoposterbackend.repository.AccountRepository;
import com.example.autoposterbackend.repository.LocationRepository;
import com.example.autoposterbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScriptService {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    public void runScript(Integer userId, ScriptRequest scriptRequest) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars", "start-maximized", "--disable-extensions", "--incognito");
        Map<String, Integer> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        List<Account> accounts = accountRepository.findAllById(scriptRequest.getAccountIds());
        Collections.shuffle(accounts);
        List<Location> locations = locationRepository.findAllByUserId(userId);
        for (Account account : accounts) {
            Random random = new Random();
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://facebook.com");
            WebElement email = driver.findElement(By.id("email"));
            email.sendKeys(account.getEmail());
            WebElement password = driver.findElement(By.id("pass"));
            password.sendKeys(account.getPassword());
            password.sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            Integer counter = 0;
            Collections.shuffle(scriptRequest.getProductsWithImages());
            for (ProductWithImageDto productWithImage : scriptRequest.getProductsWithImages()) {
                Product product = productRepository.findById(productWithImage.getProductId()).orElseThrow(RuntimeException::new);
                Category category = product.getCategories().get(random.nextInt(product.getCategories().size()));

                //Przejście do postowania ogłoszenia
                driver.get("https://www.facebook.com/marketplace/create/item");
                Thread.sleep(4000);

                //Zdjęcia
                List<Image> images = product.getImages();
                List<Image> correctImages = new ArrayList<>();
                for (Integer imageId : productWithImage.getImageIds()) {
                    correctImages.add(images.get(imageId));
                }
                WebElement photos = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@accept='image/*,image/heif,image/heic']")));
//                paths = ""
//                for image in images2:
//                paths = paths + image[0] + "\n"
//
//                paths = paths[:len(paths) - 1]
//                photos.sendKeys(paths);

                //Tytuł
                WebElement title = driver.findElement(By.xpath("//label[@aria-label='Tytuł']"));
                //clipboard.copy(product[2])
                //title.sendKeys(Keys.CONTROL + "v");
                title.sendKeys(product.getTitle());

                //Cena
                WebElement price = driver.findElement(By.xpath("//label[@aria-label='Cena']"));
                price.sendKeys(product.getPrice().toString());

                //Kategoria
                WebElement categoryElement = driver.findElement(By.xpath("//label[@aria-label='Kategoria']"));
                categoryElement.click();
                Thread.sleep(1000);
                List<WebElement> specificCategory = driver.findElements(By.xpath("//div[@role='button']"));
                specificCategory.get(specificCategory.size() - 1 - (26 - category.getId())).click();

                //Stan
                Thread.sleep(5000);
                WebElement state = driver.findElement(By.xpath("//label[@aria-label='Stan']"));
                state.click();
                Thread.sleep(5000);
                WebElement brandNew;
                try {
                    brandNew = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                            ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Nowy']")));
                } catch (StaleElementReferenceException e) {
                    state.click();
                    brandNew = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                            ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Nowy']")));
                }
                brandNew.click();

                //Opis
                WebElement description = driver.findElement(By.xpath("//label[@aria-label='Opis']"));
                description.sendKeys(product.getDescription());

                //Dostępność
                WebElement accessibility = driver.findElement(By.xpath("//label[@aria-label='Dostępność']"));
                accessibility.click();
                WebElement available = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-selected='false']")));
                available.click();

                //Lokalizacja
                WebElement location = driver.findElement(By.xpath("//label[@aria-label='Lokalizacja']"));
                Thread.sleep(5000);
                location.sendKeys(Keys.BACK_SPACE,//1
                        Keys.BACK_SPACE,//2
                        Keys.BACK_SPACE,//3
                        Keys.BACK_SPACE,//4
                        Keys.BACK_SPACE,//5
                        Keys.BACK_SPACE,//6
                        Keys.BACK_SPACE,//7
                        Keys.BACK_SPACE,//8
                        Keys.BACK_SPACE,//9
                        Keys.BACK_SPACE,//10
                        locations.get(random.nextInt(locations.size())).getName());
                location.click();
                Thread.sleep(5000);
                location.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

                //Ukryj przed znajomymi
                if (scriptRequest.getHideBeforeFriends()) {
                    List<WebElement> hideBeforeFriends = driver.findElements(By.xpath("(//div[@role='switch'])[2]"));
                    hideBeforeFriends.get(hideBeforeFriends.size() - 1).click();
                }

                try {
                    //Dalej
                    WebElement next = driver.findElement(By.xpath("//div[@aria-label='Dalej']"));
                    next.click();
                } catch (NoSuchElementException ignored) {
                }

                //Opublikuj
                Thread.sleep(5000);
                WebElement post = driver.findElement(By.xpath("//div[@aria-label='Opublikuj']"));
                post.click();

                Thread.sleep(3000);
            }
            driver.quit();
        }
    }

}
