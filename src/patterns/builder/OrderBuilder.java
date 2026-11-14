package patterns.builder;

import java.util.ArrayList;
import java.util.List;
import patterns.factory.Meal;


public class OrderBuilder {
    private String customerName;
    private int tableNumber;
    private String comment = "";
    private final List<Meal> meals = new ArrayList<>();

    public OrderBuilder setCustomerName(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        this.customerName = customerName.trim();
        return this;
    }

    public OrderBuilder setTableNumber(int tableNumber) {
        if (tableNumber <= 0) {
            throw new IllegalArgumentException("Table number must be positive");
        }
        this.tableNumber = tableNumber;
        return this;
    }

    public OrderBuilder setComment(String comment) {
        this.comment = comment == null ? "" : comment.trim();
        return this;
    }

    public OrderBuilder addMeal(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }
        meals.add(meal);
        return this;
    }

    public Order build() {
        if (customerName == null) {
            throw new IllegalStateException("Set the customer name before build()");
        }
        if (tableNumber <= 0) {
            throw new IllegalStateException("Set the table number before build()");
        }
        if (meals.isEmpty()) {
            throw new IllegalStateException("Add at least one meal before build()");
        }
        return new Order(customerName, tableNumber, comment, meals);
    }
}
