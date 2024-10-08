package cleancode.studycafe.tobe.repository;

import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPasses;

public interface PassRepository {

    StudyCafeSeatPasses readStudyCafePasses();

    StudyCafeLockerPasses readLockerPasses();
}
