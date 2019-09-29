import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//Заблокированный пользователь login:RIGA  pass:333333333333333
public class loginBlockedUser extends mainFunc {
    private WebDriver driver = new ChromeDriver();
    private variables myVar = new variables();


    @Test
    public void loginBlockedUser() throws Exception {
        try {
            driver.get(myVar.BASE_URL);
            driver.findElement(By.id("loginID")).sendKeys("RIGA");
            driver.findElement(By.id("pin")).sendKeys("333333333333333");
            driver.findElement(By.xpath("//div/form/fieldset/button")).click();
            String userBlocked = driver.findElement(By.xpath("//div[@class='css_result css_result-failure']/h2")).getText();
            Assert.assertEquals(userBlocked, myVar.ERROR_TEXT_USERBLOCKED_LV);


            System.out.println("loginBlockedUser - OK");
        } catch (Throwable userBlocked) {
            System.out.println("loginBlockedUser - Failed");
            Assert.assertEquals(userBlocked, myVar.ERROR_TEXT_USERBLOCKED_LV);
        }

    }
}
