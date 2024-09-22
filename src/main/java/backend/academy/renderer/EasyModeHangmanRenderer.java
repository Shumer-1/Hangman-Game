package backend.academy.renderer;

import java.io.PrintStream;

public class EasyModeHangmanRenderer implements HangmanRenderer {

    private final PrintStream printer;

    public EasyModeHangmanRenderer(PrintStream printStream) {
        printer = printStream;
    }

    String startPicture = """
         _ _ _ _
         |     |
         |
         |
         |
        """;

    String firstStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |
        """;

    String secondStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |    / \\
        """;

    String finalPicture = """
         _ _ _ _
         |     |
         |     O
         |    -|-
         |    / \\
        """;

    @Override
    @SuppressWarnings("MagicNumber")
    public void render(int step) {
        printer.println(switch (step) {
            case 0 -> startPicture;
            case 1 -> firstStepPicture;
            case 2 -> secondStepPicture;
            default -> finalPicture;
        });
    }
}
