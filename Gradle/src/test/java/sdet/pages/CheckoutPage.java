package sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage extends BasePage {

    private static final By PLACE_ORDER =
            By.cssSelector("button.button.primary");

    private static final By CONFIRMATION_TEXT =
            By.cssSelector(".confirmation-panel");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage placeOrder() {
        click(PLACE_ORDER);
        return this;
    }

    public String confirmationText() {
        return header().Text(CONFIRMATION_TEXT);
    }

    //new method
    public void expectOrderConfirmed() {
        String text = confirmationText().toLowerCase();
        assertTrue(text.contains("order"));
        assertTrue(text.contains("confirmed"));
    }
}