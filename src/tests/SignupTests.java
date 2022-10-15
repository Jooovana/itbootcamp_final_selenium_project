package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BasicTest {
	
	@Test(priority=1)
	public void visitsTheSignupPage() {
//		Klik na sign up dugme iz navigacije
//		Verifikovati da se u url-u stranice javlja /signup ruta 
		navPage.getSignUpBtn().click();
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/signup"),
				"ERROR: Url should be contains /signup.");
	}
	
	@Test(priority=2)
	public void checksInputTypes() {
//		Klik na sign up dugme iz navigacije
//		Verifikovati da polje za unos emaila za atribut type ima vrednost email
//		Verifikovati da polje za unos lozinke za atribut type ima vrednost password
//		Verifikovati da polje za unos lozinke za potvrdu za atribut type ima vrednost password
		navPage.getSignUpBtn().click();
		
		Assert.assertEquals(
				signupPage.getEmailInput().getAttribute("type"), 
				"email",
				"ERROR: Input type should be email");
		
		Assert.assertEquals(
				signupPage.getPasswordInput().getAttribute("type"), 
				"password",
				"ERROR: Input type should be password");
		
		Assert.assertEquals(
				signupPage.getPasswordInput().getAttribute("type"), 
				"password",
				"ERROR: Input type should be password");
	}
	
	@Test(priority=3)
	public void displaysErrorsWhenUserAlreadyExists() {
//		Podaci: 
//			name: Another User
//			email: admin@admin.com
//			password: 12345
//			confirm password: 12345
//			Koraci: 
//			Klik na sign up dugme iz navigacije
//			Verifikovati da se u url-u stranice javlja /signup ruta 
//			Popuniti formu za registraciju podacima
//			Klik na sign up dugme
//			Sacekati da popu za prikaz poruke bude vidljiv
//			Verifikovati da greska sadrzi poruku E-mail already exists
//			Verifikovati da se u url-u stranice javlja /signup ruta
		navPage.getSignUpBtn().click();
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/signup"),
				"ERROR: Url should be contains /signup.");
		
		signupPage.getUserNameInput().sendKeys("Another User");
		signupPage.getEmailInput().sendKeys("admin@admin.com");
		signupPage.getPasswordInput().sendKeys("12345");
		signupPage.getConfirmPasswordInput().sendKeys("12345");
		signupPage.getSignMeUpBtn().click();
		
		messagePopUpPage.waitForPopUpVisibility();
		
		Assert.assertTrue(
				messagePopUpPage.getElementsWithTextMessages()
				.getText()
				.equals("E-mail already exists"),
				"ERROR: Message should be E-mail already exists");
	}
	
	@Test(priority=4)
	public void signup() throws InterruptedException {
//		Podaci: 
//			name: Ime i prezime polaznika
//			email template: ime.prezime@itbootcamp.rs
//			password: 12345
//			confirm password: 12345
//			Koraci: 
//			Klik na sign up dugme iz navigacije
//			Popuniti formu podacima za registraciju
//			Klik na sign up dugme
//			Ucitati stranicu /home
//			Verifikovati da dijalog za obavestenje sadrzi tekst IMPORTANT: Verify your account
//			Kliknuti na Close dugme iz dijaloga
//			Kliknuti na logout
		navPage.getSignUpBtn().click();
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/signup"),
				"ERROR: Url should be contains /signup.");
		
		signupPage.getUserNameInput().sendKeys("Ivan Matejev");
		signupPage.getEmailInput().sendKeys("ivan.matejev@itbootcamp.rs");
		signupPage.getPasswordInput().sendKeys("12345");
		signupPage.getConfirmPasswordInput().sendKeys("12345");
		signupPage.getSignMeUpBtn().click();
		
		Thread.sleep(2000);
		
		Assert.assertTrue(
				messagePopUpPage.getVerifyYourAccountMsg().getText().equals("IMPORTANT: Verify your account"),
				"ERROR: Wrong message.");
		
		messagePopUpPage.getCloseBtn().click();
		navPage.getLogoutBtn().click();

	}


}
