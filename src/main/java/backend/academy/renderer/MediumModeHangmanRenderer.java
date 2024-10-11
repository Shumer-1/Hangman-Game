package backend.academy.renderer;

import java.io.PrintStream;

public class MediumModeHangmanRenderer extends HangmanRenderer {
    private final PrintStream printer;
    private final String[] stepValues;

    public MediumModeHangmanRenderer(PrintStream printStream) {
        printer = printStream;
        stepValues = new String[] {firstStepPicture, secondStepPicture, thirdStepPicture, fourthStepPicture,
            fifthStepPicture, seventhStepPicture, ninthStepPicture};
    }

    @Override
    public void render(int step) {
        printer.println(stepValues[step]);
    }
}
