package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.pass.StudyCafePassType;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;

import java.util.List;

public interface IOProvider {

    void showStartMessage();

    StudyCafePassType selectPassType();

    StudyCafeSeatPass selectPassFrom(List<StudyCafeSeatPass> matchingPasses);

    boolean isSelectLockerPassFrom(StudyCafeLockerPass lockerPass) ;

    void showSeatAndLockerPassUsageDetails(StudyCafeSeatPass selectedPass, StudyCafeLockerPass lockerPass) ;

    void showSeatPassUsageDetails(StudyCafeSeatPass selectedPass) ;

    void showPassOrderSummary(int totalPrice, int discountPrice) ;

    void showExceptionMessage(String message);
}
