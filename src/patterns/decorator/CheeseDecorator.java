package patterns.decorator;

import patterns.factory.Meal;

public class CheeseDecorator extends MealDecorator {
    public CheeseDecorator(Meal meal) {
        super(meal);
    }

    public String getName() {
        return meal.getName() + " + Cheese";
    }

    public double getPrice() {
        return meal.getPrice() + 500;
    }
}

