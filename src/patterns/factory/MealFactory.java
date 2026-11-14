package patterns.factory;

public class MealFactory {
    public static Meal createMeal(String type) {
        if (type.equalsIgnoreCase("pizza")) {
            return new Pizza();
        } else if (type.equalsIgnoreCase("burger")) {
            return new Burger();
        } else if (type.equalsIgnoreCase("salad")) {
            return new Salad();
        }
        throw new IllegalArgumentException("Unknown meal: " + type);
    }
}
