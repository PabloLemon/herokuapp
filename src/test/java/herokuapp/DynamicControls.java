package herokuapp;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Instant;

public class DynamicControls {
    private Instant wait;

    @Test
    public void testDynamicControls() {
        WebDriver driver;
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // 1. Нажать на кнопку Remove около чекбокса
        WebElement removeBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeBtn.click();

        // 2. Дождаться надписи "It's gone" (используем ExpectedConditions)
        WebElement message = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.visibilityOf(message));
        Assertions.assertEquals("It's gone!", message.getText());

        // 3. Проверить, что чекбокса нет
        // Используем логику со слайда "Как проверить, что элемента нет" (обнуляем таймаут)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean isCheckboxPresent = !driver.findElements(By.id("checkbox")).isEmpty();
        Assertions.assertFalse(isCheckboxPresent, "Чекбокс все еще на странице!");
        // Возвращаем таймаут обратно
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4. Найти инпут (он изначально disabled)
        WebElement inputField = driver.findElement(By.cssSelector("#input-example input"));

        // 5. Проверить, что он disabled
        Assertions.assertFalse(inputField.isEnabled(), "Инпут не должен быть активен!");

        // 6. Нажать на кнопку (Enable)
        WebElement enableBtn = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableBtn.click();

        // 7. Дождаться надписи "It's enabled!" (ждем, пока элемент станет видимым)
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's enabled!"));

        // 8. Проверить, что инпут enabled (используем явное ожидание, чтобы точно дождаться обновления DOM)
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        Assertions.assertTrue(inputField.isEnabled(), "Инпут должен быть активен!");
    }
}
