package cleancode.studycafe.tobe.pass.seat;

import cleancode.studycafe.tobe.pass.StudyCafePass;
import cleancode.studycafe.tobe.pass.StudyCafePassType;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean isSameType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSameDuration(this.duration) && lockerPass.isSameType(this.passType);
    }

    public boolean doesNotFixedType() {
        return !doesFixedType();
    }

    private boolean doesFixedType() {
        return this.passType.isLockerType();
    }

    @Override
    public StudyCafePassType getType() {
        return this.passType;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
