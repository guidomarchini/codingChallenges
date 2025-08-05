package mongodb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * Question:
 * Design and implement a Shopping Cart Checkout Service that calculates the total amount a customer must pay at checkout. A product type can be configured by an Admin to have a promotion beforehand. During checkout, the service should automatically apply the applicable promotions based on the customer’s cart contents when conditions are met. Promotions can be applied multiple times.

The service must return the following information:

- Cart subtotal: The sum of the regular prices for all items in the cart without promotions applied.
- Total promotions applied: The total discount resulting from applied promotions (as a negative amount).
- Total amount due: The amount the customer needs to pay after applying promotions (subtotal + total promotions applied).

For now, the supported promotions are:
- Buy 4 get additional 1 50% off
- Buy 2 get additional 1 free
 */

enum Product {
    APPLES(0.75),
    ORANGES(1.00),
    BANANAS(0.50);

    private double price;

    Product(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    Map<Product, Integer> cartMap = new HashMap<>();

    public void addToCart(Product product, int amount) {
        cartMap.put(product, amount);
    }
}

class PromotionService {
    private Map<Product, PromotionHandler> promotions = new HashMap<>();

    public void addPromotion(Product product, PromotionHandler handler) {
        promotions.put(product, handler);
    }

    public double applyPromotions(ShoppingCart shoppingCart) {
        double total = 0d;
        for (Entry<Product, Integer> productEntry : shoppingCart.cartMap.entrySet()) {
            total += applyPromotions(productEntry.getKey(), productEntry.getValue());
        }
        return total;
    }

    public double applyPromotions(Product product, Integer amount) {
        if (promotions.containsKey(product)) {
            return promotions.get(product).applyPromotion(product, amount);
        }
        return 0;
    }
}

abstract class PromotionHandler {
    protected Integer applicableAmount;
    protected Integer percentageDiscount;

    PromotionHandler(Integer applicableAmount, Integer percentageDiscount) {
        this.applicableAmount = applicableAmount;
        this.percentageDiscount = percentageDiscount;
    }

    public double applyPromotion(Product product, Integer amount) {
        Integer productToApplyPromotion = amount / applicableAmount;
        // percentageDiscount // calculate the %
        return product.getPrice() * percentageDiscount / 100 * productToApplyPromotion;
    }
}

class Buy4Get1HalfThePrice extends PromotionHandler {
    Buy4Get1HalfThePrice() {
        super(5, 50);
    }
}

class Buy2Get1Free extends PromotionHandler {
    Buy2Get1Free() {
        super(3, 100);
    }
}

class ShoppingCartTotal {
    public double subtotal;
    public double promotionsApplied;
    public double totalAmount;

    public ShoppingCartTotal(
        double subtotal,
        double promotionsApplied,
        double totalAmount
    ) {
        this.subtotal = subtotal;
        this.promotionsApplied = promotionsApplied;
        this.totalAmount = totalAmount;
    }
}

class Solution {
    public static void main(String[] args) {
        // set the handlers
        PromotionService promotionService = new PromotionService();
        promotionService.addPromotion(Product.ORANGES, new Buy4Get1HalfThePrice());
        promotionService.addPromotion(Product.APPLES, new Buy2Get1Free());

        // initialize the shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(Product.APPLES, 7);
        shoppingCart.addToCart(Product.ORANGES, 0);
        shoppingCart.addToCart(Product.BANANAS, 0);

        ShoppingCartTotal total = calculateShoppingCartTotal(shoppingCart, promotionService);
        System.out.println("subtotal :" + total.subtotal);
        System.out.println("promotionApplied :" + total.promotionsApplied);
        System.out.println("totalAmount :" + total.totalAmount);
    }

    static ShoppingCartTotal calculateShoppingCartTotal(
        ShoppingCart shoppingCart,
        PromotionService promotionService
    ) {
        double subtotal = 0d;
        for (Entry<Product, Integer> productEntry : shoppingCart.cartMap.entrySet()) {
            subtotal += productEntry.getKey().getPrice() * productEntry.getValue();
        }
        double promotionsApplied = promotionService.applyPromotions(shoppingCart);
        double totalAmount = subtotal - promotionsApplied;

        return new ShoppingCartTotal(subtotal, promotionsApplied, totalAmount);
    }
}


// Your previous Plain Text content is preserved below:

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dots menu on the tab, or add a new language
// tab using the Languages button on the left.

// You can also change the default language your pads are created with
// in your account settings: https://app.coderpad.io/settings

// Enjoy your interview!