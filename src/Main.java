import patterns.decorator.*;
import patterns.factory.*;
import patterns.observer.*;
import patterns.strategy.*;

public class Main {
    public static void main(String[] args) {
        // Factory + Decorator
        Meal meal = MealFactory.createMeal("pizza");
        System.out.println(meal.getName());
        System.out.println(meal.getPrice());

        meal = new CheeseDecorator(meal);
        meal = new SauceDecorator(meal);

        System.out.println(meal.getName());
        System.out.println(meal.getPrice());

        // Strategy
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        System.out.println("Regular price: " + calculator.calculate(meal.getPrice()) + " KZT");
        calculator.setStrategy(new StudentDiscount());
        System.out.println("Student price: " + calculator.calculate(meal.getPrice()) + " KZT");

        // Observer
        OrderStatusNotifier notifier = new OrderStatusNotifier(1);
        CustomerObserver customer = new CustomerObserver();
        KitchenObserver kitchen = new KitchenObserver();
        notifier.subscribe(customer);
        notifier.subscribe(kitchen);
        notifier.changeStatus(OrderStatus.ACCEPTED);
        notifier.changeStatus(OrderStatus.PREPARING);
        notifier.unsubscribe(kitchen);
        notifier.changeStatus(OrderStatus.READY);
    }
}