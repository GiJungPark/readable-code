package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.IOProvider;
import cleancode.studycafe.tobe.io.PassRepository;
import cleancode.studycafe.tobe.pass.*;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final IOProvider ioProvider;
    private final PassRepository passRepository;

    public StudyCafePassMachine(IOProvider ioProvider, PassRepository passRepository) {
        this.ioProvider = ioProvider;
        this.passRepository = passRepository;
    }

    public void run() {
        try {
            ioProvider.showStartMessage();

            StudyCafeSeatPass selectedPass = selectPassFromUser();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
            optionalLockerPass.ifPresentOrElse(
                lockerPass -> ioProvider.showSeatAndLockerPassUsageDetails(selectedPass, lockerPass),
                () -> ioProvider.showSeatPassUsageDetails(selectedPass)
            );

            OrderService orderService = OrderService.of(selectedPass, optionalLockerPass.orElse(null));
            ioProvider.showPassOrderSummary(orderService.getTotalPrice(), orderService.getDiscountPrice());
        } catch (AppException e) {
            ioProvider.showExceptionMessage(e.getMessage());
        } catch (Exception e) {
            ioProvider.showExceptionMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        if (selectedPass.doesNotFixedType()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = getStudyCafeLockerPassCandidate(selectedPass);
        if (lockerPassCandidate.isPresent()) {
            boolean isLockerSelected = ioProvider.isSelectLockerPassFrom(lockerPassCandidate.get());

            if (isLockerSelected) {
                return Optional.of(lockerPassCandidate.get());
            }
        }

        return Optional.empty();
    }

    private StudyCafeSeatPass selectPassFromUser() {
        StudyCafePassType selectedPassType = ioProvider.selectPassType();

        List<StudyCafeSeatPass> matchingPasses = getMatchingPasses(selectedPassType);

        return ioProvider.selectPassFrom(matchingPasses);
    }

    private List<StudyCafeSeatPass> getMatchingPasses(StudyCafePassType selectedPassType) {
        StudyCafeSeatPasses allPasses = passRepository.readStudyCafePasses();
        return allPasses.getMatchingPasses(selectedPassType);
    }

    private Optional<StudyCafeLockerPass> getStudyCafeLockerPassCandidate(StudyCafeSeatPass selectedPass) {
        StudyCafeLockerPasses lockerPasses = passRepository.readLockerPasses();
        return lockerPasses.getLockerPassCandidate(selectedPass);
    }

}
