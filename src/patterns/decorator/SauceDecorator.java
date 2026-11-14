package patterns.decorator;

import patterns.factory.Meal;

public class SauceDecorator extends MealDecorator {
    public SauceDecorator(Meal meal) {
        super(meal);
    }

    public String getName() {
        return meal.getName() + " + Sauce";
    }

    public double getPrice() {
        return meal.getPrice() + 200;
    }
}
