package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {

        try {
            showStartMessage();

            StudyCafePass selectedPass = selectPassFromUser();

            StudyCafeLockerPass lockerPassAvailability = selectLockerPassFromUser(selectedPass);

            showTotalPriceMessage(selectedPass, lockerPassAvailability);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showStartMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafePass selectPassFromUser() {
        StudyCafePassType selectedPassType = selectPassTypeFromUser();

        // TODO: 모든 이용권을 가져오는 것은 시작시에 이뤄지야 하는 것이 아닌가? vs 사용 시점에서 불러와야 하는 것이 아닌가?
        List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();

        List<StudyCafePass> matchingPasses = getMatchingPasses(allPasses, selectedPassType);

        StudyCafePass selectedPass = selectPassFromUserAbout(matchingPasses);
        return selectedPass;
    }

    private StudyCafePassType selectPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private static List<StudyCafePass> getMatchingPasses(List<StudyCafePass> allPasses, StudyCafePassType selectedPassType) {
        return allPasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == selectedPassType)
            .toList();
    }

    private StudyCafePass selectPassFromUserAbout(List<StudyCafePass> matchingPasses) {
        outputHandler.showPassListForSelection(matchingPasses);
        return inputHandler.getSelectPass(matchingPasses);
    }

    private StudyCafeLockerPass selectLockerPassFromUser(StudyCafePass selectedPass) {
        Optional<StudyCafeLockerPass> lockerPass = getStudyCafeLockerPass(selectedPass);
        if (lockerPass.isPresent()) {
            if (selectLockerPassFromUser(lockerPass.get())) {
                return lockerPass.get();
            }
        }
        // TODO: null을 반환하는 행위는 좋지 않다.
        //  그렇다고 Optional로 반환하게 된다면, 해당 메서드와 이를 사용하는 곳 둘 다 Optional을 처리해야 한다.
        return null;
    }

    private Optional<StudyCafeLockerPass> getStudyCafeLockerPass(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst();
    }

    private boolean selectLockerPassFromUser(StudyCafeLockerPass lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        if (inputHandler.getLockerSelection()) {
            return true;
        }
        return false;
    }

    private void showTotalPriceMessage(StudyCafePass selectedPass, StudyCafeLockerPass lockerPassAvailability) {
        outputHandler.showPassOrderSummary(selectedPass, lockerPassAvailability);
    }

}
