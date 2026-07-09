package sdet.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import sdet.pages.CartPage;
import sdet.pages.CatalogPage;
import sdet.pages.ProductPage;
import sdet.support.DriverFactory;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogPomTest {
    private WebDriver driver;

    @BeforeEach
    void setUp(){
        driver= DriverFactory.createChromeDriver();
    }

    @AfterEach
    void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
    @Test
    void searchFindsOnlyMatchingProduct(){
        CatalogPage catalog=new CatalogPage(driver)
                .open()
                .searchFor("headphones","Showing 1 product");

        List<String> titles=catalog.titles();

        assertAll(
                ()->assertFalse(titles.isEmpty(),"search returned no products"),
                ()->assertTrue(
                        titles.stream().allMatch((title)->title.toLowerCase().contains("headphone")),
                        "search results should be related to headphones"
                )
        );
    }

    @Test
    @DisplayName("POM sort hides the stale-element inside the page")
    void sortLowToHigh(){
        List<Integer> prices=new CatalogPage(driver)
                .open()
                .sortBy("Price: Low to High")
                .prices();
        assertEquals(prices.stream().sorted().toList(),prices);
    }

//    @Test
//    void sortCategory(){
//        List<String> products=new CatalogPage(driver)
//                .open()
//                .sortCatagory("Bags")
//                .titles();
//        assertEquals(products.stream().sorted(),products);
//    }



    @Test
    @DisplayName("Full journey: catalog to confirmed order")
    void catalogToConfirmedOrder() {

        CatalogPage catalog = new CatalogPage(driver)
                .open()
                .searchFor("headphones","Showing 1 product");

        ProductPage product = catalog
                .openFirstProduct()
                .addToCart();
        product.header().cartBadge().expectCount(1);
        CartPage cart = product.header().openCart();
        assertTrue(cart.lineCount() == 1);
        String message = cart
                .proceed()
                .placeOrder()
                .confirmationText();

        assertTrue(message.toLowerCase().contains("order"));
    }

    @Test
    @DisplayName("POM header component exposes cart badge and cart navigation")
    void headerComponentOpensCart(){
        CatalogPage catalog=new CatalogPage(driver).open();

        catalog.header().cartBadge().expectCount(0);
        CartPage cart=catalog.header().openCart();
        assertEquals(0,cart.lineCount());
    }
}
