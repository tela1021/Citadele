import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/*

Нажатие на кнопку "Продолжить"
Имя подключения заполненно несуществующим логином, пароль - пусто
 */

public class loginEmptyWronPass extends mainFunc {
    private WebDriver driver = new ChromeDriver();
    private variables myVar = new variables();


    @Test
    public void loginEmptyWronPass() throws Exception {
        try {
            driver.get(myVar.BASE_URL);
            driver.findElement(By.id("loginID")).sendKeys("3");
            driver.findElement(By.id("pin")).sendKeys("333333333333333333333");
            driver.findElement(By.xpath("//div/form/fieldset/button")).click();
            String errorTextWrongPass = driver.findElement(By.xpath("//dl/dt")).getText();
            String errorIconClass = driver.findElement(By.xpath("//dl/dt/a/i")).getAttribute("class");
            Assert.assertEquals(errorTextWrongPass, myVar.ERROR_TEXT_WRONG_PASS);
            Assert.assertEquals(errorIconClass, myVar.ERROR_ICON_CLASS);


            System.out.println("loginEmptyPassword - OK");
        } catch (Throwable errorTextWrongName) {
            System.out.println("loginEmptyPassword - Failed");
            Assert.assertEquals(errorTextWrongName, myVar.ERROR_TEXT_WRONG_PASS);
       }

    }
}
