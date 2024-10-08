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
            Optional<StudyCafeLockerPass> optionalLockerPass = getStudyCafeLockerPass(selectedPass);

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

    private Optional<StudyCafeLockerPass> getStudyCafeLockerPass(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst();
    }

}
