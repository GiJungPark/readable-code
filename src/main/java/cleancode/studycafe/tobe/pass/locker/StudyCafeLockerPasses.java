package cleancode.studycafe.tobe.pass.locker;

import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> passes;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public Optional<StudyCafeLockerPass> getLockerPassCandidate(StudyCafeSeatPass selectedPass) {
        return this.passes.stream()
            .filter(selectedPass::isSameDurationType)
            .findFirst();
    }
}
