package patterns.factory;

public class Salad implements Meal {
    public String getName() {
        return "Salad";
    }

    public double getPrice() {
        return 1500;
    }
}
