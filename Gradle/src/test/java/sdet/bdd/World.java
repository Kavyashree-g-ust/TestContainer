package sdet.bdd;

import org.openqa.selenium.WebDriver;
import sdet.pages.CartPage;
import sdet.pages.CatalogPage;
import sdet.pages.CheckoutPage;
import sdet.pages.ProductPage;
import sdet.pages.components.Header;


public class World {
    public WebDriver driver;
    public CatalogPage catalog;
    public ProductPage product;
    public CartPage cart;
    public CheckoutPage checkout;

    public Header header(){
        return new Header(driver);
    }
}
