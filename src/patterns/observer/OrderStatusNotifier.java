package patterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderStatusNotifier {
    private final int orderId;
    private final List<OrderObserver> observers = new ArrayList<>();
    private OrderStatus status;

    public OrderStatusNotifier(int orderId) {
        this.orderId = orderId;
    }

    public void subscribe(OrderObserver observer) {
        Objects.requireNonNull(observer, "Observer is required");
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    public void changeStatus(OrderStatus newStatus) {
        Objects.requireNonNull(newStatus, "Status is required");
        status = newStatus;
        for (OrderObserver observer : new ArrayList<>(observers)) {
            observer.update(orderId, status);
        }
    }
}