package day05_assertion_DropdownMenu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;

public class C04_DropDownMenu {
    //https://www.amazon.com/ adresine gidin.
    //- Test 1
    // Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin
    //	-Test 2
    // 1. Kategori menusunden Books secenegini secin
    // 2. Arama kutusuna Java yazin ve aratin
    // 3. Bulunan sonuc sayisini yazdirin
    // 4. Sonucun Java kelimesini icerdigini test edin
    static WebDriver driver;
    @BeforeClass
    public static void sethup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com/");
    }
    @AfterClass
    public static void teardown(){
        driver.close();
    }
    @Test
    public void test01(){
        // Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin
        WebElement ddm=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(ddm);
        int actualOptionSayisi=select.getOptions().size();
        int expectedOptionSayisi=45;
        Assert.assertEquals(expectedOptionSayisi,actualOptionSayisi);
    }
    @Test
    public void test02(){
        // 1. Kategori menusunden Books secenegini secin
        WebElement ddm=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(ddm);
        select.selectByVisibleText("Books");
        // 2. Arama kutusuna Java yazin ve aratin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Java"+ Keys.ENTER);
        // 3. Bulunan sonuc sayisini yazdirin
        WebElement sonucYaziElementi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small'] "));
        System.out.println(sonucYaziElementi.getText());
        // 4. Sonucun Java kelimesini icerdigini test edin
        String expectedIcerik="Java";
        String actualSonucYazisi=sonucYaziElementi.getText();
        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));
    }






}
