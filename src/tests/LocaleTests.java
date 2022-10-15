package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LocaleTests extends BasicTest {
	
	@Test(priority=1)
	public void setLocaleToES() {
//		Postaviti jezik na ES
//		Verifikovati da se na stranici u hederu javlja tekst Página de aterrizaje
		navPage.getChooseALanguageBtn().click();
		navPage.getEsLang().click();
		
		Assert.assertTrue(
				navPage.getHeaderFromWelcomePage().getText().equals("Página de aterrizaje"),
				"ERROR: Heder should be - Página de aterrizaje");
	}
	
	@Test(priority=2)
	public void setLocaleToEN() {
//		Postaviti jezik na EN
//		Verifikovati da se na stranici u hederu javlja tekst Landing
		navPage.getChooseALanguageBtn().click();
		navPage.getEnLang().click();
		
		Assert.assertTrue(
				navPage.getHeaderFromWelcomePage().getText().equals("Landing"),
				"ERROR: Heder should be - Landing");
	}
	
	@Test(priority=3)
	public void setLocaleToCN() {
//		Postaviti jezik na CN
//		Verifikovati da se na stranici u hederu javlja tekst 首页
		navPage.getChooseALanguageBtn().click();
		navPage.getCnLang().click();
		
		Assert.assertTrue(
				navPage.getHeaderFromWelcomePage().getText().equals("首页"),
				"ERROR: Heder should be - 首页");
	}
	
	@Test(priority=4)
	public void setLocaleToFR() {
//		Postaviti jezik na FR
//		Verifikovati da se na stranici u hederu javlja tekst Page d'atterrissage
		navPage.getChooseALanguageBtn().click();
		navPage.getFrLang().click();
		
		Assert.assertTrue(
				navPage.getHeaderFromWelcomePage().getText().equals("Page d'atterrissage"),
				"ERROR: Heder should be - Page d'atterrissage");
	}

}
