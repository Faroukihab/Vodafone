package Pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchonVodafone extends PageBase {

	public SearchonVodafone(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="sb_form_q")
	WebElement srchTxtBox ;

	@FindBy(css="a.b_widePag[aria-label='Page 2']")
	WebElement secondpage;

	@FindBy(css = "a.b_widePag.sb_bp[aria-label='Page 3']")
	WebElement  Thirdpage ;

	@FindBy(linkText ="Vodafone Egypt home page")
	WebElement searchResults;
	
	@FindBy(css = "h2")
	WebElement searchResultsPage2;

public void Searchonvodafone(String searchname) {
	setTextElementText(srchTxtBox, searchname);
	srchTxtBox.sendKeys(Keys.ENTER);

}

public String getSearchResultsText() {

    return searchResults.getText();
}
public void openSecondPage()

{
	scrollToBottom();
	
	clickButton(secondpage);
}
public void openThirdPage()

{
	scrollToBottom();
	clickButton(Thirdpage);
}
}
