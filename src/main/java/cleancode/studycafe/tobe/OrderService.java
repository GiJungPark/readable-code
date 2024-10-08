package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;

public class OrderService {

    private int totalPrice;
    private int discountPrice;

    private OrderService(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        double discountRate = seatPass.getDiscountRate();
        this.discountPrice = (int) (seatPass.getPrice() * discountRate);
        this.totalPrice = (int) seatPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
    }

    public static OrderService of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        return new OrderService(seatPass, lockerPass);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
