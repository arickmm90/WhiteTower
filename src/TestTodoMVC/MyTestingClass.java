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
import org.testng.annotations.Listeners;
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
        Assert.assertEquals(expectedResult, actualResult,"This is not the correct page");
        System.out.println("I found " + driver.getTitle());

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
        System.out.println("ToDos have been succefully added to the list");
    }

    @AfterMethod
    public void Quit(){
        System.out.println("Let's close the browser");
        driver.quit();
    }

    @Test
    public void testcase1()  {
        System.out.println("Starting test case 1");

        //Verify that the element ToDo 3 is in the 3rd position of the list
        String thirdposition= driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[3]/div/label")).getText();
        Assert.assertEquals(thirdposition, "ToDo 3", "ToDo 3 is not in third position");
        System.out.println("The element in the third position is > "+ thirdposition);
    }

    @Test
    public void testcase2() throws InterruptedException {
        System.out.println("Starting test case 2");

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

        //Verify that the element “ToDo 4” is now in the 2 nd position of the list
        Assert.assertEquals("ToDo 4", secondPosition.getText(), "ToDo 4 is not in 2nd position");
        System.out.println("The element in 2nd position is > "+ secondPosition.getText());

        //Verify that the element “ToDo 2” does not appear in the list
        for (int i=1; i<4; i++){
            String element = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li["+ i + "]/div/label")).getText();
            if (element == "ToDo 2"){
                System.out.println("Element ToDo 2 have been found in position " + i +" of the list");
            }
        }System.out.println("Element ToDo 2 is not in the list");
    }

    @Test
    public void testcase3 () throws InterruptedException{
        System.out.println("Starting test case 3");

        //Mark the element “ToDo 3” as complete
        driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li[3]/div/input")).click();

        Thread.sleep(3000);

        //Click on the active button
        driver.findElement(By.xpath("/html/body/ng-view/section/footer/ul/li[2]/a")).click();
        Thread.sleep(3000);

        //Print in the console the elements listed with the format: "[Position]- [Element Name]"
        System.out.println("These are the elements in the list");
        for (int i=1; i<3; i++){
            String element = driver.findElement(By.xpath("/html/body/ng-view/section/section/ul/li["+ i + "]/div/label")).getText();
            System.out.println( i + " - " + element);
        }

    }

}
