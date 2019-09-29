import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//Username заполняем кириллицей - авпыф
public class loginWrongUsername extends mainFunc {
    private WebDriver driver = new ChromeDriver();
    private variables myVar = new variables();


    @Test
    public void loginWrongUsername() throws Exception {
        try {
            driver.get(myVar.BASE_URL);
            driver.findElement(By.id("loginID")).sendKeys("авпыф");
            driver.findElement(By.xpath("//div/form/fieldset/button")).click();
            String errorTextEmptyName = driver.findElement(By.xpath("//dl/dt")).getText();
            String errorIconClass = driver.findElement(By.xpath("//dl/dt/a/i")).getAttribute("class");
            Assert.assertEquals(errorTextEmptyName, myVar.ERROR_TEXT_WORNG_NAME_USERNAME_LV);
            Assert.assertEquals(errorIconClass, myVar.ERROR_ICON_CLASS);


            System.out.println("loginEmptyField - OK");
        } catch (Throwable errorTextEmptyName) {
            System.out.println("loginEmptyField - Failed");
            Assert.assertEquals(errorTextEmptyName, myVar.ERROR_TEXT_WORNG_NAME_USERNAME_LV);
        }

    }
}
