package patterns.builder;

import java.util.List;
import patterns.factory.Meal;

public class Order {
    private final String customerName;
    private final int tableNumber;
    private final String comment;
    private final List<Meal> meals;

    Order(String customerName, int tableNumber, String comment, List<Meal> meals) {
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.comment = comment;
        this.meals = List.copyOf(meals);
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getComment() {
        return comment;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Meal meal : meals) {
            total += meal.getPrice();
        }
        return total;
    }
}
