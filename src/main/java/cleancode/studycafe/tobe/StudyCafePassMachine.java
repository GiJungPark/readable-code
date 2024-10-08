package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        try {
            /* 시작 메세지 출력 */
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            /* 사용자 이용권 선택 */
            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            /* 모든 이용권 불러오기 */
            StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
            List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();

            /* 이용권에 맞는 목록 불러오기 */
            List<StudyCafePass> selectPasses = allPasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();

            /* 이용권 목록에서 선택 */
            outputHandler.showPassListForSelection(selectPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(selectPasses);

            /* 사물함을 이용할 수 있는 이용권인지 가져옴 */
            StudyCafeLockerPass lockerPass = getStudyCafeLockerPass(studyCafeFileHandler, selectedPass);

            /* 사물함을 사용할 수 있는 경우, 사용 여부 확인*/
            boolean lockerSelection = false;
            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                lockerSelection = inputHandler.getLockerSelection();
            }

            /* 사물함 사용을 희망하는 경우, 이용권 결제 금액 출력*/
            if (lockerSelection) {
                outputHandler.showPassOrderSummary(selectedPass, lockerPass);
            }
            /* 사물함을 사용할 수 없거나, 사용하지 않는 경우, 이용권 결제 금액 출력 */
            else {
                outputHandler.showPassOrderSummary(selectedPass, null);
            }

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeLockerPass getStudyCafeLockerPass(StudyCafeFileHandler studyCafeFileHandler, StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
    }

}
