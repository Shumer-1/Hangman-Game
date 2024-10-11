package backend.academy.renderer;

import java.io.PrintStream;

public class EasyModeHangmanRenderer extends HangmanRenderer {

    private final PrintStream printer;
    private final String[] stepValues;

    public EasyModeHangmanRenderer(PrintStream printStream) {
        printer = printStream;
        stepValues = new String[] {thirdStepPicture, fifthStepPicture, seventhStepPicture, ninthStepPicture};
    }

    @Override
    public void render(int step) {
        printer.println(stepValues[step]);
    }
}
