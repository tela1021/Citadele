import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

public class mainFunc {


    private WebDriver driver = new ChromeDriver();
    private variables myVar = new variables();

    @Before
//инициализация хромдрайвера
    public void setup() throws Exception {
        try {
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
            driver.manage().window().maximize();
            driver.get(myVar.BASE_URL);
            System.out.println("Setup - OK");

        } catch (Throwable T) {
            System.out.println("Setup - Failed");
        }

    }

    //проверяем наличие ссылки на favicon
    public void checkFavicon() throws Exception {
        try {
            myVar.FAVICON = driver.findElement(By.xpath("/html/head/link")).getAttribute("href");
            Assert.assertEquals(myVar.FAVICON, myVar.FAVICON_ORIGINAL);
            System.out.println("checkFavicon - OK");
        } catch (Throwable T) {
            System.out.println("checkFavicon - Failed");
        }

    }

    //проверяем наличие логотипа банка
    public void logoLinkCheck() throws Exception {
        try {
            myVar.LOGO = driver.findElement(myVar.LOGO_SVG).getAttribute("src");
            Assert.assertEquals(myVar.LOGO, myVar.LOGO_ORIGINAL_LV);
            System.out.println("logoLinkCheck - OK");
        } catch (Throwable T) {
            System.out.println("logoLinkCheck - Failed");
        }

    }

//проверяем список стран

    public void countryListCheck() throws Exception {
        try {
            WebElement select = driver.findElement(By.xpath("//*[@id='loc']"));
            List<WebElement> option = select.findElements(By.tagName("option"));
            String el_latvija = option.get(0).getText().replaceAll("\\s+", "");
            String el_igaunija = option.get(1).getText().replaceAll("\\s+", "");
            String el_litvija = option.get(2).getText().replaceAll("\\s+", "");


            Assert.assertEquals(el_latvija, myVar.COUNTRY_LV);
            Assert.assertEquals(el_igaunija, myVar.COUNTRY_LT);
            Assert.assertEquals(el_litvija, myVar.COUNTRY_EE);
            System.out.println("countryListCheck - OK");
        } catch (Throwable T) {
            System.out.println("countryListCheck - Failed");
        }

    }

    //проверка списка языков и соответствующей языку ссылки
    public void langLinksCheck() throws Exception {
        try {
            driver.findElement(By.id("lv")).click();
            driver.findElement(By.id("ru")).click();
            myVar.LINKRU = driver.getCurrentUrl();

            driver.findElement(By.id("ru")).click();
            driver.findElement(By.id("lv")).click();
            myVar.LINKLV = driver.getCurrentUrl();

            driver.findElement(By.id("lv")).click();
            driver.findElement(By.id("en")).click();
            myVar.LINKEN = driver.getCurrentUrl();

            Assert.assertEquals(myVar.LINKRU, myVar.LINKRU_ORIGINAL);
            Assert.assertEquals(myVar.LINKLV, myVar.LINKLV_ORIGINAL);
            Assert.assertEquals(myVar.LINKEN, myVar.LINKEN_ORIGINAL);
            System.out.println("langLinksCheck - OK");
        } catch (Throwable T) {
            System.out.println("langLinksCheck - Failed");
        }

    }


    //Проверяем label для поля 'Pieslēgšanās vārds"
    public void labelUserNameCheck() throws Exception {
        try {
            String labelUserName = driver.findElement(By.xpath("//form[@name='docform']/fieldset/label")).getText();
            Assert.assertEquals(labelUserName, myVar.FIELD_USERNAME_LABEL_LV);
            System.out.println("labelUserNameCheck - OK");
        } catch (Throwable T) {
            System.out.println("labelUserNameCheck - Failed");
        }

    }


    //Проверяем поле (pwd)  "Пароль"
    public void labelPasswordCheck() throws Exception {
        try {
            String labelPassword = driver.findElement(By.xpath("id('psw')/label")).getText();
            Assert.assertEquals(labelPassword, myVar.FIELD_PASSWORD_LABEL_LV);
            System.out.println("labelPasswordCheck - OK");
        } catch (Throwable T) {
            System.out.println("labelPasswordCheck - Failed");
        }

    }

    //Проверяем кнопку "Продолжить"
    public void buttonContinueCheck() throws Exception {
        try {
            String buttonContinueText = driver.findElement(By.xpath("id('button')")).getText();
            String buttonContinueClass = driver.findElement(By.xpath("id('button')")).getAttribute("class");
            Assert.assertEquals(buttonContinueText, myVar.BUTTON_CONTINUE_TEXT_LV);
            Assert.assertEquals(buttonContinueClass, myVar.BUTTON_CONTINUE_CSS_STYLE_NAME);
            System.out.println("buttonContinueCheck - OK");
        } catch (Throwable T) {
            System.out.println("buttonContinueCheck - Failed");
        }

    }


    //Проверяем наличие ссылки "Забыли пароль"
    public void forgotPasswordCheck() throws Exception {
        try {
            String forgotPasswordText = driver.findElement(By.className("css_login-links")).getText();
            String forgotPasswordLink = driver.findElement(By.xpath("//form[@name='docform']/fieldset[4]/a")).getAttribute("href");
            Assert.assertEquals(forgotPasswordText, myVar.FORGOT_PASSWORD_TEXT_LV);
            Assert.assertEquals(forgotPasswordLink, myVar.FORGOT_PASSWORD_LINK_LV);
            System.out.println("forgotPasswordCheck - OK");
        } catch (Throwable T) {
            System.out.println("forgotPasswordCheck - Failed");
        }

    }

    // Проверяем элементы списка css_login-help
    public void listHelpLinkCheck() throws Exception {
        try {
        WebElement select = driver.findElement(By.xpath("//ul[@class='css_login-help']"));
        List<WebElement> option = select.findElements(By.tagName("li"));
        String phone_text = option.get(0).getText();
        String skype_text = option.get(1).getText();
        String help_text = option.get(2).getText();
        String skype_text2 = skype_text.substring(0, skype_text.length() - 7);
        String skype_link = driver.findElement(By.partialLinkText(skype_text2)).getAttribute("href");
        String help_link = driver.findElement(By.partialLinkText(help_text)).getAttribute("href");


        Assert.assertEquals(phone_text, myVar.PHONE_NUM);
        Assert.assertEquals(skype_text, myVar.SKYPE_TEXT_LV);
        Assert.assertEquals(help_text, myVar.HELP_TEXT_LV);


        Assert.assertEquals(skype_link, myVar.SKYPE_LINK);
        Assert.assertEquals(help_link, myVar.HELP_LINK);


        System.out.println("footterListLinkCheck - OK");
    } catch (Throwable T) {

        System.out.println("listHelpLinkCheck - Failed");
        }

    }


    //Проверяем элементы первого списка с классом css_footer-links в футере
    public void footterListLinkCheck() throws Exception {
        try {
            WebElement select = driver.findElement(By.xpath("//*[@id='css_footer-links']"));
            List<WebElement> option = select.findElements(By.tagName("li"));
            String info_text = option.get(0).getText();
            String security_text = option.get(1).getText();
            String www_text = option.get(2).getText();

            String info_link = driver.findElement(By.linkText(info_text)).getAttribute("href");
            String security_link = driver.findElement(By.linkText(security_text)).getAttribute("href");
            String www_link = driver.findElement(By.linkText(www_text)).getAttribute("href");

            Assert.assertEquals(info_text, myVar.INFO_TEXT_LV);
            Assert.assertEquals(security_text, myVar.SECURITY_TEXT_LV);
            Assert.assertEquals(www_text, myVar.WWW_TEXT_LV);

            Assert.assertEquals(info_link, myVar.INFO_LINK_LV);
            Assert.assertEquals(security_link, myVar.SECURITY_LINK_LV);
            Assert.assertEquals(www_link, myVar.WWW_LINK_LV);

            System.out.println("footterListLinkCheck - OK");
        } catch (Throwable T) {
            System.out.println("footterListLinkCheck - Failed");
        }

    }


    // Проверяем элементы второго списка с классом css_footer-links в футере
    public void footterCopyrightCheck() throws Exception {
        try {
            String footterCopyright = driver.findElement(By.xpath("//li[@class='css_footer-copyright']")).getText();
            Assert.assertEquals(footterCopyright, myVar.FOOTER_COPYRIGHT);

            System.out.println("footterCopyrightCheck - OK");
        } catch (Throwable T) {
            System.out.println("footterCopyrightCheck - Failed");
        }

    }

    //li[@class="css_footer-copyright"]
    // закрываем броузер
    @After
    public void close() {
        driver.close();
    }
}
