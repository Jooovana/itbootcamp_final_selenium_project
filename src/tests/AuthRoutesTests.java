package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests   extends BasicTest {

	@Test(priority=1)
	public void forbidsVisitsToHomeUrlIfNotAuthenticated() {
//		Ucitati /home stranu
//		Verifikovati da se u url-u stranice javlja ruta /login
		driver.get(baseUrl + "/home");
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/login"),
				"ERROR: Url should ends with /login");
	
	}
	
	@Test(priority=2)
	public void forbidsVisitsToProfileUrlIfNotAuthenticated() {
//		Ucitati /profile stranu
//		Verifikovati da se u url-u stranice javlja ruta /login
		driver.get(baseUrl + "/profile");
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/login"),
				"ERROR: Url should ends with /login");
	}
	
	@Test(priority=3)
	public void  forbidsVisitsToAdminCitiesUrlIfNotAuthenticated() {
//		Ucitati /admin/cities stranu
//		Verifikovati da se u url-u stranice javlja ruta /login
		driver.get(baseUrl + "/admin/cities");
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/login"),
				"ERROR: Url should ends with /login");
	}
	
	@Test(priority=4)
	public void  forbidsVisitsToAdminUsersUrlIfNotAuthenticated() {
//		Ucitati /admin/users stranu
//		Verifikovati da se u url-u stranice javlja ruta /login
		driver.get(baseUrl + "/admin/users");
		
		Assert.assertTrue(
				driver.getCurrentUrl().endsWith("/login"),
				"ERROR: Url should ends with /login");
	}
	

}
