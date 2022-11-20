package com.dividend.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DividendsRetrival {

	public WebDriver driver;

	@Test
	public void GetDividendData() throws FilloException, IOException {

		String filePath = "C:\\Users\\Sandeep\\Desktop\\DividendData.xlsx";

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		List<String> divList = new Excelreadwrite().GetTickervalues(filePath);

		for (String s : divList) {
			driver.get("https://www.nasdaq.com/market-activity/stocks/" + s + "/dividend-history");

			driver.manage().window().maximize();

			int elements = driver.findElements(By.xpath(
					"(//span[@class='dividend-history__summary-item__label' and text()='EX-DIVIDEND DATE']/following::span)[1]"))
					.size();

			if (elements > 0) {
				String exdividend = driver.findElement(By.xpath(
						"(//span[@class='dividend-history__summary-item__label' and text()='EX-DIVIDEND DATE']/following::span)[1]"))
						.getText().trim();

				String dividendyield = driver.findElement(By.xpath(
						"(//span[@class='dividend-history__summary-item__label' and text()='DIVIDEND YIELD']/following::span)[1]"))
						.getText().trim();

				String annualdividend = driver.findElement(By.xpath(
						"(//span[@class='dividend-history__summary-item__label' and text()='ANNUAL DIVIDEND']/following::span)[1]"))
						.getText().trim();

				String peratio = driver.findElement(By.xpath(
						"(//span[@class='dividend-history__summary-item__label' and text()='P/E RATIO']/following::span)[1]"))
						.getText().trim();

				new Excelreadwrite().UpdateData("C:\\Users\\Sandeep\\Desktop\\DividendData.xlsx", exdividend,
						dividendyield, annualdividend, peratio, s);

			} else {
				if (driver.findElement(By.xpath("//h2[@class='alert__heading']")).isDisplayed()) {
					String error = driver.findElement(By.xpath("//h2[@class='alert__heading']")).getText().trim();
					new Excelreadwrite().UpdateData(filePath, error, error, error, error, s);
				}
			}

		}

		driver.quit();	
		Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		Runtime.getRuntime().exec("taskkill /F /IM Chrome.exe");
		
		
	}

}
