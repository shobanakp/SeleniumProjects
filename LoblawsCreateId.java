package practice.program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


//Invoke the Browser
public class LoblawsCreateId {
	WebDriver driver;
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "C://Users/Shobana/Desktop/Shobana-Documents/SeleniumJarFiles/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.loblaws.ca/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	
//Click the Create ID
public void clickCreateID() {
	driver.findElement(By.xpath("//a[contains(@href,'My-Shop')]")).click();
	driver.findElement(By.cssSelector("#site-content > div > div.quick-shop-layout > div > div > a > span")).click();
	driver.findElement(By.xpath("//a[contains(@href,'create-account')]")).click();
}

//Create user name and password and click Create id
public void usernameLogin(String userName, String passWord){

	driver.findElement(By.id("email")).sendKeys(userName);;
	driver.findElement(By.name("newPassword")).sendKeys(passWord);
	driver.findElement(By.className("checkbox-group__icon")).click();
	driver.findElement(By.className("button button--block button--submit button--theme-base button--theme-dark submit-button")).click();
}
	
//Read file	
public String [][] getData(String fileName,String sheetName) {
  File file =    new File(fileName);
  FileInputStream inputStream = null;
try {
	inputStream = new FileInputStream(file);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  Workbook wb = null;
try {
	wb = new XSSFWorkbook(inputStream);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  //Read sheet inside the workbook by its name
  Sheet sheetname = wb.getSheet(sheetName);
  int rowCount = sheetname.getLastRowNum()+1;
  int colCount = sheetname.getRow(0).getLastCellNum();
  String [][] data = new String[rowCount][colCount];
  
    for (int i = 0; i < rowCount; i++) {
    	Row row = sheetname.getRow(i);
        for (int j = 0; j < colCount; j++) {
        	Cell cell = row.getCell(j);
        	String value = new DataFormatter().formatCellValue(cell);
        	data[i][j] = value;
        }
    } 
    return data;
}  

//Verify the Text "Complete Your Profile"
public void verifyText() {
	String expectedLabel = "Complete Your Profile";
	WebElement actualLabel = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div/h2"));
	if (expectedLabel.equals(actualLabel)){
		System.out.println("Label Verified");
	}
	else {
		System.out.println("Not Matching");
	}
}

//Main Method
public static void main(String [] args){
	LoblawsCreateId lcb = new LoblawsCreateId();
	lcb.invokeBrowser();
	lcb.clickCreateID();

	String [][] data1 = lcb.getData("Test1.xls","Sheet1");
	
	for(int i=0;i>data1.length;i++) {
	
		String username = data1[i][1];
		String password = data1[i][2];
		lcb.usernameLogin(username,password);
	}
	
	lcb.verifyText();
	}
}
