package cleancode.studycafe.tobe.model;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
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

    public StudyCafePassType getType() {
        return this.passType;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
