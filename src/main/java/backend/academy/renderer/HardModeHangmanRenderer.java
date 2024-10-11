package backend.academy.renderer;

import java.io.PrintStream;

public class HardModeHangmanRenderer extends HangmanRenderer {
    private final PrintStream printer;
    private final String[] stepValues;

    public HardModeHangmanRenderer(PrintStream printStream) {
        printer = printStream;
        stepValues = new String[] {firstStepPicture, secondStepPicture, thirdStepPicture, fourthStepPicture,
            fifthStepPicture, sixthStepPicture, seventhStepPicture, eighthStepPicture, ninthStepPicture};
    }

    @Override
    public void render(int step) {
        printer.println(stepValues[step]);
    }
}
