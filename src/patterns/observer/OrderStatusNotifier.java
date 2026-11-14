package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusNotifier {
    private final int orderId;
    private final List<OrderObserver> observers = new ArrayList<>();
    private OrderStatus status;

    public OrderStatusNotifier(int orderId) {
        this.orderId = orderId;
    }

    public void subscribe(OrderObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    public void changeStatus(OrderStatus newStatus) {
        status = newStatus;
        for (OrderObserver observer : observers) {
            observer.update(orderId, status);
        }
    }
}
