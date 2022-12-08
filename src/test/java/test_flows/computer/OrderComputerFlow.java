package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.*;
import models.components.order.ComputerEssentialComponent;
import models.pages.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.credit.CreditCardType;
import test_data.credit.PaymentMethodType;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private int quantity = 1;
    private double totalItemPrice;
    private UserDataObject defaultCheckoutUser;
    private PaymentMethodType paymentMethod;
    private CreditCardType creditCardType;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectAllDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAddedPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAddedPrice = extractAdditionalPrice(ramFullStr);
        String fullHDDStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double hddAddedPrice = extractAdditionalPrice(fullHDDStr);


        double softwarePrice = 0;
        if (!computerData.getSoftware().isEmpty()) {

            for (String option : computerData.getSoftware()) {
                String softwareStr = computerEssentialComp.selectSoftware(option);
                softwarePrice = softwarePrice + extractAdditionalPrice(softwareStr);

            }
        }

        double osAddedPrice = 0;
        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.selectOS(computerData.getOs());
            osAddedPrice = extractAdditionalPrice(osFullStr);
        }

        if (this.quantity != 1) {
            computerEssentialComp.inputQuantity(this.quantity);
        }

        double totalAddedPrice = processorAddedPrice + ramAddedPrice + hddAddedPrice + osAddedPrice + softwarePrice;
        totalItemPrice = (computerEssentialComp.basePrice() + totalAddedPrice) * this.quantity;


        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerItemDetailsPage.barNotificationComp().waitUntilItemAddedToCart();
        computerItemDetailsPage.barNotificationComp().clickOnCloseBtn();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        int factor = 1;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            String priceStr = matcher.group(1);
            if (priceStr.startsWith("-")) factor = -1;
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price * factor;
    }


    public void verifyShoppingCartPage() {

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComponentList = shoppingCartPage.cartItemRowComponentList();

        if (cartItemRowComponentList.isEmpty()) {
            Assert.fail("[ERR] There is no item display in the shopping cart");
        }

        double allSubTotal = 0;
        for (CartItemRowComponent cartItemRow : cartItemRowComponentList) {

            double currentSubTotal = cartItemRow.subTotal();
            double expectedSubTotal = cartItemRow.quantity() * cartItemRow.unitPrice();
            Assert.assertEquals(currentSubTotal, expectedSubTotal, "[ERR] The subTotal on the item is incorrect");

            allSubTotal = allSubTotal + currentSubTotal;

        }

        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();

        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);

            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }

        Assert.assertEquals(allSubTotal, checkoutSubTotal, "[ERR] Checking out Subtotal is incorrect");
        Assert.assertEquals((checkoutSubTotal + checkoutOtherFeesTotal), checkoutTotal, "[ERR] Checking out Total is incorrect");
    }

    public void agreeTOSAndCheckOut() {

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComponent().agreeTOS();
        shoppingCartPage.totalComponent().clickOnCheckOutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();
    }

    public void inputBillingAddress() {

        String defaultCheckoutUserDataFileLoc = "/src/test/java/test_data/user/DefaultCheckOutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserDataFileLoc, UserDataObject.class);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComponent = checkoutPage.billingAddressComponent();

        billingAddressComponent.selectInputNewAddress();
        billingAddressComponent.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComponent.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComponent.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComponent.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComponent.selectState(defaultCheckoutUser.getState());
        billingAddressComponent.inputCity(defaultCheckoutUser.getCity());
        billingAddressComponent.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComponent.inputZIPCode(defaultCheckoutUser.getZipCode());
        billingAddressComponent.inputPhoneNum(defaultCheckoutUser.getPhoneNum());

        billingAddressComponent.clickOnContinueBtn();

    }

    public void inputShippingAddress() {

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingAddressComponent shippingAddressComponent = checkoutPage.shippingAddressComponent();

        shippingAddressComponent.clickOnContinueBtn();
    }

    public void selectShippingMethod() {

        List<String> shippingMethod = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        int randomElemIndex = new SecureRandom().nextInt(shippingMethod.size());
        String randomMethod = shippingMethod.get(randomElemIndex);

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComponent = checkoutPage.shippingMethodComponent();

        shippingMethodComponent.selectShippingMethod(randomMethod);
        shippingMethodComponent.clickOnContinueBtn();

    }

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethodType.COD;
    }

    public void selectPaymentMethod(PaymentMethodType paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method can't be null");
        }
        this.paymentMethod = paymentMethod;

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkoutPage.paymentMethodComponent();

        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComponent.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComponent.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComponent.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComponent.selectCODMethod();
        }
        paymentMethodComponent.clickOnContinueBtn();
    }

    public void inputPaymentInfo(CreditCardType creditCardType) {
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERR] Credit Card Type can't be null");
        }
        this.creditCardType = creditCardType;

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInformationComponent paymentInformationComponent = checkoutPage.paymentInformationComponent();

        paymentInformationComponent.selectCreditCardType(creditCardType);

        String cardHolderFirstName = defaultCheckoutUser.getFirstName();
        String cardHolderLastName = defaultCheckoutUser.getLastName();
        paymentInformationComponent.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);

        String cardNumber = creditCardType.equals(creditCardType.VISA) ? "4012888888881881" : "6011000990139424";
        paymentInformationComponent.inputCardHolderNumber(cardNumber);

        //Select current month and next year
        Calendar calendar = new GregorianCalendar();
        paymentInformationComponent.inputExpireMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        paymentInformationComponent.inputExpireYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));

        paymentInformationComponent.inputCardCode("123");
        paymentInformationComponent.clickOnContinueBtn();


    }

    public void confirmOrderComponent() {

        //TODO : add verify methods
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ConfirmOrderComponent confirmOrderComponent = checkoutPage.confirmOrderComponent();
        confirmOrderComponent.clickOnContinueBtn();

        new CheckoutCompletedPage(driver).clickOnContinueBtn();
    }



}
