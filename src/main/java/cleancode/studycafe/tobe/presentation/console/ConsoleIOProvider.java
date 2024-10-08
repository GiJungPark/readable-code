package cleancode.studycafe.tobe.presentation.console;

import cleancode.studycafe.tobe.presentation.IOProvider;
import cleancode.studycafe.tobe.pass.StudyCafePassType;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;

import java.util.List;

public class ConsoleIOProvider implements IOProvider {
    private final ConsoleInputHandler consoleInputHandler;
    private final ConsoleOutputHandler consoleOutputHandler;

    public ConsoleIOProvider(ConsoleInputHandler consoleInputHandler, ConsoleOutputHandler consoleOutputHandler) {
        this.consoleInputHandler = consoleInputHandler;
        this.consoleOutputHandler = consoleOutputHandler;
    }

    @Override
    public void showStartMessage() {
        consoleOutputHandler.showWelcomeMessage();
        consoleOutputHandler.showAnnouncement();
    }

    @Override
    public StudyCafePassType selectPassType() {
        consoleOutputHandler.askPassTypeSelection();
        return consoleInputHandler.getPassTypeSelectingUserAction();
    }

    @Override
    public StudyCafeSeatPass selectPassFrom(List<StudyCafeSeatPass> matchingPasses) {
        consoleOutputHandler.showPassListForSelection(matchingPasses);
        return consoleInputHandler.getSelectPass(matchingPasses);
    }

    @Override
    public boolean isSelectLockerPassFrom(StudyCafeLockerPass lockerPass) {
        consoleOutputHandler.askLockerPass(lockerPass);
        return consoleInputHandler.getLockerSelection();
    }

    @Override
    public void showSeatAndLockerPassUsageDetails(StudyCafeSeatPass selectedPass, StudyCafeLockerPass lockerPass) {
        consoleOutputHandler.showPassUsageDetails(selectedPass, lockerPass);
    }

    @Override
    public void showSeatPassUsageDetails(StudyCafeSeatPass selectedPass) {
        consoleOutputHandler.showPassUsageDetails(selectedPass);
    }

    @Override
    public void showPassOrderSummary(int totalPrice, int discountPrice) {
        consoleOutputHandler.showPssOrderSummary(totalPrice, discountPrice);
    }

    @Override
    public void showExceptionMessage(String message) {
        consoleOutputHandler.showSimpleMessage(message);
    }
}
