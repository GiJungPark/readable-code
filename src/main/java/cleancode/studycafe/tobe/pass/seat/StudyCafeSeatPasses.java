package cleancode.studycafe.tobe.pass.seat;

import cleancode.studycafe.tobe.pass.StudyCafePassType;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> getMatchingPasses(StudyCafePassType selectedPassType) {
        return passes.stream()
            .filter(studyCafePass -> studyCafePass.isSameType(selectedPassType))
            .toList();
    }
}
