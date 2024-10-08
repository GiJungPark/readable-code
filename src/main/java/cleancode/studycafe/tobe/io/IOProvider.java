package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.pass.StudyCafePassType;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;

import java.util.List;

public class IOProvider {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public IOProvider(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void showStartMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    public StudyCafePassType selectPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafeSeatPass selectPassFrom(List<StudyCafeSeatPass> matchingPasses) {
        outputHandler.showPassListForSelection(matchingPasses);
        return inputHandler.getSelectPass(matchingPasses);
    }

    public boolean isSelectLockerPassFrom(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    public void showSeatAndLockerPassOrderSummary(StudyCafeSeatPass selectedPass, StudyCafeLockerPass lockerPass) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPass);
    }

    public void showSeatPassOrderSummary(StudyCafeSeatPass selectedPass) {
        outputHandler.showPassOrderSummary(selectedPass);
    }

    public void showExceptionMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }
}
