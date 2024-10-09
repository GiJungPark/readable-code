package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.repository.file.PassCsvRepository;
import cleancode.studycafe.tobe.presentation.console.ConsoleIOProvider;
import cleancode.studycafe.tobe.presentation.console.ConsoleInputHandler;
import cleancode.studycafe.tobe.presentation.console.ConsoleOutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            new ConsoleIOProvider(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()),
            new PassCsvRepository()
        );
        studyCafePassMachine.run();
    }

}
