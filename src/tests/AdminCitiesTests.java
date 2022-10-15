package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCitiesTests  extends BasicTest {
	
	@Test(priority=1)
	public void visitsTheAdminCitiesPageAndListCities() throws InterruptedException {
//		Podaci: 
//			admin email: admin@admin.com
//			admin password: 12345
//			Koraci: 
//			Klik na sign up dugme iz navigacije
//			Prijaviti se na sistem admin kredencijalima
//			Klik na admin dugme iz navigacije
//			Klik na Cities dugme iz padajuceg Admin menija
//			Verifikovati da se u url-u stranice javlja /admin/cities ruta 
		navPage.getLoginBtn().click();
		loginPage.getEmailInput().sendKeys("admin@admin.com");
		loginPage.getPasswordInput().sendKeys("12345");
		loginPage.getLoginBtn().click();
		
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		
		Thread.sleep(2000);
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/admin/cities"),
				"ERROR: Url should ends with /admin/cities");
	}
	
	@Test(priority=2)
	public void checksInputTypesForCreateEditNewCity() {
//		Klik na admin dugme iz navigacije
//		Klik na Cities dugme iz padajuceg Admin menija
//		Kliknuti na New Item dugme
//		Sacekati da se dijalog za kreiranje i editovanje grada pojavi
//		Verifikovati da polje za unos grada za atribut type ima tekst text
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		citiesPage.getNewItemBtn().click();
		
		citiesPage.waitForNewEditDialog();
		
		Assert.assertTrue(
				citiesPage.getInputFromNewItemDialog().getAttribute("type").equals("text"),
				"ERROR: Input type should be text");
	}
	
	@Test(priority=3)
	public void createNewCity() {
//		Podaci: 
//			city: [Ime i prezime polaznika]’s city
//			Koraci: 
//			Klik na admin dugme iz navigacije
//			Klik na Cities dugme iz padajuceg Admin menija
//			Kliknuti na New Item dugme
//			Sacekati da se dijalog za kreiranje i editovanje grada pojavi
//			Uneti ime grada u polje
//			Kliknuti na Save dugme
//			Sacekati da popu za prikaz poruke bude vidljiv
//			Verifikovati da poruka sadrzi tekst Saved successfully
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		citiesPage.getNewItemBtn().click();
		
		citiesPage.waitForNewEditDialog();
		
		citiesPage.getInputFromNewItemDialog().sendKeys("Ivan Matejev's city");
		citiesPage.getSaveNewDialogBtn().click();
		
		messagePopUpPage.waitForSavedSuccessfulyMsg();;
		
		Assert.assertTrue(
				messagePopUpPage.getSavedSuccessfulMsg().getText().contains("Saved successfully"),
				"ERROR: Wrong message.");
	}
	
	@Test(priority=4)
	public void editCity() throws InterruptedException {
//		Podaci: 
//			old city name: [Ime i prezime polaznika]’s city
//			new city name: [Ime i prezime polaznika]’s city Edited
//			Koraci: 
//			Klik na admin dugme iz navigacije
//			Klik na Cities dugme iz padajuceg Admin menija
//			U polje za pretragu uneti staro ime grada
//			Sacekati da broj redova u tabeli bude 1
//			Kliknuti na dugme Edit iz prvog reda
//			Uneti novo ime za grad
//			Kliknuti na dugme Save
//			Sacekati da popu za prikaz poruke bude vidljiv
//			Verifikovati da poruka sadrzi tekst Saved successfully
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		citiesPage.getSearchInput().sendKeys("Ivan Matejev's city");
		
		citiesPage.waitForNumbersOfCitiesToBe(1);
		citiesPage.getEditBtnFromRow(1).click();
		citiesPage.getInputFromNewItemDialog().sendKeys(" Edited");
		citiesPage.getSaveNewDialogBtn().click();
		messagePopUpPage.waitForSuccessfullSavedMsg();
		
		Assert.assertTrue(
				messagePopUpPage.getSavedSuccessfulMsg().getText().contains("Saved successfully"),
				"ERROR: Wrong message.");
		
		
	}
	
	@Test(priority=5)
	public void searchCity() {
//		Podaci: 
//			city name: [Ime i prezime polaznika]’s city Edited
//			Koraci: 
//			Klik na admin dugme iz navigacije
//			Klik na Cities dugme iz padajuceg Admin menija
//			U polje za pretragu uneti staro ime grada
//			Sacekati da broj redova u tabeli bude 1
//			Verifikovati da se u Name koloni prvog reda nalazi tekst iz pretrage
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		citiesPage.getSearchInput().sendKeys("Ivan Matejev's city");
		
		citiesPage.waitForNumbersOfCitiesToBe(1);
		Assert.assertTrue(
				 citiesPage.getCellFromRow(1, 2).getText().contains("Ivan Matejev's city"),
				 "ERROR: City name should contains - Ivan Matejev's city");
	}
	
	@Test(priority=6)
	public void deleteCity() {
//		Podaci: 
//			city name: [Ime i prezime polaznika]’s city Edited
//			Koraci: 
//			Klik na admin dugme iz navigacije
//			Klik na Cities dugme iz padajuceg Admin menija
//			U polje za pretragu uneti staro ime grada
//			Sacekati da broj redova u tabeli bude 1
//			Verifikovati da se u Name koloni prvog reda nalazi tekst iz pretrage
//			Kliknuti na dugme Delete iz prvog reda
//			Sacekati da se dijalog za brisanje pojavi
//			Kliknuti na dugme Delete iz dijaloga
//			Sacekati da popu za prikaz poruke bude vidljiv
//			Verifikovati da poruka sadrzi tekst Deleted successfully
		navPage.getAdminBtn().click();
		navPage.getAdminCitiesLink().click();
		citiesPage.getSearchInput().sendKeys("Ivan Matejev's city");
		
		citiesPage.waitForNumbersOfCitiesToBe(1);
		
		Assert.assertTrue(
				 citiesPage.getCellFromRow(1, 2).getText().contains("Ivan Matejev's city"),
				 "ERROR: City name should contains - Ivan Matejev's city");
		
		citiesPage.getDeleteBtnFromRow(1).click();
		citiesPage.waitForDeleteDialog();
		citiesPage.getDeleteDeleteDialogBtn().click();
		
		messagePopUpPage.waitForSuccessfullSavedMsg();
		
		Assert.assertTrue(
				messagePopUpPage.getSavedSuccessfulMsg().getText().contains("Deleted successfully"),
				"ERROR: Wrong message.");
		 
	}
}


