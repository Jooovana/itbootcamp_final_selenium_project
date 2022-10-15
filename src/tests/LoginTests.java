package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BasicTest {
	
	@Test(priority=1)
	public void visitTheLoginPage() {
//		Postaviti EN jezik stranice
//		Klik na login dugme iz navigacije
//		Verifikovati da se u url-u stranice javlja ruta /login
		navPage.getChooseALanguageBtn().click();
		navPage.getEnLang().click();
		navPage.getLoginBtn().click();
		
		Assert.assertTrue(
				driver.getCurrentUrl().contains("/login"),
				"ERROR: Url should be contains /login.");
	}
	
	@Test(priority=2)
	public void checkInputTypes() {
//		Klik na login dugme iz navigacije
//		Verifikovati da polje za unos emaila za atribut type ima vrednost email
//		Verifikovati da polje za unos lozinke za atribut type ima vrednost password
		navPage.getLoginBtn().click();
		Assert.assertEquals(
				loginPage.getEmailInput().getAttribute("type"), 
				"email",
				"ERROR: Input type should be email");
		
		Assert.assertEquals(
				loginPage.getPasswordInput().getAttribute("type"), 
				"password",
				"ERROR: Input type should be password");
	}
	
	@Test(priority=3)
	public void displaysErrorsWhenUserDoesNotexist() {
//		email: non-existing-user@gmal.com
//		password: password123
//		Koraci: 
//		Klik na login dugme iz navigacije
//		Popuniti login formu podacima za logovanje
//		Klik na login dugme
//		Sacekati da popu za prikaz greske bude vidljiv
//		Verifikovati da greska sadrzi poruku User does not exists
//		Verifikovati da se u url-u stranice javlja /login ruta 
		navPage.getLoginBtn().click();
		
		loginPage.getEmailInput().sendKeys("non-existing-user@gmal.com");
		loginPage.getPasswordInput().sendKeys("password123");
		loginPage.getLoginBtn().click();
		
		messagePopUpPage.waitForPopUpVisibility();
		
		Assert.assertTrue(
				messagePopUpPage.getElementsWithTextMessages()
				.getText()
				.equals("User does not exists"),
				"ERROR: Message should be User does not exist.");
		
		Assert.assertTrue(
				driver.getCurrentUrl().contains("/login"),
				"ERROR: Url should be contains /login.");
			
	}
	
	@Test(priority=4)
	public void displaysErrorsWhenPasswordIsWrong() {
//		email: admin@admin.com
//		password: password123
//		Koraci: 
//		Klik na login dugme iz navigacije
//		Popuniti login formu podacima za logovanje
//		Klik na login dugme
//		Sacekati da popu za prikaz poruke bude vidljiv
//		Verifikovati da greska sadrzi poruku Wrong password
//		Verifikovati da se u url-u stranice javlja /login ruta 
		navPage.getLoginBtn().click();
		
		loginPage.getEmailInput().sendKeys("admin@admin.com");
		loginPage.getPasswordInput().sendKeys("password123");
		loginPage.getLoginBtn().click();
		
		messagePopUpPage.waitForPopUpVisibility();
		
		Assert.assertTrue(
				messagePopUpPage.getElementsWithTextMessages()
				.getText()
				.equals("Wrong password"),
				"ERROR: Message should be Wrong password");
		
		Assert.assertTrue(
				driver.getCurrentUrl().contains("/login"),
				"ERROR: Url should be contains /login.");
			
	}
	
	@Test(priority=5)
	public void  login() throws InterruptedException {
//		email: admin@admin.com
//		password: 12345
//		Koraci: 
//		Klik na login dugme iz navigacije
//		Popuniti login formu podacima za logovanje
//		Verifikovati da se u url-u stranice javlja /home ruta 
		navPage.getLoginBtn().click();
		
		loginPage.getEmailInput().sendKeys("admin@admin.com");
		loginPage.getPasswordInput().sendKeys("12345");
		loginPage.getLoginBtn().click();
		Thread.sleep(1000);
		Assert.assertTrue(
				driver.getCurrentUrl().contains("/home"),
				"ERROR: Url should be contains /home.");
	}
	
	@Test(priority=6)
	public void logout() {
//		Verifikovati da je dugme logout vidljivo na stranici
//		Kliknuti na logout dugme
		Assert.assertTrue(
				navPage.getLogoutBtn().isDisplayed(),
				"ERROR: Logout button is not displayed.");
		navPage.getLogoutBtn().click();
		
		

	}

}
