package tests;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Pages.SearchonVodafone;


public class SearchTestWithcsv extends TestBase {

	SearchonVodafone srchobj ;
	CSVReader reader;

@SuppressWarnings("deprecation")
@Test
public void Search() throws IOException, CsvValidationException, InterruptedException {
    // Get path of CSV file
    String CSV_file = System.getProperty("user.dir") + "/src/test/java/Data/Testdata.csv";
    reader = new CSVReader(new FileReader(CSV_file));

    String[] csvCell;

    // Loop through each row in the CSV file
    while ((csvCell = reader.readNext()) != null) {
        String searchname = csvCell[0];
        srchobj = new SearchonVodafone(driver);
        srchobj.Searchonvodafone(searchname);

        // Log the search name
        System.out.println("Searching for: " + searchname);

        // Get the search results text
        String searchResult = srchobj.getSearchResultsText();

        // Assert that the search result contains "Vodafone"
        Assert.assertTrue(searchResult.contains("Vodafone"), "Search result does not contain 'Vodafone'.");

        // Log the search result
        System.out.println("Search result: " + searchResult);

        // Open the second pages
        srchobj.openSecondPage();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        System.out.println("Navigating to the second page...");
          //count the search results
        List<WebElement> searchResultsPage2 = driver.findElements(By.cssSelector("h2"));
        searchResultsPage2.size();
       

        // Open the third page
        srchobj.openThirdPage();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        System.out.println("Navigating to the third page...");
        //count the search results
        List<WebElement> searchResultsPage3 = driver.findElements(By.cssSelector("h2"));
        searchResultsPage3.size();
        
         
         // Compare the number of results on page 2 and page 3
         if (searchResultsPage2 == searchResultsPage3) {
             System.out.println("Number of results on page 2 is equal to page 3.");
         } else {
             System.out.println("Number of results on page 2 is not equal to page 3.");
         }
    }

   
    reader.close();
    driver.close();
}

}
