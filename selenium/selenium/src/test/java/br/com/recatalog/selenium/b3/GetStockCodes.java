package br.com.recatalog.selenium.b3;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetStockCodes {
	
	@Test
	public void findStockCodes() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Download\\Software\\Development\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		navegador.get("http://www.b3.com.br/pt_br/produtos-e-servicos/negociacao/renda-variavel/empresas-listadas.htm");

		WebDriver w1 = navegador.switchTo().frame("bvmf_iframe");
		WebElement we = w1.findElement(By.cssSelector("input[value='Todas']"));
		we.click();
		
		WebElement tableElement = w1.findElement(By.className("MasterTable_SiteBmfBovespa"));
		System.out.println(tableElement.getTagName());
		
		WebElement tbody = tableElement.findElement(By.cssSelector("tbody"));
		System.out.println(tbody.getTagName());

		List<WebElement> tds = tbody.findElements(By.cssSelector("td"));

		for (WebElement td : tds) {
			System.out.println(td.getText());
			WebElement linToStock = td.findElement(By.cssSelector("a"));
			linToStock.click();
			WebDriver w11 = navegador.switchTo().frame("ctl00_contentPlaceHolderConteudo_iframeCarregadorPaginaExterna");
			WebElement tableElement1 = w11.findElement(By.className("ficha"));

			WebElement tbody1 = tableElement1.findElement(By.cssSelector("tbody"));
			System.out.println(tbody1.getTagName());

			WebElement tlink1 =  tbody1.findElement(By.className("LinkCodNeg"));
			System.out.println(tlink1.getText());
			break;
		}		

//		//				elementList = parentElement.find_elements_by_tag_name("li")
//		we = w1.findElement(By.cssSelector("a"));
//		we.click();

		assertEquals(1, 1);
	}
}