package TestTodoMVC;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class MyTestingClass {
    WebDriver driver;
    String baseURL = "https://todomvc.com/";
    String expectedResult="";
    String actualResult="";


    @BeforeMethod
    public void launchBrowser() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(baseURL);

        driver.findElement(By.linkText("AngularJS")).click();
        expectedResult="AngularJS • TodoMVC";
        actualResult=driver.getTitle();
        Assert.assertEquals(expectedResult, actualResult,"Titulo diferente");
        System.out.println("Encontre AngularJS " + driver.getTitle());

        //Para llenar la lista
        WebElement todoList = driver.findElement(By.xpath("/html/body/ng-view/section/header/form/input"));
        Actions builder = new Actions(driver);
        Action seriesActions = builder.moveToElement(todoList)
                .click()
                .sendKeys("ToDo 1")
                .sendKeys(Keys.ENTER)
                .sendKeys("ToDo 2")
                .sendKeys(Keys.ENTER)
                .sendKeys("ToDo 3")
                .sendKeys(Keys.ENTER)
                .build();
        seriesActions.perform();
        Thread.sleep(3000);
        System.out.println("Se han agregado los 3 ToDos exitosamente");
    }

    /*@BeforeMethod
    public void findLink(){
        driver.findElement(By.linkText("AngularJS")).click();
        expectedResult="AngularJS • TodoMVC";
        actualResult=driver.getTitle();
        Assert.assertEquals(expectedResult, actualResult,"Titulo diferente");
        System.out.println("Encontre AngularJS " + driver.getTitle());
    }*/

    @AfterMethod
    public void Quit(){
        System.out.println("Vamos a cerrar el navegador");
        driver.quit();
    }

    @Test
    public void testcase1()  {
        /*WebElement todoList = driver.findElement(By.xpath("/html/body/ng-view/section/header/form/input"));

        Actions builder = new Actions(driver);
        Action seriesActions = builder.moveToElement(todoList)
                .click()
                .sendKeys("ToDo 1")
                .sendKeys(Keys.ENTER)
                .sendKeys("ToDo 2")
                .sendKeys(Keys.ENTER)
                .sendKeys("ToDo 3")
                .sendKeys(Keys.ENTER)
                .build();
        seriesActions.perform();
        Thread.sleep(3000);
        System.out.println("Se han agregado los 3 ToDos exitosamente");*/

        String thirdposition= driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[3]/div/label")).getText();
        Assert.assertEquals(thirdposition, "ToDo 3", "ToDo 3 is not in third position");
        System.out.println("En la tercera posicion esta: "+ thirdposition);

    }

    @Test
    public void testcase2() throws InterruptedException {
        System.out.println("Aqui va el test 2");

        /*Actions act = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[2]/div/label"));
        act.doubleClick(ele).perform();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);
        ele.sendKeys("ToDO 4");
        ele.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println(ele.getText()+"Yo");*/



        WebElement secondPosition = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[2]/div/label"));

        Actions builder = new Actions(driver);
        Action seriesAction = builder.moveToElement(secondPosition)
                        .doubleClick()
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys(Keys.BACK_SPACE)
                        .sendKeys("ToDo 4")
                        .sendKeys(Keys.ENTER)
                        .build();
        seriesAction.perform();
        Thread.sleep(3000);

        //Para verificar si el ToDo4 se encuentra ahora en la 2da posicion de la lista
        Assert.assertEquals("ToDo 4", secondPosition.getText(), "ToDo 4 no esta en la 2da posicion");
        System.out.println("En la 2da posicion esta> "+ secondPosition.getText());

        //Para verificar si ToDo2 esta en alguna posicion de la lista
        for (int i=1; i<4; i++){
            String element = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li["+ i + "]/div/label")).getText();
            if (element == "ToDo 2"){
                System.out.println("El ToDo 2 se encuentra en la posicion " + i +"de la lista");
            }
            System.out.println("El elemento ToDo 2 no se encuentra en la posicion " + i + " de la lista.");
        }
    }

    @Test
    public void testcase3 () throws InterruptedException{
        System.out.println("Aqui va el test 3");

        //Seleccionar el ToDo3
        driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[3]/div/input")).click();

        Thread.sleep(3000);

        //Click on the active button
        driver.findElement(By.xpath("/html/body/ng-view/section/footer/ul/li[2]/a")).click();
        Thread.sleep(3000);

        //Vamos a imprimir por consola lo que hay en cada posicion
        System.out.println("Estos son los elementos en cada posision");
        for (int i=1; i<3; i++){
            String element = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li["+ i + "]/div/label")).getText();
            System.out.println( i + " - " + element);
        }
        System.out.println("Termino el test 3");

    }

}
