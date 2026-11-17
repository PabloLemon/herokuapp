package herokuapp;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertEquals;

public class ContextMenu {
    @Test
    public void testContextMenu() {
        WebDriver driver = null;
        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement hotspot = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(hotspot).perform();


        Alert alert = driver.switchTo().alert();
        String expectedText = "You selected a context menu";
        AssertJUnit Assertions;
        assertEquals(expectedText, alert.getText(), "Текст на алерте не совпадает!");

        alert.accept();
    }
}