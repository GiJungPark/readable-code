package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.pass.*;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {

        try {
            showStartMessage();

            StudyCafeSeatPass selectedPass = selectPassFromUser();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                () -> outputHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        if (selectedPass.doesNotFixedType()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = getStudyCafeLockerPassCandidate(selectedPass);
        if (lockerPassCandidate.isPresent()) {
            outputHandler.askLockerPass(lockerPassCandidate.get());
            boolean isLockerSelected = inputHandler.getLockerSelection();

            if (isLockerSelected) {
                return Optional.of(lockerPassCandidate.get());
            }
        }

        return Optional.empty();
    }

    private void showStartMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafeSeatPass selectPassFromUser() {
        StudyCafePassType selectedPassType = selectPassTypeFromUser();

        List<StudyCafeSeatPass> matchingPasses = getMatchingPasses(selectedPassType);

        StudyCafeSeatPass selectedPass = selectPassFromUserAbout(matchingPasses);
        return selectedPass;
    }

    private StudyCafePassType selectPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private List<StudyCafeSeatPass> getMatchingPasses(StudyCafePassType selectedPassType) {
        StudyCafeSeatPasses allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.getMatchingPasses(selectedPassType);
    }

    private StudyCafeSeatPass selectPassFromUserAbout(List<StudyCafeSeatPass> matchingPasses) {
        outputHandler.showPassListForSelection(matchingPasses);
        return inputHandler.getSelectPass(matchingPasses);
    }

    private Optional<StudyCafeLockerPass> getStudyCafeLockerPassCandidate(StudyCafeSeatPass selectedPass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.getLockerPassCandidate(selectedPass);
    }

}
