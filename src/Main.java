import java.util.List;
import java.util.Locale;
import patterns.builder.Order;
import patterns.facade.RestaurantFacade;
import patterns.factory.Meal;
import patterns.observer.CustomerObserver;
import patterns.observer.KitchenObserver;
import patterns.observer.OrderStatus;
import patterns.observer.OrderStatusNotifier;
import patterns.strategy.DiscountStrategy;
import patterns.strategy.NoDiscount;
import patterns.strategy.PriceCalculator;
import patterns.strategy.StudentDiscount;

public class Main {
    public static void main(String[] args) {
        RestaurantFacade restaurant = new RestaurantFacade();

        // Facade calls the original Factory and Decorators internally.
        Meal pizza = restaurant.createMeal("pizza", true, true);
        // Facade calls the original OrderBuilder internally.
        Order studentOrder = restaurant.placeOrder("Abylaikhan", 3,
                List.of(pizza), "Serve hot");
        processOrder(1, studentOrder, new StudentDiscount(), restaurant);

        Meal burger = restaurant.createMeal("burger", false, false);
        Meal salad = restaurant.createMeal("salad", false, false);
        Order regularOrder = restaurant.placeOrder("Guest", 5,
                List.of(burger, salad), "Serve together");
        processOrder(2, regularOrder, new NoDiscount(), restaurant);
    }

    // All operations in this method concern the SAME Order and its orderId.
    private static void processOrder(int orderId, Order order,
                                     DiscountStrategy strategy, RestaurantFacade restaurant) {
        if (strategy == null) throw new IllegalArgumentException("Discount strategy is required");
        System.out.println("\n=== ORDER #" + orderId + " ===");
        System.out.println("Created through RestaurantFacade using Factory, Decorator and Builder.");

        // The original receipt contains the subtotal, so label it accurately.
        System.out.println("ORDER RECEIPT BEFORE DISCOUNT:");
        System.out.print(restaurant.getReceipt(order));

        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        double subtotal = calculator.calculate(order.getTotalPrice());
        calculator.setStrategy(strategy);
        double amountDue = calculator.calculate(order.getTotalPrice());
        System.out.println("PAYMENT FOR ORDER #" + orderId);
        System.out.println("Strategy: " + strategy.getClass().getSimpleName());
        System.out.printf(Locale.US, "Subtotal: %.2f KZT%n", subtotal);
        System.out.printf(Locale.US, "Discount: %.2f KZT%n", subtotal - amountDue);
        System.out.printf(Locale.US, "AMOUNT DUE: %.2f KZT%n", amountDue);

        // One notifier per order; each recipient is subscribed exactly once.
        OrderStatusNotifier notifier = new OrderStatusNotifier(orderId);
        notifier.subscribe(new CustomerObserver());
        notifier.subscribe(new KitchenObserver());
        notifier.changeStatus(OrderStatus.ACCEPTED);
        notifier.changeStatus(OrderStatus.PREPARING);
        notifier.changeStatus(OrderStatus.READY);
    }
}
