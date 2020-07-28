package br.com.recatalog.selenium.demo;


import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
	
	
	
	@Test
	public void testLogin() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Download\\Software\\Development\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		navegador.get("http://www.b3.com.br/pt_br/market-data-e-indices/servicos-de-dados/market-data/cotacoes/cotacoes/");

		WebDriver w1 = navegador.switchTo().frame("bvmf_iframe");
		WebElement we = w1.findElement(By.cssSelector("input[type='text']"));
		WebElement wsub = w1.findElement(By.cssSelector("input[type='submit']"));

		we.sendKeys("B3SA3");
		we.sendKeys(Keys.ENTER); // escolha ação
		
		wsub.sendKeys(Keys.ENTER); // pesquisa ...
		
		WebElement wlink = w1.findElement(By.linkText("5"));
		wlink.sendKeys(Keys.ENTER); // vai para página 5

		assertEquals(1, 1);
	}
}