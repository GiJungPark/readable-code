package cleancode.studycafe.tobe.io.file;

import cleancode.studycafe.tobe.io.PassRepository;
import cleancode.studycafe.tobe.pass.*;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.tobe.pass.seat.StudyCafeSeatPasses;

import java.util.ArrayList;
import java.util.List;

public class PassCsvRepository implements PassRepository {

    private static final String SEAT_PASS_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private static final String LOCKER_PASS_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    private static final String CSV_SEPARATOR = ",";

    @Override
    public StudyCafeSeatPasses readStudyCafePasses() {
        List<String> lines = FileReader.readFrom(SEAT_PASS_CSV_PATH);
        List<StudyCafeSeatPass> studyCafeSeatPasses = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(CSV_SEPARATOR);
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);
            double discountRate = Double.parseDouble(values[3]);

            StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
            studyCafeSeatPasses.add(studyCafeSeatPass);
        }

        return StudyCafeSeatPasses.of(studyCafeSeatPasses);
    }

    @Override
    public StudyCafeLockerPasses readLockerPasses() {
        List<String> lines = FileReader.readFrom(LOCKER_PASS_CSV_PATH);
        List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(CSV_SEPARATOR);
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);

            StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
            lockerPasses.add(lockerPass);
        }

        return StudyCafeLockerPasses.of(lockerPasses);
    }
}
