package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPasses;

public interface PassRepository {

    StudyCafeSeatPasses readStudyCafePasses();

    StudyCafeLockerPasses readLockerPasses();
}
