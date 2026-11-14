package patterns.facade;

import java.util.List;
import java.util.Locale;
import patterns.builder.Order;
import patterns.builder.OrderBuilder;
import patterns.decorator.CheeseDecorator;
import patterns.decorator.SauceDecorator;
import patterns.factory.Meal;
import patterns.factory.MealFactory;


public class RestaurantFacade {

    public Meal createMeal(String type, boolean extraCheese, boolean extraSauce) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Meal type is required");
        }


        Meal meal = MealFactory.createMeal(type.trim());


        if (extraCheese) {
            meal = new CheeseDecorator(meal);
        }
        if (extraSauce) {
            meal = new SauceDecorator(meal);
        }
        return meal;
    }

    public Order placeOrder(String customerName, int tableNumber,
                            List<Meal> meals, String comment) {
        if (meals == null) {
            throw new IllegalArgumentException("Meal list is required");
        }

        OrderBuilder builder = new OrderBuilder()
                .setCustomerName(customerName)
                .setTableNumber(tableNumber)
                .setComment(comment);

        for (Meal meal : meals) {
            builder.addMeal(meal);
        }
        return builder.build();
    }

    public String getReceipt(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is required");
        }
        StringBuilder receipt = new StringBuilder("\n===== RESTAURANT ORDER =====\n");
        receipt.append("Customer: ").append(order.getCustomerName()).append('\n');
        receipt.append("Table: ").append(order.getTableNumber()).append('\n');
        for (Meal meal : order.getMeals()) {
            receipt.append(meal.getName()).append(" - ")
                    .append(String.format(Locale.US, "%.2f KZT", meal.getPrice()))
                    .append('\n');
        }
        if (!order.getComment().isEmpty()) {
            receipt.append("Comment: ").append(order.getComment()).append('\n');
        }
        receipt.append("TOTAL: ")
                .append(String.format(Locale.US, "%.2f KZT", order.getTotalPrice()))
                .append("\n===========================\n");
        return receipt.toString();
    }
}
