package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.console.ConsoleIOProvider;
import cleancode.studycafe.tobe.io.console.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.console.ConsoleOutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            new ConsoleIOProvider(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()),
            new StudyCafeFileHandler()
        );
        studyCafePassMachine.run();
    }

}
