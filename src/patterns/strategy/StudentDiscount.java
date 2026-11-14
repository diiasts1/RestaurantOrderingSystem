package patterns.strategy;

public class StudentDiscount implements DiscountStrategy {
    public double apply(double price) {
        return price * 0.9; // 10% off
    }
}
