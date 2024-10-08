package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.InputException;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.pass.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        throw new InputException("이용권 번호를 잘못 입력하셨습니다.");
    }

    public StudyCafeSeatPass getSelectPass(List<StudyCafeSeatPass> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;

        if (selectedIndex < 0 || selectedIndex >= passes.size()) {
            throw new InputException("이용권 목록 번호를 잘못 입력하셨습니다.");
        }

        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        if ("1".equals(userInput)) {
            return true;
        }

        if ("2".equals(userInput)) {
            return false;
        }

        throw new InputException("사물함 이용 번호를 잘못 입력하셨습니다.");
    }

}
