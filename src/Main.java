import patterns.factory.*;
import patterns.decorator.*;

public class Main {
    public static void main(String[] args) {
        Meal meal = MealFactory.createMeal("pizza");

        System.out.println(meal.getName());
        System.out.println(meal.getPrice());

        meal = new CheeseDecorator(meal);
        meal = new SauceDecorator(meal);

        System.out.println(meal.getName());
        System.out.println(meal.getPrice());
    }
}

