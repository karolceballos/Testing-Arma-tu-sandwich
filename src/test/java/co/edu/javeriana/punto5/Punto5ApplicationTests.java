package co.edu.javeriana.punto5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
class Punto5ApplicationTests {
    private WebDriver driver;
    private WebDriverWait wait;

    private String baseUrl = "file:///C:/Users/Karol/OneDrive/Escritorio/ParcialWebKarolCeballos/punto2/sandwich.html";

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extensions");

        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

	@Test
	void agregarElemento() {
	
		driver.get(baseUrl);
	
		Select listaIzqSelect = new Select(driver.findElement(By.id("comtizq")));
		listaIzqSelect.selectByValue("queso");
		WebElement botonAgregar = driver.findElement(By.id("addButtom"));
		botonAgregar.click();
		Select listDerSelect = new Select(driver.findElement(By.id("comtDer")));
		List<WebElement> Izq = listaIzqSelect.getOptions();
		List<WebElement> Der = listDerSelect.getOptions();
	    assertEquals(5,Izq.size());
		assertEquals("queso", Der.get(0).getText());
	}
     

	@Test
	void eliminarElemento() {
		 driver.get(baseUrl);
    Select ListIzqSelect = new Select(driver.findElement(By.id("comtizq")));
   ListIzqSelect.selectByValue("tomate");
    WebElement botonAgregar = driver.findElement(By.id("addButtom"));
    botonAgregar.click();
    Select ListaDerSelect = new Select(driver.findElement(By.id("comtDer")));
    ListaDerSelect.selectByIndex(0);
    WebElement botonEliminar = driver.findElement(By.id("remove button"));
    botonEliminar.click();
    List<WebElement> Izq = ListIzqSelect.getOptions();
    List<WebElement> Der = ListaDerSelect.getOptions();
    assertEquals("Tomate", Izq.get(Izq.size() - 1).getText());
    assertEquals(0, Der.size());
	}

	@Test
	void agregarElementos() {
		 driver.get(baseUrl);
		Select listIzqSelect = new Select(driver.findElement(By.id("comtizq")));
		listIzqSelect.selectByValue("pollo");
		listIzqSelect.selectByValue("lechuga");
		listIzqSelect.selectByValue("tomate");
		listIzqSelect.selectByValue("pan");
		WebElement botonAgregar = driver.findElement(By.id("addButtom"));
		botonAgregar.click();
		Select listDer = new Select(driver.findElement(By.id("comtDer")));
		List<WebElement> Izq = listIzqSelect.getOptions();
		List<WebElement> Der = listDer.getOptions();
		assertEquals(2, Izq.size());
		assertEquals(4, Der.size());
	}
	
	@Test
	void variosElementosEliminados() {
		 driver.get(baseUrl);
		Select listIzqSelect = new Select(driver.findElement(By.id("comtizq")));
		listIzqSelect.selectByValue("pan");
		listIzqSelect.selectByValue("carne");
		listIzqSelect.selectByValue("lechuga");
		listIzqSelect.selectByValue("tomate");
		WebElement botonAgregar = driver.findElement(By.id("addButtom"));
		botonAgregar.click();
		Select listaDerSelect = new Select(driver.findElement(By.id("comtDer")));
		listaDerSelect.selectByValue("lechuga");
		listaDerSelect.selectByValue("pan");
		WebElement botonEliminar = driver.findElement(By.id("remove button"));
		botonEliminar.click();
		List<WebElement> izq = listIzqSelect.getOptions();
		List<WebElement> Der = listaDerSelect.getOptions();
		assertEquals(4, izq.size());
		assertEquals(2, Der.size());
	}
	
	@Test
	void sinAgregarElemento() {
		 driver.get(baseUrl);
		Select listIzqSelect = new Select(driver.findElement(By.id("comtizq")));
		WebElement botonAgregar = driver.findElement(By.id("addButtom"));
		botonAgregar.click();
		Select listDerSelect = new Select(driver.findElement(By.id("comtDer")));
		List<WebElement> izq = listIzqSelect.getOptions();
		List<WebElement> der = listDerSelect.getOptions();
		assertEquals(6, izq.size());
		assertEquals(0, der.size());
	}
	
	@Test
	void sinEliminarElemento() {
		 driver.get(baseUrl);
		Select listaIzqSelect = new Select(driver.findElement(By.id("comtizq")));
		Select listDerSelect = new Select(driver.findElement(By.id("comtDer")));
		WebElement botonEliminar = driver.findElement(By.id("remove button"));
		botonEliminar.click();
		List<WebElement> Izq = listaIzqSelect.getOptions();
		List<WebElement> Der = listDerSelect.getOptions();
		assertEquals(6, Izq.size());
		assertEquals(0, Der.size());
	}

@Test
void removerPosIzquierda() {
	 driver.get(baseUrl);
    Select listIzqSelect = new Select(driver.findElement(By.id("comtizq")));
    listIzqSelect.selectByValue("pollo");
    WebElement botonBorrar = driver.findElement(By.id("remove button"));
    botonBorrar.click();
    List<WebElement> Izq = listIzqSelect.getOptions();
    boolean polloPresente = Izq.stream().anyMatch(option -> option.getText().equals("pollo"));
    assertFalse(polloPresente);
}


@Test
void agregarPosDer() {
    driver.get(baseUrl);
    Select listaIzq = new Select(driver.findElement(By.id("comtizq")));
    listaIzq.selectByValue("pollo");
    WebElement botonAgregar = driver.findElement(By.id("addButtom"));
    botonAgregar.click();
    Select listDerSelect = new Select(driver.findElement(By.id("comtDer")));
    listDerSelect.selectByValue("pollo");
    botonAgregar.click();
    listDerSelect.deselectByValue("pollo");
    List<WebElement> Der = listDerSelect.getOptions();
    boolean polloPresenteEnDer = Der.stream().anyMatch(option -> option.getText().equals("pollo"));
    assertTrue(polloPresenteEnDer);
}



}
