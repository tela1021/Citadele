import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static javax.swing.text.html.CSS.getAttribute;

public class mainFunc {


    private WebDriver driver = new ChromeDriver();
    private variables myVar = new variables();

    @Before
    public  void setup() {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(myVar.BASE_URL);

    }

    @After
    public void close() {
        driver.close();
    }

    //проверяем наличие ссылки на favicon
    public void checkFavicon() throws Exception {
        try {
            myVar.FAVICON = driver.findElement(By.xpath("/html/head/link")).getAttribute("href");
            Check(myVar.FAVICON, myVar.FAVICON_ORIGINAL);
            System.out.println("checkFavicon - OK");
        } catch (Throwable T) {
            System.out.println("checkFavicon - Failed");
        }

    }

    private void openpage() {
        driver.get(myVar.BASE_URL);
    }
    //проверяем наличие логотипа банка
    public void logoLinkCheck() throws Exception {
        try {
            myVar.LOGO = driver.findElement(myVar.LOGO_SVG).getAttribute("src");
            Check(myVar.LOGO, myVar.LOGO_ORIGINAL_LV);
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


            Check(el_latvija, myVar.COUNTRY_LV);
            Check(el_igaunija, myVar.COUNTRY_LT);
            Check(el_litvija, myVar.COUNTRY_EE);
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

            Check(myVar.LINKRU, myVar.LINKRU_ORIGINAL);
            Check(myVar.LINKLV, myVar.LINKLV_ORIGINAL);
            Check(myVar.LINKEN, myVar.LINKEN_ORIGINAL);
            System.out.println("langLinksCheck - OK");
        } catch (Throwable T) {
            System.out.println("langLinksCheck - Failed");
        }

    }


    //Проверяем label для поля 'Pieslēgšanās vārds"
    public void labelUserNameCheck() throws Exception {
        try {
            String labelUserName = driver.findElement(By.xpath("//form[@name='docform']/fieldset/label")).getText();
            Check(labelUserName, myVar.FIELD_USERNAME_LABEL_LV);
            System.out.println("labelUserNameCheck - OK");
        } catch (Throwable T) {
            System.out.println("labelUserNameCheck - Failed");
        }

    }


    //Проверяем поле (pwd)  "Пароль"
    public void labelPasswordCheck() throws Exception {
        try {
            String labelPassword = driver.findElement(By.xpath("id('psw')/label")).getText();
            Check(labelPassword, myVar.FIELD_PASSWORD_LABEL_LV);
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
            Check(buttonContinueText, myVar.BUTTON_CONTINUE_TEXT_LV);
            Check(buttonContinueClass, myVar.BUTTON_CONTINUE_CSS_STYLE_NAME);
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
            Check(forgotPasswordText, myVar.FORGOT_PASSWORD_TEXT_LV);
            Check(forgotPasswordLink, myVar.FORGOT_PASSWORD_LINK_LV);
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
            String phoneText = option.get(0).getText();
            String skypeText = option.get(1).getText();
            String helpText = option.get(2).getText();
            String skype_text2 = skypeText.substring(0, skypeText.length() - 7);
            String skypeLink = driver.findElement(By.partialLinkText(skype_text2)).getAttribute("href");
            String helpLink = driver.findElement(By.partialLinkText(helpText)).getAttribute("href");


            Check(phoneText, myVar.PHONE_NUM);
            Check(skypeText, myVar.SKYPE_TEXT_LV);
            Check(helpText, myVar.HELP_TEXT_LV);


            Check(skypeLink, myVar.SKYPE_LINK);
            Check(helpLink, myVar.HELP_LINK);


            System.out.println("footterListLinkCheck - OK");
        } catch (Throwable T ) {

            System.out.println("listHelpLinkCheck - Failed");
        }

    }


    //Проверяем элементы первого списка с классом css_footer-links в футере
    public void footterListLinkCheck() throws Exception {
        try {
            WebElement select = driver.findElement(By.xpath("//*[@id='css_footer-links']"));
            List<WebElement> option = select.findElements(By.tagName("li"));
            String infoText = option.get(0).getText();
            String securityText = option.get(1).getText();
            String wwwText = option.get(2).getText();

            String infoLink = driver.findElement(By.linkText(infoText)).getAttribute("href");
            String securityLink = driver.findElement(By.linkText(securityText)).getAttribute("href");
            String wwwLink = driver.findElement(By.linkText(wwwText)).getAttribute("href");

            Check(infoText, myVar.INFO_TEXT_LV);
            Check(securityText, myVar.SECURITY_TEXT_LV);
            Check(wwwText, myVar.WWW_TEXT_LV);

            Check(infoLink, myVar.INFO_LINK_LV);
            Check(securityLink, myVar.SECURITY_LINK_LV);
            Check(wwwLink, myVar.WWW_LINK_LV);

            System.out.println("footterListLinkCheck - OK");
        } catch (Throwable T) {
            System.out.println("footterListLinkCheck - Failed");
        }

    }

    private void Check(String variable, String staticVariable) {
        Assert.assertEquals(variable, staticVariable);
    }


    // Проверяем элементы второго списка с классом css_footer-links в футере
    public void footerCopyrightCheck() throws Exception {
        try {
            String footerCopyright = driver.findElement(By.xpath("//li[@class='css_footer-copyright']")).getText();
            Check(footerCopyright, myVar.FOOTER_COPYRIGHT);

            System.out.println("footterCopyrightCheck - OK");
        } catch (Throwable T) {
            System.out.println("footterCopyrightCheck - Failed");
        }

    }


}
